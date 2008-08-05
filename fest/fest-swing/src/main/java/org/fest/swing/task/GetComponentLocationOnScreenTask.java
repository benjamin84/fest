/*
 * Created on Jul 29, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008 the original author or authors.
 */
package org.fest.swing.task;

import java.awt.Component;
import java.awt.Point;

import org.fest.swing.core.GuiTask;

/**
 * Understands a task that returns the location of a <code>{@link Component}</code> on screen.
 *
 * @author Yvonne Wang
 */
public final class GetComponentLocationOnScreenTask extends GuiTask<Point> {

  private final Component component;

  /**
   * Returns the location of the given <code>{@link Component}</code> on screen. This action is executed in the event
   * dispatch thread.
   * @param component the given <code>Component</code>.
   * @return the location of the given <code>Component</code> on screen.
   */
  public static Point locationOnScreenOf(Component component) {
    return new GetComponentLocationOnScreenTask(component).run();
  }

  private GetComponentLocationOnScreenTask(Component component) {
    this.component = component;
  }

  /**
   * Returns the location in this task's <code>{@link Component}</code> on screen. This action is executed in the event
   * dispatch thread.
   * @return the location in this task's <code>Component</code> on screen.
   */
  protected Point executeInEDT() {
    return component.getLocationOnScreen();
  }
}