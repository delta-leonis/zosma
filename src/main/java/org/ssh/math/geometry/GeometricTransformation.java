package org.ssh.math.geometry;

import org.ssh.math.geometry.motion.StationaryObject;

/**
 * The Interface GeometricTransformation.
 * <p>
 * This interface describes a geometric transformation of some object.
 *
 * @param <T> the type parameter
 * @author Rimon Oz
 */
@FunctionalInterface
public interface GeometricTransformation<T extends StationaryObject> {

  /**
   * Returns the result of applying the transformation to the argument.
   *
   * @param toBeTransformed The argument of the transformation.
   * @return The transformed argument.
   */
  T transform(T toBeTransformed);

  /**
   * The Enum Type.
   * <p>
   * This enumeration lists the possible geometric transformations.
   *
   * @author Rimon Oz
   */
  enum Type {
    /**
     * Translation type.
     */
    TRANSLATION,
    /**
     * Reflection type.
     */
    REFLECTION,
    /**
     * Glide reflection type.
     */
    GLIDE_REFLECTION,
    /**
     * Rotation type.
     */
    ROTATION,
    /**
     * Scaling type.
     */
    SCALING,
    /**
     * Shear type.
     */
    SHEAR;
  }
}
