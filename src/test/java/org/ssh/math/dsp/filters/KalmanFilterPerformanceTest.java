package org.ssh.math.dsp.filters;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.ssh.math.statistics.Distribution;
import org.ssh.math.statistics.SimpleDistribution;
import org.ssh.math.statistics.distributions.GaussianDistribution;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The Test KalmanFilterPerformanceTest.
 *
 * @author Rimon Oz
 */
@Slf4j
public class KalmanFilterPerformanceTest {
    private final static float NOISE_RANGE = 10f;
    private final static int POINTS = 10000;
    private final INDArray stateTransitionMatrix = Nd4j.create(
            new float[]{
                    1, 0,
                    0.5f, 0
            },
            new int[]{2, 2});
    private final INDArray measurementTransitionMatrix = Nd4j.create(
            new float[]{
                    1, 0,
                    0, 1
            },
            new int[]{2, 2});
    private final INDArray measurementCovarianceMatrix = Nd4j.create(
            new float[]{
                    0, 0,
                    0, 0.01f
            },
            new int[]{2, 2});
    private final INDArray controlTransitionMatrix = Nd4j.create(
            new float[]{
                    1, 0,
                    0, 1
            },
            new int[]{2, 2});
    private final INDArray controlInputVector = Nd4j.create(
            new float[]{
                    0.00f,
                    0.00f
            },
            new int[]{2, 1});
    private final INDArray processCovariance = Nd4j.create(
            new float[]{
                    0.001f, 0,
                    0, 0.1f
            },
            new int[]{2, 2});
    private final List<INDArray> testData = IntStream.range(0, POINTS).sequential()
            .mapToObj(index -> Nd4j.create(
                    new float[]{
                            2 * index,
                            index + NOISE_RANGE * ((float) Math.random()) - NOISE_RANGE / 2f
                    },
                    new int[]{2, 1}))
            .collect(Collectors.toList());

    @Test(invocationCount = 1)
    public void filter() {
        Flux.fromIterable(this.testData)
                .scan(new AggregateState(
                                new GaussianDistribution(this.controlInputVector, this.processCovariance),
                                new GaussianDistribution(this.controlInputVector, this.processCovariance)),
                        (previousFilteredState, nextMeasurement) ->
                                new AggregateState(
                                        KalmanFilter.apply(
                                                this.stateTransitionMatrix,
                                                this.measurementTransitionMatrix,
                                                this.controlTransitionMatrix,
                                                this.controlInputVector,
                                                this.measurementCovarianceMatrix,
                                                new SimpleDistribution(
                                                        nextMeasurement,
                                                        previousFilteredState.getFilteredState().getCovariance()),
                                                previousFilteredState.getFilteredState()),
                                        new SimpleDistribution(
                                                nextMeasurement,
                                                previousFilteredState.getFilteredState().getCovariance())))
                .map(aggregateState -> {
                    System.out.println("Before: " + aggregateState.getUnfilteredState().getMean()
                            + " " + aggregateState.getUnfilteredState().getMean().shapeInfoToString());
                    System.out.println("After:  " + aggregateState.getFilteredState().getMean()
                            + " " + aggregateState.getFilteredState().getMean().shapeInfoToString());
                    return aggregateState;
                })
                .subscribe();
    }

    @AfterMethod
    public void getRunTime(ITestResult testResult) {
        long time = testResult.getEndMillis() - testResult.getStartMillis();
        log.info("Iteration #" + testResult.getMethod().getCurrentInvocationCount()
                + " on " + POINTS + " measurements ran in " + time + "ms. "
                + (time / ((double) POINTS)) + "ms per measurement.");
    }

    @BeforeClass
    public void initialization() {
        log.info("Starting Kalman filter test suite.");
    }

    @AfterClass
    public void terminate() {
        log.info("Done with Kalman filter test suite.");
    }

    @Value
    private class AggregateState {
        private final Distribution<INDArray> filteredState;
        private final Distribution<INDArray> unfilteredState;
    }
}