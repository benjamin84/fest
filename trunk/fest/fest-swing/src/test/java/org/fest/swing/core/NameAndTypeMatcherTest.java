/*
 * Created on Jan 10, 2008
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
 * Copyright @2008 the original author or authors.
 */
package org.fest.swing.core;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for <code>{@link NameAndTypeMatcher}</code>.
 *
 * @author Alex Ruiz
 */
public class NameAndTypeMatcherTest {

  private static class MyTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    private boolean showing;

    public void setShowing(boolean showing) {
      this.showing = showing;
    }
    
    @Override public boolean isShowing() {
      return showing;
    }
  }
  
  private MyTextField textField;
  
  @BeforeMethod public void setUp() {
    textField = new MyTextField();
    textField.setName("myTextField");
  }
  
  @Test public void shouldFindShowingComponentWithMatchingTypeAndName() {
    NameAndTypeMatcher matcher = new NameAndTypeMatcher("myTextField", JTextComponent.class, true);
    textField.setShowing(true);
    assertThat(matcher.matches(textField)).isTrue();
  }  
}