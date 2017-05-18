package org.ssh.ipc.db;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.*;
import org.influxdb.dto.Point;
import org.reactivestreams.*;
import org.ssh.torch.Default;

/**
 * The Class InfluxSubscriber.
 *
 * @author Rimon Oz
 */
@Slf4j
public class InfluxSubscriber implements Subscriber<Point> {
  private final InfluxDB influx;
  private final String databaseName;

  /**
   * Creates a new {@link Subscriber} for InfluxDB running at the specified address and port,
   * authenticating using the supplied username and password (or none if empty).
   * @param address      The address of the database.
   * @param databaseName The name of the database.
   * @param username     The username to authenticate with.
   * @param password     The password to authenticate with.
   */
  public InfluxSubscriber(
      final @Default("http://localhost:8086/") String address,
      final @Default("test") String databaseName,
      final String username,
      final String password
  ) {
    this.databaseName = databaseName;
    if (username.isEmpty() && password.isEmpty())
      this.influx = InfluxDBFactory.connect(address);
    else
      this.influx = InfluxDBFactory.connect(address, username, password);
    this.influx.enableGzip();
    this.influx.createDatabase(this.databaseName);
    this.influx.enableBatch(2000, 1000, TimeUnit.MILLISECONDS);
  }

  /**
   * Creates a new {@link Subscriber} for InfluxDB
   * @param address
   * @param databaseName
   */
  public InfluxSubscriber(
      final @Default("http://localhost:8086/") String address,
      final @Default("test") String databaseName
  ) {
    this(address, databaseName, "", "");
  }

  @Override
  public void onSubscribe(final Subscription subscription) {
    subscription.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(final Point measurement) {
    try {
      this.influx.write(this.databaseName, "autogen", measurement);
    } catch (final Exception exception) {
      this.onError(exception);
    }
  }

  @Override
  public void onError(final Throwable throwable) {
    log.error("Encountered an error while preparing measurement record!", throwable);
  }

  @Override
  public void onComplete() {
    this.influx.close();
  }
}
