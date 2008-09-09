/*
 * Created on Nov 1, 2007
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
 * Copyright @2007-2008 the original author or authors.
 */
package org.fest.swing.hierarchy;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.swing.edt.GuiQuery;
import org.fest.swing.testing.TestWindow;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.hierarchy.JFrameContentPaneQuery.contentPaneOf;
import static org.fest.swing.hierarchy.MDIFrame.showInTest;
import static org.fest.swing.testing.TestGroups.GUI;

/**
 * Tests for <code>{@link ParentFinder}</code>.
 *
 * @author Alex Ruiz
 */
@Test(groups = GUI)
public class ParentFinderTest {
  
  private ParentFinder finder;
  
  @BeforeMethod public void setUp() {
    finder = new ParentFinder();
  }

  public void shouldReturnParentOfComponent() {
    MyWindow window = MyWindow.createNew();
    window.display();
    try {
      assertThat(finder.parentOf(window.textField)).isSameAs(contentPaneOf(window));
    } finally {
      window.destroy();
    }
  }
  
  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    static MyWindow createNew() {
      return new MyWindow();
    }
    
    final JTextField textField = new JTextField();

    private MyWindow() {
      super(ParentFinderTest.class);
      addComponents(textField);
    }
  }
  
  public void shouldReturnParentOfInternalFrame() {
    MDIFrame frame = showInTest(getClass());
    JInternalFrame internalFrame = frame.internalFrame();
    try {
      assertThat(finder.parentOf(internalFrame)).isNotNull()
                                                .isSameAs(desktopPaneOf(internalFrame));
    } finally {
      frame.destroy();
    }
  }

  private static JDesktopPane desktopPaneOf(final JInternalFrame internalFrame) {
    return execute(new GuiQuery<JDesktopPane>() {
      protected JDesktopPane executeInEDT() {
        return internalFrame.getDesktopIcon().getDesktopPane();
      }
    });
  }
}
