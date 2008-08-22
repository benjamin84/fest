/*
 * Created on Aug 8, 2008
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

import java.io.File;

import javax.swing.JFileChooser;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.fest.mocks.EasyMockTemplate;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;

/**
 * Tests for <code>{@link JFileChooserSetCurrentDirectoryTask}</code>.
 *
 * @author Yvonne Wang
 */
@Test public class JFileChooserSetCurrentDirectoryTaskTest {

  private JFileChooser fileChooser;
  private File dir;

  @BeforeMethod public void setUp() {
    fileChooser = createMock(JFileChooser.class);
    dir = createMock(File.class);
  }

  public void shouldReturnApproveButtonTextFromJFileChooser() {
    new EasyMockTemplate(fileChooser) {
      protected void expectations() {
        fileChooser.setCurrentDirectory(dir);
        expectLastCall().once();
      }

      protected void codeToTest() {
        JFileChooserSetCurrentDirectoryTask.setCurrentDirectoryTask(fileChooser, dir).executeInEDT();
      }
    }.run();
  }
}