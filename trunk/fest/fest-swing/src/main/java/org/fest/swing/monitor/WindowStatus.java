/*
 * Created on Oct 17, 2007
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
package org.fest.swing.monitor;

import java.awt.*;

import static java.lang.Math.max;

import static org.fest.swing.query.ComponentSizeQuery.sizeOf;
import static org.fest.swing.util.AWT.insetsFrom;

/**
 * Understands verification of the state of a window.
 *
 * @author Alex Ruiz
 */
class WindowStatus {

  private static final int ARBITRARY_EXTRA_VALUE = 20;

  private static int sign = 1;

  private final Windows windows;
  private final Robot robot;

  WindowStatus(Windows windows) {
    this(windows, createRobot());
  }

  WindowStatus(Windows windows, Robot robot) {
    this.windows = windows;
    this.robot = robot;
  }

  private static Robot createRobot() {
    try {
      return new Robot();
    } catch (AWTException e) {
      return null;
    }
  }

  Windows windows() { return windows; }

  /**
   * Checks whether the given window is ready for input.
   * @param w the given window.
   */
  void checkIfReady(Window w) {
    if (robot == null) return;
    // Must avoid frame borders, which are insensitive to mouse motion (at least on w32).
    WindowMetrics metrics = new WindowMetrics(w);
    mouseMove(w, metrics.center());
    if (windows.isShowingButNotReady(w) && isEmptyFrame(w))
      makeLargeEnoughToReceiveEvents(w, metrics);
  }

  private void mouseMove(Window w, Point point) {
    int x = point.x;
    int y = point.y;
    if (x == 0 || y == 0) return;
    robot.mouseMove(x, y);
    Dimension windowSize = sizeOf(w);
    if (windowSize.width > windowSize.height) robot.mouseMove(x + sign, y);
    else robot.mouseMove(x, y + sign);
    sign = -sign;
  }

  private boolean isEmptyFrame(Window w) {
    Insets insets = insetsFrom(w);
    Dimension windowSize = sizeOf(w);
    return insets.top + insets.bottom == windowSize.height || insets.left + insets.right == windowSize.width;
  }

  private void makeLargeEnoughToReceiveEvents(Window window, WindowMetrics metrics) {
    Dimension windowSize = sizeOf(window);
    int w = max(windowSize.width, proposedWidth(metrics));
    int h = max(windowSize.height, proposedHeight(metrics));
    window.setSize(new Dimension(w, h));
    // execute(setSizeTask(window, new Dimension(w, h)));
  }

  private int proposedWidth(WindowMetrics metrics) {
    return metrics.verticalInsets() + ARBITRARY_EXTRA_VALUE;
  }

  private int proposedHeight(WindowMetrics metrics) {
    return metrics.horizontalInsets() + ARBITRARY_EXTRA_VALUE;
  }

  static int sign() { return sign; }
}
