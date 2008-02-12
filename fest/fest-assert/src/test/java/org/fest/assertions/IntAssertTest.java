/*
 * Created on Jun 14, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2007 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.testng.annotations.Test;

/**
 * Tests for <code>{@link IntAssert}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 */
public class IntAssertTest {

  @Test public void shouldPassIfValuesAreEqualAsAnticipated() {
    new IntAssert(8).isEqualTo(8);
  }

  @Test public void shouldFailIfValuesAreNotEqualAndExpectedEqual() {
    expectAssertionError("expected:<8> but was:<6>").on(new CodeToTest() {
      public void run() {
        new IntAssert(6).isEqualTo(8);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfValuesAreNotEqualAndExpectedEqual() {
    expectAssertionError("[A Test] expected:<8> but was:<6>").on(new CodeToTest() {
      public void run() {
        new IntAssert(6).as("A Test").isEqualTo(8);
      }
    });
  }

  @Test public void shouldPassIfValuesAreNotEqualAsAnticipated() {
    new IntAssert(6).isNotEqualTo(8);
  }

  @Test public void shouldFailIfValuesAreEqualAndExpectingNotEqual() {
    expectAssertionError("actual value:<8> should not be equal to:<8>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).isNotEqualTo(8);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfValuesAreEqualAndExpectingNotEqual() {
    expectAssertionError("[A Test] actual value:<8> should not be equal to:<8>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).as("A Test").isNotEqualTo(8);
      }
    });
  }

  @Test public void shouldPassIfActualIsGreaterThanExpectedAsAnticipated() {
    new IntAssert(8).isGreaterThan(6);
  }

  @Test public void shouldFailIfActualIsEqualToExpectedAndExpectingGreaterThan() {
    expectAssertionError("actual value:<8> should be greater than:<8>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).isGreaterThan(8);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsEqualToExpectedAndExpectingGreaterThan() {
    expectAssertionError("[A Test] actual value:<8> should be greater than:<8>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).as("A Test").isGreaterThan(8);
      }
    });
  }

  @Test public void shouldFailIfActualIsLessThanExpectedAndExpectingGreaterThan() {
    expectAssertionError("actual value:<6> should be greater than:<8>").on(new CodeToTest() {
      public void run() {
        new IntAssert(6).isGreaterThan(8);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsLessThanExpectedAndExpectingGreaterThan() {
    expectAssertionError("[A Test] actual value:<6> should be greater than:<8>").on(new CodeToTest() {
      public void run() {
        new IntAssert(6).as("A Test").isGreaterThan(8);
      }
    });
  }

  @Test public void shouldPassIfActualIsLessThanExpectedAsAnticipated() {
    new IntAssert(6).isLessThan(8);
  }

  @Test public void shouldFailIfActualIsEqualToExpectedAndExpectingLessThan() {
    expectAssertionError("actual value:<8> should be less than:<8>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).isLessThan(8);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsEqualToExpectedAndExpectingLessThan() {
    expectAssertionError("[A Test] actual value:<8> should be less than:<8>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).as("A Test").isLessThan(8);
      }
    });
  }

  @Test public void shouldFailIfGreaterThanAndExpectedLessThan() {
    expectAssertionError("actual value:<8> should be less than:<6>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).isLessThan(6);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfGreaterThanAndExpectedLessThan() {
    expectAssertionError("[A Test] actual value:<8> should be less than:<6>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).as("A Test").isLessThan(6);
      }
    });
  }

  @Test public void shouldPassIfActualIsPositiveAsAnticipated() {
    new IntAssert(6).isPositive();
  }

  @Test public void shouldFailIfActualIsZeroAndExpectingPositive() {
    expectAssertionError("actual value:<0> should be greater than:<0>").on(new CodeToTest() {
      public void run() {
        new IntAssert(0).isPositive();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsZeroAndExpectingPositive() {
    expectAssertionError("[A Test] actual value:<0> should be greater than:<0>").on(new CodeToTest() {
      public void run() {
        new IntAssert(0).as("A Test").isPositive();
      }
    });
  }

  @Test public void shouldFailIfActualLessToZeroAndExpectingPositive() {
    expectAssertionError("actual value:<-8> should be greater than:<0>").on(new CodeToTest() {
      public void run() {
        new IntAssert(-8).isPositive();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualLessToZeroAndExpectingPositive() {
    expectAssertionError("[A Test] actual value:<-8> should be greater than:<0>").on(new CodeToTest() {
      public void run() {
        new IntAssert(-8).as("A Test").isPositive();
      }
    });
  }

  @Test public void shouldPassIfActualIsNegativeAsAnticipated() {
    new IntAssert(-6).isNegative();
  }

  @Test public void shouldFailIfActualIsZeroAndExpectingNegative() {
    expectAssertionError("actual value:<0> should be less than:<0>").on(new CodeToTest() {
      public void run() {
        new IntAssert(0).isNegative();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsZeroAndExpectingNegative() {
    expectAssertionError("[A Test] actual value:<0> should be less than:<0>").on(new CodeToTest() {
      public void run() {
        new IntAssert(0).as("A Test").isNegative();
      }
    });
  }

  @Test public void shouldFailIfActualIsGreaterThanZeroAndExpectedNegative() {
    expectAssertionError("actual value:<8> should be less than:<0>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).isNegative();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsGreaterThanZeroAndExpectedNegative() {
    expectAssertionError("[A Test] actual value:<8> should be less than:<0>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).as("A Test").isNegative();
      }
    });
  }

  @Test public void shouldPassIfActualIsZeroAsAnticipated() {
    new IntAssert(0).isZero();
  }

  @Test public void shouldFailIfNotZeroAndExpectedZero() {
    expectAssertionError("expected:<0> but was:<9>").on(new CodeToTest() {
      public void run() {
        new IntAssert(9).isZero();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfNotZeroAndExpectedZero() {
    expectAssertionError("[A Test] expected:<0> but was:<9>").on(new CodeToTest() {
      public void run() {
        new IntAssert(9).as("A Test").isZero();
      }
    });
  }

  @Test public void shouldPassIfActualGreaterThanOrEqualToExpectedAsAnticipated() {
    new IntAssert(8).isGreaterOrEqualTo(8).isGreaterOrEqualTo(6);
  }

  @Test public void shouldFailIfActualIsLessThanExpectedAndExpectingGreaterThanOrEqualTo() {
    expectAssertionError("actual value:<6> should be greater than or equal to:<8>").on(new CodeToTest() {
      public void run() {
        new IntAssert(6).isGreaterOrEqualTo(8);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsLessThanExpectedAndExpectingGreaterThanOrEqualTo() {
    expectAssertionError("[A Test] actual value:<6> should be greater than or equal to:<8>").on(new CodeToTest() {
      public void run() {
        new IntAssert(6).as("A Test").isGreaterOrEqualTo(8);
      }
    });
  }

  @Test public void shouldPassIfActualIsLessThanOrEqualToExpectedAsAnticipated() {
    new IntAssert(6).isLessOrEqualTo(6).isLessOrEqualTo(8);
  }

  @Test public void shouldFailIfGreaterOrEqualToAndExpectedLessThan() {
    expectAssertionError("actual value:<8> should be less than or equal to:<6>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).isLessOrEqualTo(6);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfGreaterOrEqualToAndExpectedLessThan() {
    expectAssertionError("[A Test] actual value:<8> should be less than or equal to:<6>").on(new CodeToTest() {
      public void run() {
        new IntAssert(8).as("A Test").isLessOrEqualTo(6);
      }
    });
  }
}
