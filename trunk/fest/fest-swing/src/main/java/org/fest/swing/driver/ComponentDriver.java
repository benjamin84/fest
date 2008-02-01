/*
 * Created on Jan 26, 2008
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

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Point;

import javax.accessibility.AccessibleAction;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import org.fest.swing.core.RobotFixture;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.util.TimeoutWatch;

import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.core.Pause.pause;
import static org.fest.swing.core.Settings.*;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.util.Platform.*;
import static org.fest.swing.util.TimeoutWatch.startWatchWithTimeoutOf;
import static org.fest.util.Strings.concat;

/**
 * Understands simulation of user input on a <code>{@link Component}</code>. This class is intended for internal use
 * only.
 *
 * @author Alex Ruiz
 */
public abstract class ComponentDriver {

  /** Number of pixels traversed before a drag starts. */
  public static final int DRAG_THRESHOLD = IS_WINDOWS || IS_MACINTOSH ? 10 : 16;

  protected final RobotFixture robot;

  /**
   * Creates a new </code>{@link ComponentDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public ComponentDriver(RobotFixture robot) {
    this.robot = robot;
  }

  /**
   * Performs a drag action at the given point.
   * @param target the target component.
   * @param where the point where to start the drag action.
   */
  protected void drag(Component target, Point where) {
    robot.mousePress(target, where, LEFT_BUTTON);
    if (dragDelay() > delayBetweenEvents()) pause(dragDelay());
    mouseMove(target, where.x, where.y);
    robot.waitForIdle();
  }

  private void mouseMove(Component target, int x, int y) {
    if (IS_WINDOWS || IS_MACINTOSH) {
      mouseMoveOnWindowsAndMacintosh(target, x, y);
      return;
    }
    mouseMove(target,
        point(x + DRAG_THRESHOLD / 2, y + DRAG_THRESHOLD / 2),
        point(x + DRAG_THRESHOLD, y + DRAG_THRESHOLD),
        point(x + DRAG_THRESHOLD / 2, y + DRAG_THRESHOLD / 2),
        point(x, y)
    );
  }

  private void mouseMoveOnWindowsAndMacintosh(Component target, int x, int y) {
    int dx = dragThreshold(x, target.getWidth());
    int dy = dragThreshold(y, target.getHeight());
    if (dx == 0 && dy == 0) dx = DRAG_THRESHOLD;
    mouseMove(target,
        point(x + dx / 4, y + dy / 4),
        point(x + dx / 2, y + dy / 2),
        point(x + dx, y + dy),
        point(x + dx + 1, y + dy)
    );
  }

  private int dragThreshold(int coordinate, int dimension) {
    return coordinate + DRAG_THRESHOLD < dimension ? DRAG_THRESHOLD : 0;
  }

  private Point point(int x, int y) { return new Point(x, y); }

  /**
   * Ends a drag operation, releasing the mouse button over the given target location.
   * <p>
   * This method is tuned for native drag/drop operations, so if you get odd behavior, you might try using a simple
   * <code>{@link RobotFixture#mouseMove(Component, int, int)}</code> and
   * <code>{@link RobotFixture#releaseMouseButtons()}</code>.
   * @param target the target component.
   * @param where the point where the drag operation ends.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  protected void drop(Component target, Point where) {
    dragOver(target, where);
    TimeoutWatch watch = startWatchWithTimeoutOf(eventPostingDelay() * 4);
    while (!robot.isDragging()) {
      if (watch.isTimeOut()) throw actionFailure("There is no drag in effect");
      pause();
    }
    if (dropDelay() > delayBetweenEvents()) pause(dropDelay() - delayBetweenEvents());
    robot.releaseMouseButtons();
    robot.waitForIdle();
  }

  /**
   * Move the mouse appropriately to get from the source to the destination. Enter/exit events will be generated where
   * appropriate.
   * @param target the target component.
   * @param where the point to drag over.
   */
  protected void dragOver(Component target, Point where) {
    dragOver(target, where.x, where.y);
  }

  private void dragOver(Component target, int x, int y) {
    robot.mouseMove(target, x - 4, y);
    robot.mouseMove(target, x, y);
  }

  private void mouseMove(Component target, Point...points) {
    for (Point p : points) robot.mouseMove(target, p.x, p.y);
  }

  /**
   * Indicates whether it is possible for the user to resize the given component.
   * @param c the target component.
   * @return <code>true</code> if it is possible for the user to resize the given component, <code>false</code>
   * otherwise.
   */
  protected final boolean isUserResizable(Component c) {
    if (c instanceof Dialog) return ((Dialog)c).isResizable();
    if (c instanceof Frame) return ((Frame)c).isResizable();
    return canResizeWindows(); // most X11 window managers allow arbitrary resizing
  }

  /**
   * Indicates whether it is possible for the user to move the given component.
   * @param c the target component.
   * @return <code>true</code> if it is possible for the user to move the given component, <code>false</code>
   * otherwise.
   */
  protected final boolean isUserMovable(Component c) {
    return c instanceof Dialog || c instanceof Frame || canMoveWindows();
  }

  /**
   * Performs the specified <code>{@link AccessibleAction}</code> in the given <code>{@link Component}</code>'s event
   * queue.
   * @param c the given <code>Component</code>.
   * @throws ActionFailedException if <code>action</code> is <code>null</code> or empty. 
   */
  protected final void performAccessibleActionOf(Component c) {
    final AccessibleAction action = c.getAccessibleContext().getAccessibleAction();
    if (action == null || action.getAccessibleActionCount() == 0)
      throw actionFailure(concat("Unable to perform accessible action for ", format(c)));
    robot.invokeLater(c, new Runnable() {
      public void run() {
        action.doAccessibleAction(0);
      }
    });
  }

  /**
   * Wait the given number of milliseconds for the <code>{@link Component}</code> to be showing and ready. Returns
   * <code>false</code> if the operation times out.
   * @param c the given <code>Component</code>.
   * @param timeout the time in milliseconds to wait for the <code>Component</code> to be showing and ready.
   * @return <code>true</code> if the <code>Component</code> is showing and ready, <code>false</code> otherwise.
   */
  protected boolean waitForShowing(Component c, long timeout) {
    if (robot.isReadyForInput(c)) return true;
    TimeoutWatch watch = startWatchWithTimeoutOf(timeout);
    while (!robot.isReadyForInput(c)) {
      if (c instanceof JPopupMenu) {
        // move the mouse over the parent menu item to ensure the sub-menu shows
        Component invoker = ((JPopupMenu)c).getInvoker();
        if (invoker instanceof JMenu) robot.jitter(invoker);
      }
      if (watch.isTimeOut()) return false;
      pause();
    }
    return true;
  }
}
