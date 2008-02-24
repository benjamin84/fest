/*
 * Created on Feb 23, 2008
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
package org.fest.swing.task;

import java.awt.Frame;

import javax.swing.JFrame;

/**
 * Understands setting the extended state of a <code>{@link JFrame}</code>.
 *
 * @author Alex Ruiz
 */
public class SetExtendedStateTask implements Runnable {

  private final Frame f;
  private final int state;

  /**
   * Creates a new </code>{@link SetExtendedStateTask}</code>.
   * @param f the given <code>Frame</code>.
   * @param state the state to set.
   */
  public SetExtendedStateTask(Frame f, int state) {
    this.f = f;
    this.state = state;
  }

  /** Sets the extended state of this task's <code>{@link Frame}</code> */
  public void run() {
    f.setExtendedState(state);
  }

}
