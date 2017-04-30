package org.ssh.ipc.serialization.json;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

/**
 * The Test JsonWriterTest.
 *
 * @author Rimon Oz
 */
public class JsonWriterTest {

  /**
   * This test determines whether the first found JsonWriteHandler implementation
   * does its job.
   * @throws Exception
   */
  @Test
  public void testToJson() throws Exception {
    JsonWriter jsonWriter = new JsonWriter();
    assertTrue(jsonWriter.write(new Snippet("blurg", "nurg"))
        .replace(" ", "")
        .equals("{\"title\":\"blurg\",\"body\":\"nurg\"}"));
  }
}