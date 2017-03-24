package org.ssh.math.geometry.motion;

/**
 * The Class MovingObject2D.
 * <p>
 * This class represents an object (in a 2-dimensional space) which has a position and velocity vector.
 *
 * @author Rimon Oz
 */
public interface MovingObject2D<T> extends MovingObject<T>, StationaryObject2D<T> {
    /**
     * Returns the X-coordinate (or the first coordinate) of the velocity vector.
     *
     * @return The X-coordinate (or the first coordinate) of the velocity vector.
     */
    double getXVelocity();

    /**
     * Returns the Y-coordinate (or the second coordinate) of the velocity vector.
     *
     * @return The Y-coordinate (or the second coordinate) of the velocity vector.
     */
    double getYVelocity();

}
