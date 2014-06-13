/**
 * Copyright (C) 2014 PipesBox UG (haftungsbeschränkt) (elmar.zeeb@pipesbox.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ws4d.pipes.placeholders.text;

import org.ws4d.pipes.appkit.client.placeholder.BaseEntryPoint;

public class Placeholder extends BaseEntryPoint {

	// -------------------------------------------------------------------------
	// singleton
	// -------------------------------------------------------------------------

	private static Placeholder instance = null;

	public static Placeholder getInstance() {
		return instance;
	}

	// -------------------------------------------------------------------------
	// gwt entry point
	// -------------------------------------------------------------------------

	@Override
	public void onModuleLoad() {
		instance = this;

		super.onModuleLoad();

		addDialog(TextPlaceholder.ID, new TextPlaceholder());
	}

	public String getNamespace() {
		return "org.ws4d.pipes.packages.text";
	}

	public String getBaseUrl() {
		return "..";
	}
}
