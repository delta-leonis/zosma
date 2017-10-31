package io.leonis.zosma.function;

public interface Function7<T1, T2, T3, T4, T5, T6, T7, R> {
  R apply(T1 firstArgument, T2 secondArgument, T3 thirdArgument, T4 fourthArgument,
      T5 fifthArgument, T6 sixthArgument, T7 seventhArgument);
}
