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
package org.fest.swing.query;

import java.awt.Component;
import java.awt.Point;

import org.fest.swing.edt.GuiQuery;

import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Understands an action, executed in the event dispatch thread, that returns the location of a
 * <code>{@link Component}</code> on screen.
 * @see Component#getLocationOnScreen()
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class ComponentLocationOnScreenQuery {

  /**
   * Returns the location of the given <code>{@link Component}</code> on screen. This action is executed in the event
   * dispatch thread.
   * @param component the given <code>Component</code>.
   * @return the location of the given <code>Component</code> on screen.
   * @see Component#getLocationOnScreen()
   */
  public static Point locationOnScreen(final Component component) {
    return execute(new GuiQuery<Point>() {
      protected Point executeInEDT() {
        return component.getLocationOnScreen();
      }
    });
  }

  private ComponentLocationOnScreenQuery() {}
}