package org.ssh.math.filter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.ssh.math.statistic.Distribution;
import org.ssh.math.statistic.SimpleDistribution;
import org.ssh.math.statistic.distribution.GaussianDistribution;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import reactor.core.publisher.Flux;

/**
 * The Test KalmanFilterPerformanceTest.
 *
 * @author Rimon Oz
 */
@Slf4j
public class KalmanFilterPerformanceTest {

  private final static double NOISE_RANGE = 10f;
  private final static int POINTS = 1000;
  private final INDArray stateTransitionMatrix = Nd4j.create(
      new double[]{
          1, 0,
          0.5f, 0
      },
      new int[]{2, 2});
  private final INDArray measurementTransitionMatrix = Nd4j.create(
      new double[]{
          1, 0,
          0, 1
      },
      new int[]{2, 2});
  private final INDArray measurementCovarianceMatrix = Nd4j.create(
      new double[]{
          0, 0,
          0, 0.01f
      },
      new int[]{2, 2});
  private final INDArray controlTransitionMatrix = Nd4j.create(
      new double[]{
          1, 0,
          0, 1
      },
      new int[]{2, 2});
  private final INDArray controlInputVector = Nd4j.create(
      new double[]{
          0.01f,
          0.01f
      },
      new int[]{2, 1});
  private final INDArray processCovariance = Nd4j.create(
      new double[]{
          0.001f, 0,
          0, 0.1f
      },
      new int[]{2, 2});
  private final List<INDArray> testData = IntStream.range(0, POINTS).sequential()
      .mapToObj(index -> Nd4j.create(
          new double[]{
              2 * index,
              index + NOISE_RANGE * ((double) Math.random()) - NOISE_RANGE / 2f
          },
          new int[]{2, 1}))
      .collect(Collectors.toList());

  /**
   * Filter.
   */
  @Test(invocationCount = 1)
  public void filter() {
    final KalmanFilter filter = new KalmanFilter();
    Flux.fromIterable(this.testData)
        .scan(new AggregateState(
                new GaussianDistribution(this.controlInputVector, this.processCovariance),
                new GaussianDistribution(this.controlInputVector, this.processCovariance)),
            (previousFilteredState, nextMeasurement) ->
                new AggregateState(
                    filter.apply(
                        this.stateTransitionMatrix,
                        this.measurementTransitionMatrix,
                        this.controlTransitionMatrix,
                        this.controlInputVector,
                        this.processCovariance,
                        new SimpleDistribution(
                            nextMeasurement,
                            this.measurementCovarianceMatrix),
                        previousFilteredState.getFilteredState()),
                    previousFilteredState.getFilteredState()))
        .subscribe();
  }

  /**
   * Gets run time.
   *
   * @param testResult the test result
   */
  @AfterMethod
  public void getRunTime(final ITestResult testResult) {
    final long time = testResult.getEndMillis() - testResult.getStartMillis();
    log.info("Iteration #" + testResult.getMethod().getCurrentInvocationCount()
        + " on " + POINTS + " measurements ran in " + time + "ms. "
        + (time / ((double) POINTS)) + "ms per measurement.");
  }

  /**
   * Initialization.
   */
  @BeforeClass
  public void initialization() {
    log.info("Starting Kalman filter test suite.");
  }

  /**
   * Terminate.
   */
  @AfterClass
  public void terminate() {
    log.info("Done with Kalman filter test suite.");
  }

  @Value
  private class AggregateState {
    private final Distribution filteredState;
    private final Distribution unfilteredState;
  }
}
