package org.ssh.ipc.serialization.influx;

import java.lang.reflect.Modifier;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.*;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.reflections.Reflections;
import org.ssh.ipc.serialization.Writer;
import org.ssh.math.function.LambdaExceptions;

/**
 * The Class PointWriter.
 *
 * This class represents a {@link Writer} of {@link Point}.
 *
 * @author Rimon Oz
 */
@Slf4j
public class PointWriter implements Writer<Point> {
  private final Map<Class, PointWriteHandler> pointWriters;

  public PointWriter() {
    this.pointWriters = new Reflections("org.ssh.ipc.serialization.influx")
        .getSubTypesOf(PointWriteHandler.class).stream()
        .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
        .filter(clazz -> !clazz.isInterface())
        .map(LambdaExceptions.rethrowFunction(Class::newInstance))
        .flatMap(
            writeHandler -> (Stream<Entry<Class, PointWriteHandler>>) writeHandler
                .getTypes().stream()
                .map(type -> new SimpleImmutableEntry<>(type, writeHandler)))
        .collect(Collectors.toMap(
            Entry::getKey,
            Entry::getValue));
  }

  @Override
  public <I> Point write(final I input) {
    return this.pointWriters.get(input.getClass()).write(input);
  }
}
