/*
 * Created on Oct 24, 2008
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
package org.fest.swing.driver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.exception.UnexpectedException;
import org.fest.swing.testing.TestWindow;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.fest.swing.core.RobotFixture.robotWithNewAwtHierarchy;
import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Tests for <code>{@link ComponentStateValidator}</code>.
 *
 * @author Alex Ruiz
 */
@Test
public class ComponentStateValidatorTest {

  private Robot robot;
  private TestWindow window;
  
  @BeforeMethod public void setUp() {
    robot = robotWithNewAwtHierarchy();
    window = TestWindow.createNewWindow(getClass());
  }
  
  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }
  
  public void shouldNotThrowErrorIfComponentIsEnabled() {
    execute(new GuiTask() {
      protected void executeInEDT() {
        ComponentStateValidator.validateIsEnabled(window);
      }
    });
    robot.waitForIdle();
  }
  
  public void shouldThrowErrorIfComponentIsDisabled() {
    try {
      execute(new GuiTask() {
        protected void executeInEDT() {
          window.setEnabled(false);
          ComponentStateValidator.validateIsEnabled(window);
        }
      });
      fail("Expecting exception");
    } catch (UnexpectedException unexpected) {
      Throwable cause = unexpected.getCause();
      assertThat(cause).isInstanceOf(ActionFailedException.class)
                       .message().contains("Expecting component")
                                 .contains("to be enabled");
    }
    robot.waitForIdle();
  }
}