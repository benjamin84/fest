/*
 * Created on Feb 2, 2008
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
package org.fest.swing.driver;

import static java.awt.BorderLayout.*;
import static java.lang.Math.max;
import static javax.swing.SwingConstants.HORIZONTAL;
import static org.fest.util.Arrays.format;
import static org.fest.util.Strings.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JToolBar;

/**
 * Understands a visible location on a <code>{@link JToolBar}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class JToolBarLocation {

  private static String[] VALID_CONSTRAINTS = { NORTH, EAST, SOUTH, WEST };

  /**
   * Returns the point where to grab the given <code>{@link JToolBar}</code>.
   * @param toolBar the target <code>JToolBar</code>.
   * @return the point where to grab the given <code>JToolBar</code>.
   */
  public Point pointToGrab(JToolBar toolBar) {
    Insets insets = toolBar.getInsets();
    if (max(max(max(insets.left, insets.top), insets.right), insets.bottom) == insets.left)
      return new Point(insets.left / 2, toolBar.getHeight() / 2);
    if (max(max(insets.top, insets.right), insets.bottom) == insets.top)
      return new Point(toolBar.getWidth() / 2, insets.top / 2);
    if (max(insets.right, insets.bottom) == insets.right)
      return new Point(toolBar.getWidth() - insets.right / 2, toolBar.getHeight() / 2);
    return new Point(toolBar.getWidth() / 2, toolBar.getHeight() - insets.bottom / 2);
  }

  /**
   * Returns the location where to dock the given <code>{@link JToolBar}</code>, at the given constraint position.
   * The constraint position must be one of the constants <code>{@link BorderLayout#NORTH NORTH}</code>,
   * <code>{@link BorderLayout#EAST EAST}</code>, <code>{@link BorderLayout#SOUTH SOUTH}</code>, or
   * <code>{@link BorderLayout#WEST WEST}</code>.
   * @param toolBar the target <code>JToolBar</code>.
   * @param dock the container where to dock the <code>JToolBar</code> to.
   * @param constraint the constraint position.
   * @return the location where to dock the given <code>JToolBar</code>.
   * @throws IllegalArgumentException if the constraint has an invalid value.
   */
  public Point dockLocation(JToolBar toolBar, Container dock, String constraint) {
    validate(constraint);
    Insets insets = dock.getInsets();
    // BasicToolBarUI prioritizes location N/E/W/S by proximity to the respective border. Close to top border is N, even
    // if close to the left or right border.
    int offset = isHorizontal(toolBar) ? toolBar.getHeight() : toolBar.getWidth();
    if (NORTH.equals(constraint))
      return new Point(dock.getWidth() / 2, insets.top);
    if (EAST.equals(constraint))
      return new Point(dock.getWidth() - insets.right - 1, verticalDockingVerticalCoordinate(dock, insets, offset));
    if (WEST.equals(constraint))
      return new Point(insets.left, verticalDockingVerticalCoordinate(dock, insets, offset));
    int x = dock.getWidth() / 2;
    // Make sure we don't get mistaken for EAST or WEST
    if (x < insets.left + offset)
      x = insets.left + offset;
    else if (x > dock.getWidth() - insets.right - offset - 1)
      x = dock.getWidth() - insets.right - offset - 1;
    return new Point(x, dock.getHeight() - insets.bottom - 1);
  }

  private void validate(String constraint) {
    for (String validConstraint : VALID_CONSTRAINTS)
      if (validConstraint.equals(constraint)) return;
    throw new IllegalArgumentException(
        concat(quote(constraint), " is not a valid constraint. Valid constraints are ", format(VALID_CONSTRAINTS)));
  }

  private boolean isHorizontal(JToolBar toolBar) {
    return toolBar.getOrientation() == HORIZONTAL;
  }

  private int verticalDockingVerticalCoordinate(Container dock, Insets insets, int offset) {
    int y = dock.getHeight() / 2;
    // Make sure we don't get mistaken for NORTH
    if (y < insets.top + offset) y = insets.top + offset;
    return y;
  }
}