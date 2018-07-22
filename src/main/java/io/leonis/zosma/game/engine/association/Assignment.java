package io.leonis.zosma.game.engine.association;

import static java.time.temporal.ChronoUnit.MILLIS;

import io.leonis.algieba.Temporal;
import io.leonis.zosma.game.data.Player.PlayerIdentity;
import java.time.Duration;
import lombok.Value;

/**
 * The Class Assignment.
 * 
 * An {@link Temporal} assignment which can expire based on the provided lifetime.
 * 
 * @author Jeroen de Jong
 */
@Value
public class Assignment implements Temporal {
  private final Duration lifetime;
  private final PlayerIdentity assignee;
  private final Role role;
  private final long timestamp = System.currentTimeMillis();

  public boolean isUnexpired() {
    return timestamp + lifetime.get(MILLIS) > System.currentTimeMillis();
  }
}
