/*
 * Copyright 2015 Red Hat Inc. and/or its affiliates and other contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.qa.phaser;

import java.util.ArrayList;
import java.util.List;

public class PhaseTreeBuilder {

	private List<PhaseTreeNode> roots = new ArrayList<>();
	private PhaseTreeNode actualNodeLevel;
	private PhaseTreeNode actualNode;

	public PhaseTreeBuilder addRootPhase(Phase phase) {
		final PhaseTreeNode node = new PhaseTreeNode(phase);
		actualNodeLevel = null;
		actualNode = node;
		roots.add(node);
		return this;
	}

	public PhaseTreeBuilder addPhase(Phase phase) {
		if (actualNodeLevel == null) {
			return addRootPhase(phase);
		}
		final PhaseTreeNode node = new PhaseTreeNode(phase);
		actualNodeLevel.addChild(node);
		actualNode = node;
		return this;
	}

	public PhaseTreeBuilder next() {
		actualNodeLevel = actualNode;
		return this;
	}

	public PhaseTreeBuilder back() {
		if (actualNodeLevel != null) {
			actualNode = actualNodeLevel;
			actualNodeLevel = actualNodeLevel.getParent();
		}
		return this;
	}

	public PhaseTree build() {
		return new PhaseTree(roots);
	}
}
