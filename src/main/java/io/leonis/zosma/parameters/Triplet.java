package io.leonis.zosma.parameters;

import io.reactivex.functions.Function3;
import lombok.*;

/**
 * @author jeroen.dejong.
 */
@Value
@AllArgsConstructor
public class Triplet<I0, I1, I2> {
  I0 first;
  I1 second;
  I2 third;

  public <R> R apply(Function3<I0, I1, I2, R> function) throws Exception {
    return function.apply(first, second, third);
  }

  public <I3> Quadruplet<I0, I1, I2, I3> join(Singleton<I3> singleton) {
    return new Quadruplet<>(this.first, this.second, this.third, singleton.getFirst());
  }
}
