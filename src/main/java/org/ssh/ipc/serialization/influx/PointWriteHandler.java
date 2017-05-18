package org.ssh.ipc.serialization.influx;

import org.influxdb.dto.Point;
import org.ssh.ipc.serialization.WriteHandler;

/**
 * The Interface PointWriteHandler.
 *
 * @author Rimon Oz
 */
public interface PointWriteHandler<O> extends WriteHandler<O> {
  /**
   * Converts the supplied object into a {@link Point}.
   * @param src The object to convert.
   * @return    The object serialized as a {@link Point}
   */
  Point write(final O src);
}
