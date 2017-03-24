package org.ssh.math.statistics;

/**
 * The Interface DescriptiveMeasure.
 * <p>
 * This interface describes the functionality of a descriptive measure.
 *
 * @author Rimon Oz
 */
public interface DescriptiveMeasure {
    /**
     * The Enum DescriptiveMeasure.Center.
     * <p>
     * This enumeration lists the types of descriptive counting measures.
     *
     * @author Rimon Oz
     */
    enum Count implements DescriptiveMeasure {
        TOTAL,
        DISPERSION;
    }

    /**
     * The Enum DescriptiveMeasure.Summary.
     * <p>
     * This enumeration lists the types of descriptive summary measures.
     *
     * @author Rimon Oz
     */
    enum Summary implements DescriptiveMeasure {
        DESCRIPTION,
        VALIDITY,
        GROUPED,
        PERCENTAGE,
        CONTINGENCY;
    }

    /**
     * The Enum DescriptiveMeasure.Dependence.
     * <p>
     * This enumeration lists the types of descriptive measures of dependence.
     *
     * @author Rimon Oz
     */
    enum Dependence implements DescriptiveMeasure {
        PEARSON_PRODUCT_MOMENT,
        RANK,
        PARTIAL;
    }

    /**
     * The Enum DescriptiveMeasure.Graphic.
     * <p>
     * This enumeration lists the types of descriptive graphics.
     *
     * @author Rimon Oz
     */
    enum Graphic implements DescriptiveMeasure {
        BAR_CHART,
        BIPLOT,
        BOX_PLOT,
        CONTROL_CHART,
        CORRELOGRAM,
        FAN_CHART,
        FOREST_PLOT,
        HISTORGRAM,
        PIE_CHART,
        Q_Q_PLOT,
        RUN_CHART,
        SCATTER_PLOT,
        STEM_AND_LEAF,
        RADAR_CHART;
    }

    /**
     * The Interface DescriptiveMeasure.Continuous.
     * <p>
     * This interface describes the functionality of a continuous descriptive
     * measure.
     *
     * @author Rimon Oz
     */
    interface Continuous extends DescriptiveMeasure {
        /**
         * The Enum DescriptiveMeasure.Continuous.Center.
         * <p>
         * This enumeration lists the types of continuous descriptive measures
         * of center.
         *
         * @author Rimon Oz
         */
        enum Center implements DescriptiveMeasure {
            MEAN,
            MEDIAN,
            MODE;
        }

        /**
         * The Enum DescriptiveMeasure.Continuous.Dispersion.
         * <p>
         * This enumeration lists the types of continuous descriptive measures
         * of dispersion.
         *
         * @author Rimon Oz
         */
        enum Dispersion implements DescriptiveMeasure {
            VARIANCE,
            STANDARD_DEVIATION,
            VARIATION,
            PERCENTILE,
            RANGE,
            INTERQUARTILE_RANGE;
        }

        /**
         * The Interface DescriptiveMeasure.Continuous.Shape.
         * <p>
         * This interface describes the functionality of a continuous
         * descriptive measure of shape.
         *
         * @author Rimon Oz
         */
        interface Shape extends Continuous {
            /**
             * The Enum DescriptiveMeasure.Continuous.Shape.Moment.
             * <p>
             * This enumeration enumerates the types of continuous descriptive
             * shape measures of moment.
             *
             * @author Rimon Oz
             */
            enum Moment implements DescriptiveMeasure {
                SKEWNESS,
                KURTOSIS,
                L_MOMENT;
            }
        }
    }
}
