/*
 * Created on Nov 2, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.swing.util;

import java.util.Map;

import static org.fest.reflect.Reflection.field;

/**
 * Understands utility methods related to reflection.
 *
 * @author Alex Ruiz
 */
public final class ReflectionUtils {

  @SuppressWarnings("unchecked") 
  public static <K, V> Map<K, V> mapField(String fieldName, Object target) {
    Map<?, ?> map = field(fieldName).ofType(Map.class).in(target).get();
    return (Map<K, V>) map;
  }
  
  private ReflectionUtils() {}
}