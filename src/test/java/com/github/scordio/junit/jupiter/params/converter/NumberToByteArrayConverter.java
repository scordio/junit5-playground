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
      return ByteBuffer.allocate(Byte.BYTES).put((Byte) source).array();
    } else if (source instanceof Short) {
      return ByteBuffer.allocate(Short.BYTES).putShort((Short) source).array();
    } else if (source instanceof Integer) {
      return ByteBuffer.allocate(Integer.BYTES).putInt((Integer) source).array();
    } else if (source instanceof Long) {
      return ByteBuffer.allocate(Long.BYTES).putLong((Long) source).array();
    } else {
      throw new ArgumentConversionException("Conversion from " + source.getClass().getName() + " is not supported");
    }
  }

}
