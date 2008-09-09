/*
 * Created on Jul 30, 2008
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

import javax.swing.JList;

import org.fest.swing.core.GuiQuery;

import static org.fest.swing.core.GuiActionRunner.execute;

/**
 * Understands an action, executed in the event dispatch thread, that returns the number of elements in a
 * <code>{@link JList}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
class JListElementCountQuery extends GuiQuery<Integer> {

  private final JList list;

  static int elementCountOf(JList list) {
    return execute(new JListElementCountQuery(list));
  }

  JListElementCountQuery(JList list) {
    this.list = list;
  }

  protected Integer executeInEDT() {
    return list.getModel().getSize();
  }
}