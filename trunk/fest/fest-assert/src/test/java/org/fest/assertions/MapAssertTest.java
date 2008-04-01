/*
 * Created on Jan 24, 2008
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
 * Copyright @2008 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.CommonFailures.*;
import static org.fest.assertions.MapAssert.entry;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.fest.assertions.MapAssert.Entry;
import org.fest.test.CodeToTest;
import org.testng.annotations.Test;

/**
 * Tests for <code>{@link MapAssert}</code>.
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class MapAssertTest {

  private static final Map<Object, Object> EMPTY_MAP = new HashMap<Object, Object>();

  @Test public void shouldSetDescription() {
    MapAssert assertion = new MapAssert(EMPTY_MAP);
    assertNull(assertion.description());
    assertion.as("A Test");
    assertEquals(assertion.description(), "A Test");
  }

  @Test public void shouldSetDescriptionSafelyForGroovy() {
    MapAssert assertion = new MapAssert(EMPTY_MAP);
    assertNull(assertion.description());
    assertion.describedAs("A Test");
    assertEquals(assertion.description(), "A Test");
  }

  private static class NullMap extends Condition<Map<?, ?>> {
    @Override public boolean matches(Map<?, ?> m) {
      return m == null;
    }
  }

  @Test public void shouldPassIfConditionSatisfied() {
    new MapAssert(null).satisfies(new NullMap());
  }

  @Test public void shouldThrowErrorIfConditionIsNullWhenCheckingIfSatisfied() {
    expectIllegalArgumentExceptionIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        new MapAssert(EMPTY_MAP).satisfies(null);
      }
    });
  }

  @Test public void shouldFailIfConditionNotSatisfied() {
    expectAssertionError("actual value:<{}> should satisfy condition:<NullMap>").on(new CodeToTest() {
      public void run() {
        new MapAssert(EMPTY_MAP).satisfies(new NullMap());
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfConditionNotSatisfied() {
    expectAssertionError("[A Test] actual value:<{}> should satisfy condition:<NullMap>").on(new CodeToTest() {
      public void run() {
        new MapAssert(EMPTY_MAP).as("A Test").satisfies(new NullMap());
      }
    });
  }

  @Test public void shouldFailIfConditionNotSatisfiedShowingDescriptionOfCondition() {
    expectAssertionError("actual value:<{}> should satisfy condition:<Null map>").on(new CodeToTest() {
      public void run() {
        new MapAssert(EMPTY_MAP).satisfies(new NullMap().as("Null map"));
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfConditionNotSatisfiedShowingDescriptionOfCondition() {
    expectAssertionError("[A Test] actual value:<{}> should satisfy condition:<Null map>").on(new CodeToTest() {
      public void run() {
        new MapAssert(EMPTY_MAP).as("A Test").satisfies(new NullMap().as("Null map"));
      }
    });
  }

  //
  @Test public void shouldPassIfConditionNotSatisfied() {
    new MapAssert(EMPTY_MAP).doesNotSatisfy(new NullMap());
  }

  @Test public void shouldThrowErrorIfConditionIsNullWhenCheckingIfNotSatisfied() {
    expectIllegalArgumentExceptionIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        new MapAssert(EMPTY_MAP).doesNotSatisfy(null);
      }
    });
  }

  @Test public void shouldFailIfConditionSatisfied() {
    expectAssertionError("actual value:<null> should not satisfy condition:<NullMap>").on(new CodeToTest() {
      public void run() {
        new MapAssert(null).doesNotSatisfy(new NullMap());
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfConditionSatisfied() {
    expectAssertionError("[A Test] actual value:<null> should not satisfy condition:<NullMap>").on(new CodeToTest() {
      public void run() {
        new MapAssert(null).as("A Test").doesNotSatisfy(new NullMap());
      }
    });
  }

  @Test public void shouldFailIfConditionSatisfiedShowingDescriptionOfCondition() {
    expectAssertionError("actual value:<null> should not satisfy condition:<Null map>").on(new CodeToTest() {
      public void run() {
        new MapAssert(null).doesNotSatisfy(new NullMap().as("Null map"));
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfConditionSatisfiedShowingDescriptionOfCondition() {
    expectAssertionError("[A Test] actual value:<null> should not satisfy condition:<Null map>").on(new CodeToTest() {
      public void run() {
        new MapAssert(null).as("A Test").doesNotSatisfy(new NullMap().as("Null map"));
      }
    });
  }

  @Test public void shouldPassIfGivenKeysAreInMap() {
    Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
    new MapAssert(map).keySetIncludes("key1", "key2");
  }

  @Test public void shouldFailIfActualIsNullWhenCheckingIfContainsKeys() {
    shouldFailIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).keySetIncludes("key1");
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNullWhenCheckingIfContainsKeys() {
    shouldFailShowingDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).as("A Test").keySetIncludes("key1");
      }
    });
  }

  @Test public void shouldThrowErrorIfArrayOfKeysIsNullWhenCheckingIfContainsKeys() {
    expectIllegalArgumentException("The given array of keys should not be null").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
        Object[] keys = null;
        new MapAssert(map).keySetIncludes(keys);
      }
    });
  }

  @Test public void shouldFailIfGivenKeysAreNotInMap() {
    expectAssertionError("the map:<{'key1'=1, 'key2'=2}> does not contain the key:<['key4']>").on(
        new CodeToTest() {
          public void run() {
            Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
            new MapAssert(map).keySetIncludes("key4");
          }
        });
  }

  @Test public void shouldFailShowingDescriptionIfGivenKeysAreNotInMap() {
    expectAssertionError("[A Test] the map:<{'key1'=1, 'key2'=2}> does not contain the keys:<['key4', 'key5']>").on(
        new CodeToTest() {
          public void run() {
            Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
            new MapAssert(map).as("A Test").keySetIncludes("key4", "key5");
          }
        });
  }
  
  @Test public void shouldPassIfGivenKeysAreNotInMap() {
    Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
    new MapAssert(map).keySetExcludes("key3", "key4");
  }

  @Test public void shouldFailIfActualIsNullWhenCheckingIfExcludesKeys() {
    shouldFailIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).keySetExcludes("key1");
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNullWhenCheckingIfExcludesKeys() {
    shouldFailShowingDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).as("A Test").keySetExcludes("key1");
      }
    });
  }

  @Test public void shouldThrowErrorIfArrayOfKeysIsNullWhenCheckingIfExcludesKeys() {
    expectIllegalArgumentException("The given array of keys should not be null").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
        Object[] keys = null;
        new MapAssert(map).keySetExcludes(keys);
      }
    });
  }

  @Test public void shouldFailIfGivenKeysAreInMap() {
    expectAssertionError("the map:<{'key1'=1, 'key2'=2}> contains the key:<['key2']>").on(
        new CodeToTest() {
          public void run() {
            Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
            new MapAssert(map).keySetExcludes("key2");
          }
        });
  }

  @Test public void shouldFailShowingDescriptionIfGivenKeysAreInMap() {
    expectAssertionError("[A Test] the map:<{'key1'=1, 'key2'=2}> contains the keys:<['key1', 'key2']>").on(
        new CodeToTest() {
          public void run() {
            Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
            new MapAssert(map).as("A Test").keySetExcludes("key1", "key2");
          }
        });
  }

  @Test public void shouldPassIfGivenValuesAreInMap() {
    Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
    new MapAssert(map).valuesInclude(1, 2);
  }

  @Test public void shouldFailIfActualIsNullWhenCheckingIfContainsValues() {
    shouldFailIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).valuesInclude(8);
      }
    });
  }

  @Test public void shouldShowingDescriptionFailIfActualIsNullWhenCheckingIfContainsValues() {
    shouldFailShowingDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).as("A Test").valuesInclude(8);
      }
    });
  }

  @Test public void shouldThrowErrorIfArrayOfKeysIsNullWhenCheckingIfContainsValues() {
    expectIllegalArgumentException("The given array of values should not be null").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
        Object[] values = null;
        new MapAssert(map).valuesInclude(values);
      }
    });
  }

  @Test public void shouldFailIfGivenValuesAreNotInMap() {
    expectAssertionError("the map:<{'key1'=1, 'key2'=2}> does not contain the value:<[4]>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
        new MapAssert(map).valuesInclude(4);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfGivenValuesAreNotInMap() {
    expectAssertionError("[A Test] the map:<{'key1'=1, 'key2'=2}> does not contain the values:<[4, 5]>").on(
        new CodeToTest() {
          public void run() {
            Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
            new MapAssert(map).as("A Test").valuesInclude(4, 5);
          }
        });
  }

  @Test public void shouldPassIfGivenValuesAreNotInMap() {
    Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
    new MapAssert(map).valuesExclude(6, 8);
  }

  @Test public void shouldFailIfActualIsNullWhenCheckingIfExcludesValues() {
    shouldFailIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).valuesExclude(8);
      }
    });
  }

  @Test public void shouldShowingDescriptionFailIfActualIsNullWhenCheckingIfExcludesValues() {
    shouldFailShowingDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).as("A Test").valuesExclude(8);
      }
    });
  }

  @Test public void shouldThrowErrorIfArrayOfKeysIsNullWhenCheckingIfExcludesValues() {
    expectIllegalArgumentException("The given array of values should not be null").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
        Object[] values = null;
        new MapAssert(map).valuesExclude(values);
      }
    });
  }

  @Test public void shouldFailIfGivenValuesAreInMap() {
    expectAssertionError("the map:<{'key1'=1, 'key2'=2}> contains the value:<[1]>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
        new MapAssert(map).valuesExclude(1);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfGivenValuesAreInMap() {
    expectAssertionError("[A Test] the map:<{'key1'=1, 'key2'=2}> contains the values:<[1, 2]>").on(
        new CodeToTest() {
          public void run() {
            Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
            new MapAssert(map).as("A Test").valuesExclude(1, 2);
          }
        });
  }

  @Test public void shouldPassIfGivenEntryIsInMap() {
    Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
    new MapAssert(map).includes(entry("key1", 1));
  }

  @Test public void shouldThrowErrorIfEntryIsNullwhenCheckingIfContainsEntry() {
    expectIllegalArgumentException("The entry to check should not be null").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
        Entry[] entries = { entry("key6", 6), null };
        new MapAssert(map).includes(entries);
      }
    });
  }

  @Test public void shouldFailIfActualIsNullWhenCheckingIfContainsEntry() {
    shouldFailIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).includes(entry("key6", 6));
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNullWhenCheckingIfContainsEntry() {
    shouldFailShowingDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).as("A Test").includes(entry("key6", 6));
      }
    });
  }

  @Test public void shouldThrowErrorIfEntryArrayIsNullWhenCheckingIfContainsEntry() {
    expectIllegalArgumentException("The given array of entries should not be null").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
        Entry[] entry = null;
        new MapAssert(map).includes(entry);
      }
    });
  }

  @Test public void shouldThrowErrorIfEntryIsNullWhenCheckingIfContainsEntry() {
    expectIllegalArgumentException("The entry to check should not be null").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
        Entry entry = null;
        new MapAssert(map).includes(entry);
      }
    });
  }

  @Test public void shouldFailIfGivenEntryIsNotInMap() {
    expectAssertionError("the map:<{'key1'=1, 'key2'=2}> does not contain the entry:<['key6'=6]>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
        new MapAssert(map).includes(entry("key6", 6));
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfGivenEntryIsNotInMap() {
    expectAssertionError("[A Test] the map:<{'key1'=1, 'key2'=2}> does not contain the entry:<['key6'=6]>").on(
        new CodeToTest() {
          public void run() {
            Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
            new MapAssert(map).as("A Test").includes(entry("key6", 6));
          }
        });
  }

  @Test public void shouldFailIfGivenEntriesIsNotInMap() {
    expectAssertionError("the map:<{'key1'=1, 'key2'=2}> does not contain the entries:<['key6'=6, 'key8'=8]>").on(
        new CodeToTest() {
          public void run() {
            Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
            new MapAssert(map).includes(entry("key6", 6), entry("key8", 8));
          }
        });
  }

  @Test public void shouldFailShowingDescriptionIfGivenEntriesIsNotInMap() {
    expectAssertionError("[A Test] the map:<{'key1'=1, 'key2'=2}> does not contain the entries:<['key6'=6, 'key8'=8]>")
        .on(new CodeToTest() {
          public void run() {
            Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
            new MapAssert(map).as("A Test").includes(entry("key6", 6), entry("key8", 8));
          }
        });
  }

  @Test public void shouldFailIfGivenValueNotAssociatedWithExistingKey() {
    expectAssertionError("the map:<{'key1'=1, 'key2'=2}> does not contain the entry:<['key1'=6]>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
        new MapAssert(map).includes(entry("key1", 6));
      }
    });
  }

  @Test public void shouldFailIfMapsAreEqualAndExpectingNotEqual() {
    expectAssertionError("actual value:<{'key1'=1, 'key2'=2}> should not be equal to:<{'key1'=1, 'key2'=2}>").on(
        new CodeToTest() {
          public void run() {
            Map<Object, Object> map1 = map(entry("key1", 1), entry("key2", 2));
            Map<Object, Object> map2 = map(entry("key1", 1), entry("key2", 2));
            new MapAssert(map1).isNotEqualTo(map2);
          }
        });
  }

  @Test public void shouldFailShowingDescriptionIfMapsAreEqualAndExpectingNotEqual() {
    expectAssertionError("[A Test] actual value:<{'key1'=1, 'key2'=2}> should not be equal to:<{'key1'=1, 'key2'=2}>")
        .on(new CodeToTest() {
          public void run() {
            Map<Object, Object> map1 = map(entry("key1", 1), entry("key2", 2));
            Map<Object, Object> map2 = map(entry("key1", 1), entry("key2", 2));
            new MapAssert(map1).as("A Test").isNotEqualTo(map2);
          }
        });
  }

  @Test public void shouldFailIfActualIsNotEqualToExpectedAndExpectingEqual() {
    expectAssertionError("expected:<{'key6'=6, 'key8'=8}> but was:<{'key1'=1, 'key2'=2}>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map1 = map(entry("key1", 1), entry("key2", 2));
        Map<Object, Object> map2 = map(entry("key6", 6), entry("key8", 8));
        new MapAssert(map1).isEqualTo(map2);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNotEqualToExpectedAndExpectingEqual() {
    expectAssertionError("[A Test] expected:<{'key6'=6, 'key8'=8}> but was:<{'key1'=1, 'key2'=2}>").on(
        new CodeToTest() {
          public void run() {
            Map<Object, Object> map1 = map(entry("key1", 1), entry("key2", 2));
            Map<Object, Object> map2 = map(entry("key6", 6), entry("key8", 8));
            new MapAssert(map1).as("A Test").isEqualTo(map2);
          }
        });
  }

  @Test public void shouldFailIfActualIsNullWhenCheckingSize() {
    shouldFailIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).hasSize(2);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNullWhenCheckingSize() {
    shouldFailShowingDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).as("A Test").hasSize(2);
      }
    });
  }

  @Test public void shouldFailIfMapDoesNotHaveExpectedSize() {
    expectAssertionError("expected size:<2> but was:<1> for map:<{'key1'=1}>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1));
        new MapAssert(map).hasSize(2);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfMapDoesNotHaveExpectedSize() {
    expectAssertionError("[A Test] expected size:<2> but was:<1> for map:<{'key1'=1}>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1));
        new MapAssert(map).as("A Test").hasSize(2);
      }
    });
  }

  @Test public void shouldFailIfActualIsNullWhenCheckingIfEmpty() {
    shouldFailIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).isEmpty();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNullWhenCheckingIfEmpty() {
    shouldFailShowingDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).as("A Test").isEmpty();
      }
    });
  }

  @Test public void shouldFailIfMapIsNotEmptyAndExpectingEmpty() {
    expectAssertionError("expecting empty map, but was:<{'key1'=1}>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1));
        new MapAssert(map).isEmpty();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfMapIsNotEmptyAndExpectingEmpty() {
    expectAssertionError("[A Test] expecting empty map, but was:<{'key1'=1}>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1));
        new MapAssert(map).as("A Test").isEmpty();
      }
    });
  }

  @Test public void shouldFailIfMapIsEmptyAndExpectingNotEmpty() {
    expectAssertionError("expecting non-empty map, but it was empty").on(new CodeToTest() {
      public void run() {
        new MapAssert(EMPTY_MAP).isNotEmpty();
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfMapIsEmptyAndExpectingNotEmpty() {
    expectAssertionError("[A Test] expecting non-empty map, but it was empty").on(new CodeToTest() {
      public void run() {
        new MapAssert(EMPTY_MAP).as("A Test").isNotEmpty();
      }
    });
  }

  @Test public void shouldFailIfMapIsNotNullAndExpectingNull() {
    expectAssertionError("<{}> should be null").on(new CodeToTest() {
      public void run() {
        new MapAssert(EMPTY_MAP).isNull();
      }
    });
  }

  @Test public void shouldPassIfGivenMapIsEqualTo() {
    Map<Object, Object> map1 = map(entry("key1", 1), entry("key2", 2));
    Map<Object, Object> map2 = map(entry("key1", 1), entry("key2", 2));
    new MapAssert(map1).isEqualTo(map2);
  }

  @Test public void shouldPassIfGivenMapIsNotEqualTo() {
    Map<Object, Object> map1 = map(entry("key1", 1), entry("key2", 2));
    Map<Object, Object> map2 = map(entry("key1", 1), entry("key3", 3));
    new MapAssert(map1).isNotEqualTo(map2);
  }

  @Test public void shouldPassIfMapHasExpectedSize() {
    Map<Object, Object> map = map(entry("key1", 1), entry("key2", 2));
    new MapAssert(map).hasSize(2);
  }

  @Test public void shouldPassIfMapIsEmptyAndExpectingEmpty() {
    new MapAssert(new HashMap<Object, Object>()).isEmpty();
  }

  @Test public void shouldPassIfMapIsNotEmptyAndExpectingNotEmpty() {
    Map<Object, Object> map = map(entry("key1", 1));
    new MapAssert(map).isNotEmpty();
  }

  @Test public void shouldPassIfMapIsNullAndExpectingNull() {
    new MapAssert(null).isNull();
  }

  @Test public void shouldPassIfMapsAreSame() {
    Map<Object, Object> map = map(entry("key1", 1));
    new MapAssert(map).isSameAs(map);
  }

  @Test public void shouldFailIfActualIsNotSameAsExpectedAndExpectingSame() {
    expectAssertionError("expected same instance but found:<{'key1'=1}> and:<{}>").on(new CodeToTest() {
      public void run() {
        new MapAssert(map(entry("key1", 1))).isSameAs(EMPTY_MAP);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsNotSameAsExpectedAndExpectingSame() {
    expectAssertionError("[A Test] expected same instance but found:<{'key1'=1}> and:<{}>").on(new CodeToTest() {
      public void run() {
        new MapAssert(map(entry("key1", 1))).as("A Test").isSameAs(EMPTY_MAP);
      }
    });
  }

  @Test public void shouldFailIfActualIsSameAsExpectedAndExpectingNotSame() {
    expectAssertionError("given objects are same:<{'key1'=1}>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1));
        new MapAssert(map).isNotSameAs(map);
      }
    });
  }

  @Test public void shouldFailShowingDescriptionIfActualIsSameAsExpectedAndExpectingNotSame() {
    expectAssertionError("[A Test] given objects are same:<{'key1'=1}>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1));
        new MapAssert(map).as("A Test").isNotSameAs(map);
      }
    });
  }

  @Test public void shouldPassIfMapsAreNotSame() {
    new MapAssert(map(entry("key1", 1))).isNotSameAs(EMPTY_MAP);
  }

  @Test public void shouldPassIfArrayIsNullAndExpectingNullOrEmpty() {
    new MapAssert(null).isNullOrEmpty();
  }

  @Test public void shouldPassIfArrayIsEmptyAndExpectingNullOrEmpty() {
    new MapAssert(EMPTY_MAP).isNullOrEmpty();
  }

  @Test public void shouldFailIfArrayIsNotNullOrEmptyAndExpectingNullOrEmpty() {
    expectAssertionError("expecting a null or empty map, but was:<{'key1'=1}>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1));
        new MapAssert(map).isNullOrEmpty();
      }
    });
  }

  @Test public void shouldFailShowingDescritptionIfArrayIsNotNullOrEmptyAndExpectingNullOrEmpty() {
    expectAssertionError("[A Test] expecting a null or empty map, but was:<{'key1'=1}>").on(new CodeToTest() {
      public void run() {
        Map<Object, Object> map = map(entry("key1", 1));
        new MapAssert(map).as("A Test").isNullOrEmpty();
      }
    });
  }

  @Test public void shouldFailIfActualIsNullWhenReturningKeys() {
    shouldFailIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).keys();
      }
    });
  }

  @Test public void shouldReturnKeys() {
    Map<Object, Object> map = map(entry("key1", 1));
    assertEquals(new MapAssert(map).keys().actual, map.keySet());
  }
  
  @Test public void shouldFailIfActualIsNullWhenReturningValues() {
    shouldFailIfActualIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).values();
      }
    });
  }

  @Test public void shouldReturnValues() {
    Map<Object, Object> map = map(entry("key1", 1));
    assertEquals(new MapAssert(map).values().actual, map.values());
  }

  private void shouldFailIfActualIsNull(CodeToTest codeToTest) {
    expectAssertionError("expecting a non-null map, but it was null").on(codeToTest);
  }

  private void shouldFailShowingDescriptionIfActualIsNull(CodeToTest codeToTest) {
    expectAssertionError("[A Test] expecting a non-null map, but it was null").on(codeToTest);
  }

  private Map<Object, Object> map(Entry... entries) {
    Map<Object, Object> map = new LinkedHashMap<Object, Object>();
    for (Entry entry : entries)
      map.put(entry.key, entry.value);
    return map;
  }
}
