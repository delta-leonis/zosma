package io.leonis.zosma.math.function;

public interface Function3<T1, T2, T3, R> {
  R apply(T1 firstArgument, T2 secondArgument, T3 thirdArgument);
}
