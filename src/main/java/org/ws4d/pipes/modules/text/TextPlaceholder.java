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
package org.ws4d.pipes.modules.text;

import org.json.JSONObject;
import org.ws4d.pipes.module.PlaceholderModuleWorker;
import org.ws4d.pipes.module.annotation.InPort;
import org.ws4d.pipes.module.annotation.Module;
import org.ws4d.pipes.module.annotation.OutPort;
import org.ws4d.pipes.placeholder.annotation.Placeholder;

@Module(//
label = "Text place holder", //
icon = "", //
inPorts = { //
		@InPort(name = "question", label = "Question", isStatic = true, fieldType = "text/textarea") }, //
outPorts = { //
		@OutPort(name = "text", label = "Text")} //
)
@Placeholder(//
script = "placeholder/org.ws4d.pipes.packages.text.nocache.js" //
)
public class TextPlaceholder extends PlaceholderModuleWorker {

	@Override
	protected void sendData(JSONObject data) throws Exception {

		final String text = data.getString("text");

		setOutData("text", text);
	}
}
