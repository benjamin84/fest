/*
 * Created on Dec 26, 2006
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright @2006 the original author or authors.
 */
package org.fest.assertions;

import org.fest.util.Strings;

import static org.fest.assertions.Fail.fail;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

/**
 * Understands assertion methods for <code>String</code>s.
 *
 * @author Yvonne Wang
 */
public final class StringAssert extends GroupAssert<String> {

  StringAssert(String actual) {
    super(actual);
  }
  
  /**
   * Verifies that the actual <code>String</code> is empty (not <code>null</code> with zero characters.)
   * @throws AssertionError if the actual <code>String</code> is <code>null</code> or not empty.
   */
  public void isEmpty() {
    if (!Strings.isEmpty(actual)) fail(concat("the String ", quote(actual), " should be empty"));
  }
  
  /**
   * Verifies that the actual <code>String</code> contains at least on character.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is empty.
   */
  public StringAssert isNotEmpty() {
    if (Strings.isEmpty(actual)) fail(concat("the String ", quote(actual), " should not be empty"));   
    return this;
  }
  
  /**
   * Verifies that the actual <code>String</code> is equal to the given one.
   * @param expected the given <code>String</code> to compare the actual <code>String</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is not equal to the given one.
   */
  @Override public StringAssert isEqualTo(String expected) {
    return (StringAssert)super.isEqualTo(expected);
  }
  
  /**
   * Verifies that the actual <code>String</code> is not equal to the given one.
   * @param other the given <code>String</code> to compare the actual <code>String</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is equal to the given one.
   */
  @Override public StringAssert isNotEqualTo(String other) {
    return (StringAssert)super.isNotEqualTo(other);
  }

  /**
   * Verifies that the actual <code>String</code> is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is <code>null</code>.
   */
  @Override public StringAssert isNotNull() {
    return (StringAssert)super.isNotNull();
  }

  /**
   * Verifies that the actual <code>String</code> is not the same as the given one.
   * @param other the given <code>String</code> to compare the actual <code>String</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is the same as the given one.
   */
  @Override public StringAssert isNotSameAs(String other) {
    return (StringAssert)super.isNotSameAs(other);
  }

  /**
   * Verifies that the actual <code>String</code> is the same as the given one.
   * @param expected the given <code>String</code> to compare the actual <code>String</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> is not the same as the given one.
   */
  @Override public StringAssert isSameAs(String expected) {
    return (StringAssert)super.isSameAs(expected);
  }

  /**
   * Verifies that the number of characters in the actual <code>String</code> is equal to the given one.
   * @param expected the expected number of characters in the actual <code>String</code>.
   * @return this assertion object.
   * @throws AssertionError if the number of characters of the actual <code>String</code> is not equal to the given 
   * one.
   */
  @Override public StringAssert hasSize(int expected) {
    return (StringAssert)super.hasSize(expected);
  }

  int actualGroupSize() {
    return actual.length();
  }

  /**
   * Verifies that the actual <code>String</code> contains the given one.
   * @param expected the given <code>String</code> expected to be contained in the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>String</code> does not contain the given one.
   */
  public StringAssert contains(String expected) {
    if (actual.indexOf(expected) == -1) 
      fail(concat("the String ", quote(actual), " should contain the String ", quote(expected)));
    return this;
  } 
}
