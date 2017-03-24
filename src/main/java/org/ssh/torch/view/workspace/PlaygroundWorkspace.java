package org.ssh.torch.view.workspace;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Window;
import org.ssh.ipc.system.SystemComponent;
import org.ssh.ipc.system.publisher.ProcessorInfoPublisher;
import org.ssh.ipc.system.publisher.RamInfoPublisher;
import org.ssh.ipc.system.resource.ProcessorInfo;
import org.ssh.ipc.system.resource.state.RamInfoState;
import org.ssh.ipc.system.resource.state.SystemInfoState;
import org.ssh.math.statistics.DescriptiveMeasure;
import org.ssh.math.units.BaseUnit;
import org.ssh.torch.view.AbstractWorkspace;
import org.ssh.torch.view.component.MeasurementsTable;
import org.ssh.torch.view.component.graph.BarGraph;
import org.ssh.torch.view.model.systeminfo.RamInfoViewModel;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author jeroen.dejong
 */
public class PlaygroundWorkspace extends AbstractWorkspace {

    public PlaygroundWorkspace() {
        super("Random stuffies space");
    }

    private Window cpuUsageWindow(){
        Window window = new BasicWindow("CPU usage");
        MeasurementsTable table = new MeasurementsTable();
        Flux.from(new ProcessorInfoPublisher())
                .map(ProcessorInfo::getMeasurements)
                .subscribe(table);
        window.setComponent(table);
        return window;
    }

    @Override
    protected void construct() {
        this.addWindow(this.newRambarWindow())
                .addWindow(this.newRambarWindow())
                .addWindow(this.newRambarWindow())
                .addWindow(this.newRandbarWindow())
                .addWindow(this.newRandbarWindow())
                .addWindow(this.newRandbarWindow())
                .addWindow(this.newRandbarWindow())
                .addWindow(this.cpuUsageWindow());
    }

    private Window newRambarWindow(){
        Window window = new BasicWindow("Rambar");
        BarGraph rambar = new BarGraph<>(RamInfoViewModel::getRangedUsedMemory);
        rambar.setPreferredWidth(100);
        Flux.from(new RamInfoPublisher())
            .map(RamInfoViewModel::new)
            .subscribe(rambar);
        window.setComponent(rambar);
        return window;
    }

    private Window newRandbarWindow() {
        Window window = new BasicWindow("randbar");
        BarGraph randbar = new BarGraph<>(RamInfoViewModel::getRangedUsedMemory);
        Flux.range(0, 100)
                .delayElements(Duration.of(50L, ChronoUnit.MILLIS))
                .map(free ->
                        new RamInfoState(
                                new SystemInfoState<>(
                                        SystemComponent.MEMORY,
                                        BaseUnit.BYTE,
                                        DescriptiveMeasure.Summary.DESCRIPTION,
                                        free.longValue() * 1_000_000L,
                                        "Total free memory"),
                                new SystemInfoState<>(
                                        SystemComponent.MEMORY,
                                        BaseUnit.BYTE,
                                        DescriptiveMeasure.Summary.DESCRIPTION,
                                        100 * 1_000_000L,
                                        "Total allocated memory"),
                                new SystemInfoState<>(
                                        SystemComponent.MEMORY,
                                        BaseUnit.BYTE,
                                        DescriptiveMeasure.Summary.DESCRIPTION,
                                        (100-free.longValue()) * 1_000_000L,
                                        "Total used memory")))
                .subscribeOn(Schedulers.single())
                .doOnComplete(window::close)
                .map(RamInfoViewModel::new)
                .subscribe(randbar);
        window.setComponent(randbar);
        return window;
    }
}
