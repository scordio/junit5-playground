package com.github.scordio.junit.jupiter.params.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NumberToByteArrayTest {

  @ParameterizedTest
  @ValueSource(bytes = {Byte.MIN_VALUE, 0, Byte.MAX_VALUE})
  void bytes(@ConvertWith(NumberToByteArrayConverter.class) byte[] byteArray) {
    assertThat(byteArray).hasSize(1);
  }

  @ParameterizedTest
  @ValueSource(shorts = {Short.MIN_VALUE, 0, 0xFF, Short.MAX_VALUE})
  void shorts(@ConvertWith(NumberToByteArrayConverter.class) byte[] byteArray) {
    assertThat(byteArray).hasSize(2);
  }

  @ParameterizedTest
  @ValueSource(ints = {Integer.MIN_VALUE, 0, 0xFF, 0xFFFF, 0xFFFFFF, Integer.MAX_VALUE})
  void integers(@ConvertWith(NumberToByteArrayConverter.class) byte[] byteArray) {
    assertThat(byteArray).hasSize(4);
  }

  @ParameterizedTest
  @ValueSource(longs = {
    Long.MIN_VALUE,
    0,
    0xFF,
    0xFFFF,
    0xFFFFFF,
    0xFFFFFFFF,
    0xFFFFFFFFFFL,
    0xFFFFFFFFFFFFL,
    0xFFFFFFFFFFFFFFL,
    Long.MAX_VALUE
  })
  void longs(@ConvertWith(NumberToByteArrayConverter.class) byte[] byteArray) {
    assertThat(byteArray).hasSize(8);
  }

}
