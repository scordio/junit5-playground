package com.github.scordio.junit.jupiter.params.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.converter.ArgumentConversionException;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.InstanceOfAssertFactories.BYTE_ARRAY;

class NumberToByteArrayConverterTest {

  private final NumberToByteArrayConverter underTest = new NumberToByteArrayConverter();

  @Test
  void should_fail_with_non_byte_array_target() {
    // WHEN
    Throwable thrown = catchThrowable(() -> underTest.convert(0, Object.class));
    // THEN
    then(thrown)
      .isInstanceOf(ArgumentConversionException.class)
      .hasMessage("Can only convert to byte array");
  }

  @Test
  void should_fail_with_unsupported_source() {
    // WHEN
    Throwable thrown = catchThrowable(() -> underTest.convert(new Object(), byte[].class));
    // THEN
    then(thrown)
      .isInstanceOf(ArgumentConversionException.class)
      .hasMessage("Conversion from java.lang.Object is not supported");
  }

  @Test
  void should_return_null_if_source_is_null() {
    // WHEN
    Object result = underTest.convert(null, byte[].class);
    // THEN
    then(result).isNull();
  }

  @Test
  void should_convert_byte() {
    // GIVEN
    byte source = 0x7F;
    // WHEN
    Object result = underTest.convert(source, byte[].class);
    // THEN
    then(result)
      .asInstanceOf(BYTE_ARRAY)
      .containsExactly(127);
  }

  @Test
  void should_convert_short() {
    // GIVEN
    short source = 0x7E7F;
    // WHEN
    Object result = underTest.convert(source, byte[].class);
    // THEN
    then(result)
      .asInstanceOf(BYTE_ARRAY)
      .containsExactly(126, 127);
  }

  @Test
  void should_convert_int() {
    // GIVEN
    int source = 0x7C7D7E7F;
    // WHEN
    Object result = underTest.convert(source, byte[].class);
    // THEN
    then(result)
      .asInstanceOf(BYTE_ARRAY)
      .containsExactly(124, 125, 126, 127);
  }

  @Test
  void should_convert_long() {
    // GIVEN
    long source = 0x78797A7B7C7D7E7FL;
    // WHEN
    Object result = underTest.convert(source, byte[].class);
    // THEN
    then(result)
      .asInstanceOf(BYTE_ARRAY)
      .containsExactly(120, 121, 122, 123, 124, 125, 126, 127);
  }

}
