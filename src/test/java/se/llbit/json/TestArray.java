/* Copyright (c) 2017, Jesper Öqvist
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package se.llbit.json;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TestArray {
  /** The add method supports different input types. */
  @Test public void testAdd() {
    JsonArray array = new JsonArray();
    assertTrue(array.isEmpty());
    array.add("!");
    array.add(711);
    array.add(0xFF00FF00FF00L);
    array.add(0.2);
    array.add(0.3);
    array.add(true);
    array.add(false);
    array.add(Json.NULL);

    assertEquals("!", array.get(0).asString(""));
    assertEquals(711, array.get(1).asInt(117));
    assertEquals(0xFF00FF00FF00L, array.get(2).asLong(1010));
    assertEquals(0.2, array.get(3).asDouble(117), 0.00001);
    assertEquals(0.3f, array.get(4).asFloat(117), 0.00001f);
    assertEquals(true, array.get(5).boolValue(false));
    assertEquals(false, array.get(6).boolValue(true));
    assertSame(Json.NULL, array.get(7));
    assertFalse(array.isEmpty());
  }

  /** The set method can modify existing elements and insert out-of-order. */
  @Test public void testSet() {
    JsonArray array = new JsonArray();
    assertTrue(array.isEmpty());
    array.add("wrong");
    array.add("wrong");
    array.add("wrong");
    array.set(0, Json.of("!"));
    array.set(2, Json.of(0xFF00FF00FF00L));
    array.set(1, Json.of(711));
    array.add(Json.of(0.2));
    array.add(Json.of(0.3));
    array.add(Json.of(true));
    array.add(Json.of(false));
    array.add(Json.NULL);

    assertEquals("!", array.get(0).asString(""));
    assertEquals(711, array.get(1).asInt(117));
    assertEquals(0xFF00FF00FF00L, array.get(2).asLong(1010));
    assertEquals(0.2, array.get(3).asDouble(117), 0.00001);
    assertEquals(0.3f, array.get(4).asFloat(117), 0.00001f);
    assertEquals(true, array.get(5).boolValue(false));
    assertEquals(false, array.get(6).boolValue(true));
    assertSame(Json.NULL, array.get(7));
    assertFalse(array.isEmpty());
  }

  /** May not add a null JsonValue. */
  @Test(expected = NullPointerException.class)
  public void testAddErr() {
    new JsonArray().add((JsonValue) null);
  }

  /** The set method can not insert past the end of the array. */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testSetErr() {
    JsonArray array = new JsonArray();
    array.add("bort");
    array.set(0, Json.of(711)); // OK!
    array.set(1, Json.of(711)); // Error.
  }

  @Test public void testArrayConversion() {
    JsonArray array = new JsonArray();
    assertSame(array, array.array());
    assertSame(array, array.asArray());
    assertFalse(Json.of("bort").isArray());
    assertFalse(array.object().isArray());
    assertNotSame(array, Json.of("bort").array());
    assertNotSame(array, new JsonObject().asArray());
  }
}
