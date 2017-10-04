package io.leonis.zosma.math.function;

public interface Function5<T1, T2, T3, T4, T5, R> {
  R apply(T1 firstArgument, T2 secondArgument, T3 thirdArgument, T4 fourthArgument,
      T5 fifthArgument);
}
