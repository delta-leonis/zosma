package io.leonis.ipc.io;

import com.typesafe.config.*;
import lombok.AllArgsConstructor;
import org.reactivestreams.*;
import reactor.core.publisher.Flux;

/**
 * @author Jeroen de Jong
 */
@AllArgsConstructor
public final class ConfigPublisher implements Publisher<Config> {
  private final String resourceBasename;

  @Override
  public void subscribe(final Subscriber<? super Config> s) {
    Flux.intervalMillis(500)
        .map(tick -> ConfigFactory.load(resourceBasename))
        .distinctUntilChanged()
        .subscribe(s);
  }
}
