/*
 * Created on Feb 24, 2008
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

import static org.fest.swing.core.RobotFixture.robotWithNewAwtHierarchy;
import static org.fest.swing.testing.ClickRecorder.attachTo;
import static org.fest.swing.testing.TestGroups.FUNCTIONAL;

import java.awt.Dimension;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.fest.swing.core.RobotFixture;
import org.fest.swing.testing.ClickRecorder;
import org.fest.swing.testing.TestFrame;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for <code>{@link JMenuItemDriver}</code>.
 *
 * @author Yvonne Wang
 */
@Test(groups = FUNCTIONAL)
public class JMenuItemDriverTest {

  private RobotFixture robot;
  private JMenuItem menuItem;
  private JMenuItemDriver driver;

  @BeforeMethod public void setUp() {
    robot = robotWithNewAwtHierarchy();
    driver = new JMenuItemDriver(robot);
    MyFrame frame = new MyFrame();
    menuItem = frame.menuNew;
    robot.showWindow(frame);
  }

  @AfterMethod public void tearDown() {
    robot.cleanUp();
  }

  @Test public void shouldSelectMenu() {
    ClickRecorder clickRecorder = attachTo(menuItem);
    driver.selectMenuItem(menuItem);
    clickRecorder.wasClicked();
  }


  private static class MyFrame extends TestFrame {
    private static final long serialVersionUID = 1L;

    final JMenu menuFile = new JMenu("File");
    final JMenuItem menuNew = new JMenuItem("New");

    boolean newMenuSelected;

    MyFrame() {
      super(JMenuItemDriverTest.class);
      setJMenuBar(new JMenuBar());
      menuFile.add(menuNew);
      getJMenuBar().add(menuFile);
      setPreferredSize(new Dimension(80, 60));
    }
  }
}