package org.ssh.ipc.serialization.json;

import java.io.Serializable;
import lombok.Value;

/**
 * The Class Snippet.
 *
 * This class is used in the {@link JsonWriterTest}.
 *
 * @author Rimon Oz
 */
@Value
public class Snippet implements Serializable {
  private final String title;
  private final String body;
}
