# `zosma`
> center of our universe

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fb040017069b489faf37ee1ef601906a)](https://www.codacy.com/app/delta-leonis/zosma?utm_source=github.com&utm_medium=referral&utm_content=delta-leonis/zosma&utm_campaign=badger)
[![CircleCI](https://circleci.com/gh/delta-leonis/zosma.svg?style=svg)](https://circleci.com/gh/delta-leonis/zosma)

`zosma` is a [reactive](https://projectreactor.io/) game agent modeling framework. Its purpose is to
provide a minimal declarative framework with which to process game data.

You'll need at least Java 1.8 ([jre](https://www.java.com/download/)
/[jdk](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html))
to run `zosma`.

## Dependency

#### Maven

```
<dependency>
    <groupId>io.leonis</groupId>
    <artifactId>zosma</artifactId>
    <version>0.0.5</version>
</dependency>
```

#### Gradle

```
compile 'io.leonis:zosma:0.0.5'
```

## Overview

The first step in creating a game agent is to form some notion of game state, and how it changes over time.
A game state is used by the game agent in order to compute possible ways to play. Usually game 
data is broadcast by a game host server (or some other application) which measures (or simulates) 
the state of a game. For instance, one could represent the state of a chess 
game as follows (getters [ommitted for brevity](https://projectlombok.org/features/Value)):

```java
@Value
public class ChessGame {
  private final long                      timestamp;
  private final Map<ChessPiece, Location> chessPiecePositions;
  private final Player                    currentPlayer;
  
  public enum Player {
    WHITE,
    BLACK
  }
}
```

In a similar fashion game agents must have some notion of strategic state, and how it changes over 
time, in order to decide the exact way in which play must be executed. Take for instance the 
following representation of a chess strategy:

```java
@Value
public class ChessStrategy {
  private final long       timestamp;
  private final ChessPiece toMove;
  private final Location   destination;
}
```

Ultimately a game agent is nothing more than logic which allow consecutive frames of game data to
to be translated into game strategies. In Java this could be expressed as a function which takes
a [`Publisher`](http://www.reactive-streams.org/reactive-streams-1.0.1-javadoc/org/reactivestreams/Publisher.html?is-external=true)
of game state representations and returns a `Publisher` of strategy representations, or 
`Function<Publisher<GameState>, Publisher<Strategy>>`. These strategies can then be relayed to a 
system which executes them. Note that trying to implement such a system using 
`Function<GameState, Strategy>` only will not allow the implementation to take into account 
previously received game state representations, or previously emitted strategy representations,
whereas a `Function<Publisher<GameState>, Publisher<Strategy>>` will:

```java
// create a stream of game state representations
Flux.from(chessGamePublisher)           
    .transform(chessGames ->
        Flux.from(chessGames)
            // store the two most recent game states in a List
            .buffer(2, 1)               
            .map(previousTwoGames -> {
              // ... do something with the two most recent game state representations
            }));
```

### Identities, Rules, and Formations

A game (usually) contains some objects which can interact with each other. These interactions mutate
state of the game. In the example below the `ChessPiece` is a data representation of such an
object. `zosma` requires that each of these objects be identifiable, i.e. it should be able to
supply an identity (getters omitted for brevity):

```java
@Value
public class ChessPiece implements Identity.Supplier {
  private final Location            location;
  private final ChessPiece.Identity identity;

  @Value
  public static class Identity implements io.leonis.zosma.game.Identity {
    private final ChessPiece.Type type;
    private final ChessGame.Player    owner;
  }
  
  public enum Type {
    KING,
    QUEEN,
    ROOK,
    BISHOP,
    KNIGHT,
    PAWN
  }
}
```

A strategy will often specify some desired configuration of agents. This configuration can be
purely positional, but can also be used for assigning roles to agents (not applicable to chess, 
since chess roles are not dynamic). For example:

```java
@Value
public class ChessFormation implements Formation<ChessPiece, Location> {
  private final Map<ChessPiece, Location> desiredFormation;
  
  @Override
  public Location getFormationFor(final ChessPiece piece) {
    return this.desiredFormation.get(piece);
  }
}
```

The legality of an action in a game and the win-conditions are determined by the rules of the game. 
The game agent must know these rules in order to determine the validity of its own potential moves
or to anticipate a specific state of the game. A rule is evaluated by examining a game state:

```java
public class LoseCondition implements Rule<ChessGame, ChessGame.Player> {
  @Override
  Set<ChessGame.Player> getViolators(final ChessGame input) {
    // a player has lost, if
    return input.getChessPiecePositions().keySet().stream()
      // its king
      .filter(chessPiece -> chessPiece.getIdentity().getType().equals(ChessPiece.Type.KING))
      // is off the board
      .filter(chessPiece -> chessPiece.getLocation.equals(Location.OUTSIDE)
      .map(chessPiece -> chessPiece.getIdentity().getOwner())
      .collect(Collectors.toSet()));
  }
}
```

### Deducers

Incoming data is not always expressed in a compact form such as the snippets described in the system
overview. When creating game agents using pre-existing protocols, or external systems, the provided
data representations are often bloated or contain data in undesirable formats. Similarly, data which
has been received may need to be embellished with information which can be used downstream. 
This pattern of transforming emitted data is so prevalent that it has been explicitly typed in 
`zosma` under the name `Deducer`. Note that `Deducer` is simply an interface
for an [Rx operator](http://reactivex.io/documentation/operators.html):

```java
public class ChessStrategyDeducer implements Deducer<ChessGame, ChessStrategy> {
  @Override
  public Publisher<ChessStrategy> apply(final Publisher<ChessGame> chessGamePublisher) {
    // compute strategy
  }    
}
```

Which would then be used as follows:

```java
Flux.from(chessGamePublisher)
    .transform(new ChessFormationDeducer())
```

**N.B.**: There is a `Deducer.Identity` for when the input-type is the same as the output-type. Examples
of use cases where this may occur include application of filters and other correction mechanism. 

#### Parallelizing deducers

At any point during data processing it might be desirable to parallelize certain computations and
combine their results. `zosma` contains a `ParallelDeducer` which allow the most recent emissions
of a list of deducers to be combined:

```java
Flux.from(chessGamePublisher)
    .transform(
        new ParallelDeducer<>(
            new ChessStrategyDeducer(),
            new OtherChessStrategyDeducer(),
            (chessStrategy, otherChessStrategy) -> {
              // combine, pick, or create a new strategy and return it
            }))
```

As can be seen in the example above, `ParallelDeducer` takes deducers as its first *n* arguments 
(n <= 10) and a combining function as its last argument. The combining function takes *n* arguments, 
where each argument corresponds to the latest emission by the *n*th provided deducer.

### Putting it together

`zosma` provides a helper class named `Zosma` for modeling game IO in five parts which represents the
run configuration of the system:

  1. Publisher of external input
      * The external input to the system, which is represented as a `Publisher` of the raw input 
      to the system.
  1. Internal input adapter (optional)
      * The internal input adapter to the system, which transforms data emitted by the external input 
      into a format which can be processed by the game agent.
  1. Internal output adapter
      * The internal output adapter to the system, which transforms data emitted by the internal 
      input adapter into a format which can be processed by the internal output adapter. This is 
      where the game logic resides.
  1. External output adapter (optional)
      * The external output adapter to the system, which transforms data emitted by the internal 
      output adapter into a format which can be processed by the external output adapter.
  1. Subscriber
      * The subscription mechanism of the system, which consumes data emitted by the external 
      output adapter.

The internal input adapter and external output adapter are optional adapters which allow data types
from external libraries to be converted to an intermediary type which is interpreted by the game agent.

Following the example of a chess game, `Zosma` might be instantiated in the long form as follows:

```java
final Zosma chessZosma = new Zosma<>(
    new ChessGamePublisher(),
    new ChessGameRepresentationAdapter(),
    new ChessStrategizer(),
    new ChessStrategyRepresentationAdapter(),
    new ChessStrategySubscriber());
```

Or in the short form as follows:

```java
final Zosma chessZosma = new Zosma<>(
    new ChessGamePublisher(),
    new ChessStrategizer(),
    new ChessStrategySubscriber());
```

Once instantiated, the game agent can be run by calling `Zosma#run`:

```java
chessZosma.run();
```

Notice that `Zosma` implements the `Runnable` interface which means you can run it in a separate thread
or make use of [`ExecutorService`](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html)
in order to manage the life cycle of the game agent:

```java
final ExecutorService zosmaExecutor = Executors.newSingleThreadExecutor();

final Future futureZosma = zosmaExecutor.submit(chessZosma); 
```

### Suppliers

Since the data evolves as it is processed the type of the data container may change as well. In 
order to allow composition of these data containers through `Deducer`s, these `Deducer`s should 
be parametrized using interface instead of class types. Unfortunately Java's `Supplier` 
can only be implemented once due to type erasure; this is why most data types in `zosma` have a
nested `Supplier` (or `SetSupplier`, `MappingSupplier`, e.a.) interface (see [Rule](https://github.com/delta-leonis/zosma/blob/master/src/main/java/io/leonis/zosma/game/Rule.java#L24-L26) 
or [Controller](https://github.com/delta-leonis/zosma/blob/master/src/main/java/io/leonis/zosma/ipc/peripheral/Controller.java) for example).

This allows for both of the following classes:

```java
@Value
public class InputType implements Controller.MappingSupplier, Rule.SetSupplier {
  private final Map<Controller, Set<Agent>> agentMapping;
  private final Set<Rule> rules;
}
@Value
public class AnotherInputType implements Rule.SetSupplier {
  private final Set<Rule> rules;
}
```

To be acted on by the following `Deducer`:

```java
public class ExampleDeducer<I implements Rule.SetSupplier> implements Deducer.Identity<I> { /** ... */ }
```

Notice that Java [allows multiple bounds](https://docs.oracle.com/javase/tutorial/java/generics/bounded.html) 
on type parameters, so it is possible to require multiple suppliers on the generic input type as follows:

```java
public class AnotherExampleDeducer<I implements Rule.SetSupplier & Controller.MappingSupplier> 
    implements Deducer.Identity<I>{ /** ... */ }
```

### Helpers

`zosma` contains two helper packages, `ipc` and `function`. The `ipc` package contains `Publisher`s
and `Subscriber`s for common protocols (such as TCP, UDP, multicast, and others). The `function`
package contains functional interfaces for `Function3` to `Function10` which are missing from the
JDK.

## Documentation

The javadoc for the current code on `master` can be found on https://delta-leonis.github.io/zosma/

## Building

Make sure you have `gradle>=v2.10` installed. Run the following to build the application:

```
  gradle build
```

## Copyright

This project is licensed under the AGPL version 3 license (see LICENSE).

```
zosma - delta-leonis
Copyright (C) 2017 Rimon Oz, Jeroen de Jong, Ryan Meulenkamp, Thomas Hakkers

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
```
