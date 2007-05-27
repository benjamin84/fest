/*
 * Created on Oct 20, 2006
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
package org.fest.swing.fixture;

import javax.swing.JLabel;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.swing.RobotFixture;


/**
 * Understands simulation of user events and state verification of a <code>{@link JLabel}</code>.
 *
 * @author Alex Ruiz
 */
public class JLabelFixture extends ComponentFixture<JLabel> implements TextDisplayFixture<JLabel> {
  
  /**
   * Creates a new </code>{@link JLabelFixture}</code>.
   * @param robot performs simulation of user events on a <code>JLabel</code>.
   * @param labelName the name of the label to find using the given <code>RobotFixture</code>.
   * @see org.fest.swing.ComponentFinder#findByName(String, Class)
   */
  public JLabelFixture(RobotFixture robot, String labelName) {
    super(robot, labelName, JLabel.class);
  }
  
  /**
   * Creates a new </code>{@link JLabelFixture}</code>.
   * @param robot performs simulation of user events on the given label.
   * @param target the target label.
   */
  public JLabelFixture(RobotFixture robot, JLabel target) {
    super(robot, target);
  }
  
  /** {@inheritDoc} */
  public final JLabelFixture requireText(String expected) {
    assertThat(text()).isEqualTo(expected);
    return this;
  }
  
  /** {@inheritDoc} */
  public final String text() { return target.getText(); }
  
  /** {@inheritDoc} */
  @Override public final JLabelFixture click() {
    return (JLabelFixture)super.click(); 
  }

  /** {@inheritDoc} */
  @Override public final JLabelFixture focus() {
    return (JLabelFixture)super.focus();
  }

  /** {@inheritDoc} */
  @Override public final JLabelFixture requireVisible() {
    return (JLabelFixture)super.requireVisible();
  }

  /** {@inheritDoc} */
  @Override public final JLabelFixture requireNotVisible() {
    return (JLabelFixture)super.requireNotVisible();
  }

  /** {@inheritDoc} */
  @Override public final JLabelFixture requireEnabled() {
    return (JLabelFixture)super.requireEnabled();
  }
  
  /** {@inheritDoc} */  
  @Override public final JLabelFixture requireDisabled() {
    return (JLabelFixture)super.requireDisabled();
  }
}
