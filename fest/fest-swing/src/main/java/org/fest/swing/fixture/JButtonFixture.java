/*
 * Created on Dec 16, 2006
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

import javax.swing.JButton;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.swing.core.MouseButton;
import org.fest.swing.core.RobotFixture;
import org.fest.swing.core.Timeout;
import org.fest.swing.exception.WaitTimedOutError;

/**
 * Understands simulation of user events on a <code>{@link JButton}</code> and verification of the state of such 
 * <code>{@link JButton}</code>.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JButtonFixture extends ComponentFixture<JButton> implements TextDisplayFixture {

  /**
   * Creates a new <code>{@link JButtonFixture}</code>.
   * @param robot performs simulation of user events on a <code>JButton</code>.
   * @param buttonName the name of the <code>JButton</code> to find using the given <code>RobotFixture</code>.
   * @throws org.fest.swing.exception.ComponentLookupException if a matching <code>JButton</code> could not be found.
   */
  public JButtonFixture(RobotFixture robot, String buttonName) {
    super(robot, buttonName, JButton.class);
  }
  
  /**
   * Creates a new <code>{@link JButtonFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JButton</code>.
   * @param target the <code>JButton</code> to be managed by this fixture.
   */
  public JButtonFixture(RobotFixture robot, JButton target) {
    super(robot, target);
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JButton}</code>.
   * @return this fixture.
   */
  public final JButtonFixture click() {
    return (JButtonFixture)doClick();
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JButton}</code>.
   * @param button the button to click.
   * @return this fixture.
   */
  public final JButtonFixture click(MouseButton button) {
    return (JButtonFixture)doClick(button);
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JButton}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   */
  public final JButtonFixture click(MouseClickInfo mouseClickInfo) {
    return (JButtonFixture)doClick(mouseClickInfo);
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JButton}</code>.
   * @return this fixture.
   */
  public final JButtonFixture rightClick() {
    return (JButtonFixture)doRightClick();
  }

  /**
   * Simulates a user doble-clicking this fixture's <code>{@link JButton}</code>.
   * @return this fixture.
   */
  public final JButtonFixture doubleClick() {
    return (JButtonFixture)doDoubleClick();
  }

  /**
   * Gives input focus to this fixture's <code>{@link JButton}</code>.
   * @return this fixture.
   */
  public final JButtonFixture focus() {
    return (JButtonFixture)doFocus();
  }

  /**
   * Asserts that the text of this fixture's <code>{@link JButton}</code> is equal to the specified <code>String</code>.
   * @param expected the text to match.
   * @return this fixture.
   * @throws AssertionError if the text of the target JButton is not equal to the given one.
   */
  public final JButtonFixture requireText(String expected) {
    assertThat(text()).as(formattedPropertyName("text")).isEqualTo(expected);
    return this;
  }

  /**
   * Returns the text of this fixture's <code>{@link JButton}</code>. 
   * @return the text of this fixture's <code>JButton</code>. 
   */
  public final String text() {
    return target.getText();
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's <code>{@link JButton}</code>.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @see java.awt.event.KeyEvent
   */
  public final JButtonFixture pressAndReleaseKeys(int... keyCodes) {
    return (JButtonFixture)doPressAndReleaseKeys(keyCodes);
  }
  
  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JButton}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @see java.awt.event.KeyEvent
   */
  public final JButtonFixture pressKey(int keyCode) {
    return (JButtonFixture)doPressKey(keyCode);
  }
  
  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JButton}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @see java.awt.event.KeyEvent
   */
  public final JButtonFixture releaseKey(int keyCode) {
    return (JButtonFixture)doReleaseKey(keyCode);
  }
  
  /**
   * Asserts that this fixture's <code>{@link JButton}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JButton</code> is not visible.
   */
  public final JButtonFixture requireVisible() {
    return (JButtonFixture)assertVisible();
  }

  /**
   * Asserts that this fixture's <code>{@link JButton}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JButton</code> is visible.
   */
  public final JButtonFixture requireNotVisible() {
    return (JButtonFixture)assertNotVisible();
  }

  /**
   * Asserts that this fixture's <code>{@link JButton}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JButton</code> is disabled.
   */
  public final JButtonFixture requireEnabled() {
    return (JButtonFixture)assertEnabled();
  }
  
  /**
   * Asserts that this fixture's <code>{@link JButton}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's <code>JButton</code> is never enabled.
   */
  public final JButtonFixture requireEnabled(Timeout timeout) {
    return (JButtonFixture)assertEnabled(timeout);
  }

  /**
   * Asserts that this fixture's <code>{@link JButton}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JButton</code> is enabled.
   */
  public final JButtonFixture requireDisabled() {
    return (JButtonFixture)assertDisabled();
  }
}