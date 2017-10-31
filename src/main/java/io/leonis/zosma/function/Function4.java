package io.leonis.zosma.function;

public interface Function4<T1, T2, T3, T4, R> {
  R apply(T1 firstArgument, T2 secondArgument, T3 thirdArgument, T4 fourthArgument);
}
