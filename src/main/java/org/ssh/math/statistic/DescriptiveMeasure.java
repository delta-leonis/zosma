package org.ssh.math.statistic;

/**
 * The Interface DescriptiveMeasure.
 *
 * This interface describes the functionality of a descriptive measure.
 *
 * @author Rimon Oz
 */
public interface DescriptiveMeasure {

  /**
   * The Enum DescriptiveMeasure.Center.
   *
   * This enumeration lists the types of descriptive counting measures.
   *
   * @author Rimon Oz
   */
  enum Count implements DescriptiveMeasure {
    /**
     * Total count.
     */
    TOTAL,
    /**
     * Dispersion count.
     */
    DISPERSION;
  }

  /**
   * The Enum DescriptiveMeasure.Summary.
   *
   * This enumeration lists the types of descriptive summary measures.
   *
   * @author Rimon Oz
   */
  enum Summary implements DescriptiveMeasure {
    /**
     * Description summary.
     */
    DESCRIPTION,
    /**
     * Validity summary.
     */
    VALIDITY,
    /**
     * Grouped summary.
     */
    GROUPED,
    /**
     * Percentage summary.
     */
    PERCENTAGE,
    /**
     * Contingency summary.
     */
    CONTINGENCY;
  }

  /**
   * The Enum DescriptiveMeasure.Dependence.
   *
   * This enumeration lists the types of descriptive measures of dependence.
   *
   * @author Rimon Oz
   */
  enum Dependence implements DescriptiveMeasure {
    /**
     * Pearson product moment dependence.
     */
    PEARSON_PRODUCT_MOMENT,
    /**
     * Rank dependence.
     */
    RANK,
    /**
     * Partial dependence.
     */
    PARTIAL;
  }

  /**
   * The Enum DescriptiveMeasure.Graphic.
   *
   * This enumeration lists the types of descriptive graphics.
   *
   * @author Rimon Oz
   */
  enum Graphic implements DescriptiveMeasure {
    /**
     * Bar chart graphic.
     */
    BAR_CHART,
    /**
     * Biplot graphic.
     */
    BIPLOT,
    /**
     * Box plot graphic.
     */
    BOX_PLOT,
    /**
     * Control chart graphic.
     */
    CONTROL_CHART,
    /**
     * Correlogram graphic.
     */
    CORRELOGRAM,
    /**
     * Fan chart graphic.
     */
    FAN_CHART,
    /**
     * Forest plot graphic.
     */
    FOREST_PLOT,
    /**
     * Historgram graphic.
     */
    HISTORGRAM,
    /**
     * Pie chart graphic.
     */
    PIE_CHART,
    /**
     * Q q plot graphic.
     */
    Q_Q_PLOT,
    /**
     * Run chart graphic.
     */
    RUN_CHART,
    /**
     * Scatter plot graphic.
     */
    SCATTER_PLOT,
    /**
     * Stem and leaf graphic.
     */
    STEM_AND_LEAF,
    /**
     * Radar chart graphic.
     */
    RADAR_CHART;
  }

  /**
   * The Interface DescriptiveMeasure.Continuous.
   *
   * This interface describes the functionality of a continuous descriptive
   * measure.
   *
   * @author Rimon Oz
   */
  interface Continuous extends DescriptiveMeasure {

    /**
     * The Enum DescriptiveMeasure.Continuous.Center.
     *
     * This enumeration lists the types of continuous descriptive measures
     * of center.
     *
     * @author Rimon Oz
     */
    enum Center implements DescriptiveMeasure {
      /**
       * Mean center.
       */
      MEAN,
      /**
       * Median center.
       */
      MEDIAN,
      /**
       * Mode center.
       */
      MODE;
    }

    /**
     * The Enum DescriptiveMeasure.Continuous.Dispersion.
     *
     * This enumeration lists the types of continuous descriptive measures
     * of dispersion.
     *
     * @author Rimon Oz
     */
    enum Dispersion implements DescriptiveMeasure {
      /**
       * Variance dispersion.
       */
      VARIANCE,
      /**
       * Standard deviation dispersion.
       */
      STANDARD_DEVIATION,
      /**
       * Variation dispersion.
       */
      VARIATION,
      /**
       * Percentile dispersion.
       */
      PERCENTILE,
      /**
       * Range dispersion.
       */
      RANGE,
      /**
       * Interquartile range dispersion.
       */
      INTERQUARTILE_RANGE;
    }

    /**
     * The Interface DescriptiveMeasure.Continuous.Shape.
     *
     * This interface describes the functionality of a continuous
     * descriptive measure of shape.
     *
     * @author Rimon Oz
     */
    interface Shape extends Continuous {

      /**
       * The Enum DescriptiveMeasure.Continuous.Shape.Moment.
       *
       * This enumeration enumerates the types of continuous descriptive
       * shape measures of moment.
       *
       * @author Rimon Oz
       */
      enum Moment implements DescriptiveMeasure {
        /**
         * Skewness moment.
         */
        SKEWNESS,
        /**
         * Kurtosis moment.
         */
        KURTOSIS,
        /**
         * L moment moment.
         */
        L_MOMENT;
      }
    }
  }
}
