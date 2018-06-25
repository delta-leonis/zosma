package io.leonis.zosma.parameters;

import io.reactivex.functions.Function4;
import lombok.*;

/**
 * @author jeroen.dejong.
 */
@Value
@AllArgsConstructor
public class Quadruplet<I0, I1, I2, I3> {
  I0 first;
  I1 second;
  I2 third;
  I3 fourth;

  public <R> R apply(Function4<I0, I1, I2, I3, R> function) throws Exception {
    return function.apply(first, second, third, fourth);
  }
}
