package org.fest.swing.driver;

import javax.swing.JTable;

import org.fest.swing.core.Condition;
import org.fest.swing.edt.GuiTask;

import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Understands a task that clears the selection in a <code>{@link JTable}</code>. This task is executed in the event 
 * dispatch thread.
 *
 * @author Alex Ruiz 
 */
class JTableClearSelectionTask {
  
  static void clearSelectionOf(final JTable table) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        table.clearSelection();
      }
    }, new Condition("JTable's selection is cleared") {
      public boolean test() {
        return table.getSelectedRowCount() == 0;
      }
    });
  }
}