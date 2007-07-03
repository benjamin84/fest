/*
 * Created on Jul 3, 2007
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
package org.fest.swing.fixture;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import static org.fest.assertions.Assertions.assertThat;

import static org.fest.swing.RobotFixture.robotWithNewAwtHierarchy;

import org.fest.swing.Condition;
import org.fest.swing.RobotFixture;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Understands test methods for subclasses of <code>{@link ComponentFixture}</code>.
 * @param <T> the type of component tested by this test class. 
 *
 * @author Alex Ruiz
 */
public abstract class AbstractComponentFixtureTest<T extends Component> {

  protected static class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    final JButton button = new JButton("Some Button");
    
    MainWindow() {
      setLayout(new FlowLayout());
      button.setName("button");
      add(button);
    }
  }

  private RobotFixture robot;
  private MainWindow window;
  private ComponentFixture<T> fixture;

  @BeforeMethod public final void setUp() {
    robot = robotWithNewAwtHierarchy();
    window = new MainWindow();
    T target = createTarget();
    window.add(target);
    robot().showWindow(window);
    fixture = createFixture(); 
    giveFocusTo(window.button);
    assertThat(fixture.target).isSameAs(target);
    afterSetUp();
  }

  protected abstract T createTarget();
  protected abstract void afterSetUp();
  protected abstract ComponentFixture<T> createFixture();
  
  @Test public final void shouldClickComponent() {
    ComponentEvents events = ComponentEvents.attachTo(fixture.target);
    fixture.click();
    assertThat(events.clicked()).isTrue();
  }
  
  @Test public final void shouldGiveFocusToComponent() {
    T target = fixture.target;
    assertThat(target.hasFocus()).isFalse();
    fixture.focus();
    assertThat(target.hasFocus()).isTrue();
  }

  protected final void giveFocusTo(final Component c) {
    c.requestFocusInWindow();
    robot().wait(new Condition("component has focus") {
      public boolean test() {
        return c.hasFocus();
      }
    });
  }
  
  @Test public final void shouldPassIfComponentIsVisibleAndExpectingVisible() {
    fixture.target.setVisible(true);
    fixture.requireVisible();
  }
  
  @Test(expectedExceptions = AssertionError.class)
  public final void shouldFailIfComponentIsNotVisibleAndExpectingVisible() {
    fixture.target.setVisible(false);
    fixture.requireVisible();
  }
  
  @Test public final void shouldPassIfComponentIsNotVisibleAndExpectingNotVisible() {
    fixture.target.setVisible(false);
    fixture.requireNotVisible();
  }
  
  @Test(expectedExceptions = AssertionError.class)
  public final void shouldFailIfComponentIsVisibleAndExpectingNotVisible() {
    fixture.target.setVisible(true);
    fixture.requireNotVisible();
  }

  @AfterMethod public final void tearDown() {
    robot.cleanUp();
  }
  
  protected final RobotFixture robot() { return robot; }
  protected final ComponentFixture<T> fixture() { return fixture; }
}
