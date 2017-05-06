/* Copyright (c) 2013-2017, Jesper Öqvist
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

public class TestToString {

  @Test public void testNumber() {
    assertEquals("123", new JsonNumber("123").toString());
  }

  @Test public void testString() {
    assertEquals("\"eat more carrots\"", new JsonString("eat more carrots").toString());
  }

  @Test public void testTrue() {
    assertEquals("true", Json.TRUE.toString());
  }

  @Test public void testFalse() {
    assertEquals("false", Json.FALSE.toString());
  }
  @Test public void testMember() {
    assertEquals("\"foo\" : \"bar\"", new JsonMember("foo", new JsonString("bar")).toString());
  }

  @Test public void testNull() {
    assertEquals("null", Json.NULL.toString());
  }

  @Test public void testObject() {
    JsonObject obj = new JsonObject();
    obj.add(new JsonMember("foo", new JsonString("bar")));
    obj.add(new JsonMember("cow", new JsonNumber("4")));
    assertEquals("{ \"foo\" : \"bar\", \"cow\" : 4 }", obj.toString());
  }

  @Test public void testArray() {
    JsonArray array = new JsonArray();
    array.add(new JsonString("foo"));
    array.add(new JsonString("bar"));
    array.add(Json.TRUE);
    array.add(Json.FALSE);
    array.add(Json.NULL);
    array.add(Json.UNKNOWN);
    array.add(new JsonNumber("4"));
    assertEquals("[ \"foo\", \"bar\", true, false, null, \"<unknown>\", 4 ]", array.toString());
  }
}
