package org.ssh.torch.workspace;

import com.google.common.collect.ImmutableMap;
import com.googlecode.lanterna.gui2.table.*;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.ssh.benchmarks.GroupedMeasurement;
import org.ssh.ipc.db.InfluxSubscriber;
import org.ssh.ipc.serialization.influx.PointWriter;
import org.ssh.ipc.system.SystemInfoPublisher;
import org.ssh.ipc.system.publisher.*;
import org.ssh.torch.view.*;
import org.ssh.torch.view.model.reflect.ConstructorViewModel;
import org.ssh.torch.view.window.modal.ConstructorModal;
import reactor.core.publisher.*;

/**
 * The Class InfluxSystemWorkspace.
 *
 * This class represents a {@link org.ssh.torch.view.Workspace} which starts streams of
 * {@link org.ssh.ipc.system.resource.SystemInfoState} and logs them to database.
 *
 * @author Rimon Oz
 */
@Slf4j
public class InfluxSystemWorkspace extends AbstractWorkspace {
  private final PointWriter pointWriter = new PointWriter();

  /**
   * Constructs a new InfluxSystemWorkspace.
   */
  public InfluxSystemWorkspace() {
    super("SYSTEM MONITORING");
  }

  @Override
  protected void construct() {
    try {
      this.addWindow(new ConstructorModal<InfluxSubscriber>(
          Arrays.asList(
              new ConstructorViewModel(
                  InfluxSubscriber.class.getConstructor(String.class, String.class)),
              new ConstructorViewModel(InfluxSubscriber.class
                  .getConstructor(String.class, String.class, String.class, String.class))),
          subscriber -> {
            final Table<String> publisherStatusTable = new Table<>("Loading...");

            final TopicProcessor<Map<String, Boolean>> stateProcessor = TopicProcessor.create();

            stateProcessor
                .scan((leftMap, rightMap) ->
                    ImmutableMap.<String, Boolean>builder()
                        .putAll(leftMap)
                        .putAll(rightMap)
                        .build())
                .map(mapping -> {
                  final TableModel<String> table = new TableModel<>("", "STATUS");

                  mapping.forEach(
                      (name, status) -> table.addRow(name, status ? "RUNNING" : "NOT RUNNING"));

                  return table;
                })
                .subscribe(publisherStatusTable::setTableModel);

            this.createDebugFlux(stateProcessor, new RamInfoPublisher(), "RAM")
                .subscribe(subscriber);

            this.createDebugFlux(stateProcessor, new NetworkInfoPublisher(), "NETWORK")
                .subscribe(subscriber);

            this.createDebugFlux(stateProcessor, new DiskInfoPublisher(), "DISK")
                .subscribe(subscriber);

            this.createDebugFlux(stateProcessor, new ProcessorInfoPublisher(), "PROCESSOR")
                .subscribe(subscriber);

            final Window window = new BasicWindow("Metric Publishers");
            window.setComponent(publisherStatusTable);
            this.addWindow(window);
          }));
    } catch (final NoSuchMethodException exception) {
      log.error("Exception encountered!", exception);
    }
  }

  /**
   * Creates a debugging {@link Flux} which supplied {@link org.ssh.ipc.system.resource.SystemInfoState}
   * from the supplied publisher and udpates the {@link TopicProcessor state processor} accordingly.
   * @param stateProcessor The {@link TopicProcessor state processor} to update.
   * @param publisher      The {@link Flux} which emits {@link org.ssh.ipc.system.resource.SystemInfoState}.
   * @param name           The name of the debugging {@link Flux}.
   * @return               The debugging {@link Flux} itself.
   */
  private Flux<Point> createDebugFlux(
      final TopicProcessor<Map<String, Boolean>> stateProcessor,
      final SystemInfoPublisher<? extends GroupedMeasurement> publisher,
      final String name
  ) {
    return Flux.from(publisher)
        .map(this.pointWriter::write)
        .doOnSubscribe(a -> stateProcessor.onNext(Collections.singletonMap(name, true)))
        .doOnComplete(() -> stateProcessor.onNext(Collections.singletonMap(name, false)));
  }
}
