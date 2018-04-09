# `zosma`
> soccer is simple, but it is difficult to play simple

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fb040017069b489faf37ee1ef601906a)](https://www.codacy.com/app/delta-leonis/zosma?utm_source=github.com&utm_medium=referral&utm_content=delta-leonis/zosma&utm_campaign=badger)
[![CircleCI](https://circleci.com/gh/delta-leonis/zosma.svg?style=svg)](https://circleci.com/gh/delta-leonis/zosma)

You'll need at least Java 1.8 ([jre](https://www.java.com/download/)
/[jdk](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html))
to run `zosma`.

## Dependency

#### Maven

```
<dependency>
    <groupId>io.leonis</groupId>
    <artifactId>zosma</artifactId>
    <version>0.0.8</version>
</dependency>
```

#### Gradle

```
compile 'io.leonis:zosma:0.0.8'
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
  private final Location           location;
  private final ChessPieceIdentity identity;

  @Value
  public static class ChessPieceIdentity implements Identity {
    private final ChessPiece.Type  type;
    private final ChessGame.Player owner;
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
