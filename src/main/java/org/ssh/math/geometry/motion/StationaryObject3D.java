package org.ssh.math.geometry.motion;

/**
 * The Class StationaryObject3D.
 * <p>
 * This class represents an object (in a 3-dimensional space) which has a position vector.
 *
 * @author Rimon Oz
 */
public interface StationaryObject3D<T> extends StationaryObject2D<T> {
    /**
     * Returns the Z-coordinate (or the third coordinate) of the position vector.
     *
     * @return The Z-coordinate (or the third coordinate) of the position vector.
     */
    double getZ();
}
