package org.ssh.math.function;

public interface Function6<T1, T2, T3, T4, T5, T6, R> {
  R apply(T1 firstArgument, T2 secondArgument, T3 thirdArgument, T4 fourthArgument,
      T5 fifthArgument, T6 sixthArgument);
}
