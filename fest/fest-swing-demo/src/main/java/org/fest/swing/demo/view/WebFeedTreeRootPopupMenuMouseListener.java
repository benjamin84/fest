/*
 * Created on Apr 23, 2008
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
package org.fest.swing.demo.view;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.tree.TreePath;

/**
 * Understands a mouse listener that shows a pop-up menu on the root node of a <code>{@link WebFeedTree}</code>.
 *
 * @author Alex Ruiz
 */
class WebFeedTreeRootPopupMenuMouseListener {

  static void attachTo(WebFeedTree tree) {
    JPopupMenu popupMenu = new JPopupMenu();
    popupMenu.add(new AddWebFeedOrFolderAction(tree.mainFrame()));
    tree.addMouseListener(new MouseListener(popupMenu));
  }

  private WebFeedTreeRootPopupMenuMouseListener() {}

  private static class MouseListener extends MouseAdapter {

    private final JPopupMenu popupMenu;

    MouseListener(JPopupMenu popupMenu) {
      this.popupMenu = popupMenu;
    }

    @Override public void mouseReleased(MouseEvent e) {
      if (!e.isPopupTrigger()) return;
      Component c = e.getComponent();
      if (!(c instanceof WebFeedTree)) return;
      WebFeedTree tree = (WebFeedTree)c;
      int x = e.getX();
      int y = e.getY();
      int row = tree.getRowForLocation(x, y);
      TreePath path = tree.getPathForRow(row);
      if (path == null) return;
      Object value = path.getLastPathComponent();
      if (!tree.getModel().getRoot().equals(value)) return;
      popupMenu.show(tree, x, y);
    }
  }
}
