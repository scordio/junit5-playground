package com.github.scordio.junit.jupiter.params;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

// https://github.com/junit-team/junit5/issues/2708
class MethodSourceWithArraysBugTest {

  @ParameterizedTest
  @MethodSource("objectArrays")
  void should_inject_object_array(Object[] actual) {
  }

//  private static Stream<Object[]> objectArrays() {
//    return Stream.of(
//      new Object[]{new Object(), new Object()},
//      new Object[]{new Object(), new Object()}
//    );
//  }

  private static Stream<Arguments> objectArrays() {
    return Stream.of(
      arguments((Object) new Object[]{new Object(), new Object()}),
      arguments((Object) new Object[]{new Object(), new Object()})
    );
  }

  @ParameterizedTest
  @MethodSource("primitiveArrays")
  void should_inject_primitive_array(Object actual) {
    assertThat(actual).isInstanceOf(int[].class);
  }

  private static Stream<int[]> primitiveArrays() {
    return Stream.of(
      new int[]{1, 2, 3},
      new int[]{1, 2, 3}
    );
  }

}
