package org.ssh.math.geometry.motion;

/**
 * The Class MovingObject.
 * <p>
 * This class represents an object (in an n-dimensional space) which has a position and velocity vector.
 *
 * @author Rimon Oz
 */
public interface MovingObject<T> extends StationaryObject<T> {
    /**
     * Returns the velocity vector of the moving object.
     *
     * @return The velocity vector of the moving object.
     */
    T getVelocity();
}