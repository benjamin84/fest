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

import javax.swing.JInternalFrame;

import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that sends a <code>{@link JInternalFrame}</code> to the front. This task should be executed in the
 * event dispatch thread.
 *
 * @author Yvonne Wang
 */
class JInternalFrameMoveToFrontTask extends GuiTask {

  private final JInternalFrame internalFrame;

  static JInternalFrameMoveToFrontTask toFrontTask(JInternalFrame internalFrame) {
    return new JInternalFrameMoveToFrontTask(internalFrame);
  }
  
  private JInternalFrameMoveToFrontTask(JInternalFrame internalFrame) {
    this.internalFrame = internalFrame;
  }

  protected void executeInEDT() {
    internalFrame.toFront();
  }
}