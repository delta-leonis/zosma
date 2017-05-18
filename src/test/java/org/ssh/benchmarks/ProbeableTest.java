package org.ssh.benchmarks;

import static org.testng.Assert.assertTrue;

import com.google.common.collect.Sets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;

/**
 * The Test ProbableTest
 *
 * Tests all various aspects of {@link Probeable}.
 *
 * @author Jeroen de Jong
 */
public class ProbeableTest {
  @Test
  public void getProbes() {
    ProbeablePublisher<Void> subject = new ProbeablePublisher<>();
    subject.flux()
        .doOnNext(subject.createProbeTarget("Before"))
        .map(Object::toString)
        .doOnNext(subject.createProbeTarget("After"));
    assertTrue(subject.getProbes().equals(Sets.newHashSet("Before", "After")));
  }

  @Test
  public void probeBefore() throws InterruptedException {
    // build a probeable publisher
    ProbeablePublisher<Long> subject = new ProbeablePublisher<>();
    // create a latch to track counts asynchronously
    CountDownLatch cdl = new CountDownLatch(1);
    // build the flux
    subject.flux().doOnNext(subject.createProbeTarget("Before")).subscribe();
    //setup an expectation
    DescriptiveMeasurement<Long> expectation = new SimpleMeasurement<>(5L, "Before");
    // add a probe which'll trigger the latch if the measurement is as expected
    subject.probe("Before", measurement -> {
      if(measurement.getValue().equals(expectation.getValue())
          && measurement.getLabel().equals(expectation.getLabel()))
        cdl.countDown();
    });
    //submit the value of the expectation
    subject.next(expectation.getValue());
    //await the latch or fail after 1 second
    assertTrue(cdl.await(1, TimeUnit.SECONDS));
  }

  @Test
  public void probeAfter() throws InterruptedException {
    // build a probeable publisher
    ProbeablePublisher<Long> subject = new ProbeablePublisher<>();
    // create a latch to track counts asynchronously
    CountDownLatch cdl = new CountDownLatch(1);
    // build the flux
    subject.flux()
        .doOnNext(subject.createProbeTarget("Before"))
        .map(Math::log)
        .doOnNext(subject.createProbeTarget("After"))
        .subscribe();
    Long input = 5L;
    //setup an expectation
    DescriptiveMeasurement<Double> expectation = new SimpleMeasurement<>(Math.log(input), "After");
    // add a probe which'll trigger the latch if the measurement is as expected
    subject.probe("After", measurement -> {
      if(measurement.getValue().equals(expectation.getValue())
          && measurement.getLabel().equals(expectation.getLabel()))
        cdl.countDown();
    });
    //submit the value of the expectation
    subject.next(input);
    //await the latch or fail after 1 second
    assertTrue(cdl.await(1, TimeUnit.SECONDS));
  }
}
