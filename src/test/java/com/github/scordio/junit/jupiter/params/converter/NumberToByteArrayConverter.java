package com.github.scordio.junit.jupiter.params.converter;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.nio.ByteBuffer;

public class NumberToByteArrayConverter extends SimpleArgumentConverter {

  @Override
  protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
    if (targetType != byte[].class) {
      throw new ArgumentConversionException("Can only convert to byte array");
    }

    if (source == null) {
      return null;
    }

    if (source instanceof Byte) {
      return ByteBuffer.allocate(1).put((Byte) source).array();
    } else if (source instanceof Short) {
      return ByteBuffer.allocate(2).putShort((Short) source).array();
    } else if (source instanceof Integer) {
      return ByteBuffer.allocate(4).putInt((Integer) source).array();
    } else if (source instanceof Long) {
      return ByteBuffer.allocate(8).putLong((Long) source).array();
    } else {
      throw new ArgumentConversionException("Conversion from " + source.getClass().getName() + " is not supported");
    }
  }

}
