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

import org.ws4d.pipes.module.DefaultModuleWorker;
import org.ws4d.pipes.module.PortValue;
import org.ws4d.pipes.module.annotation.Module;
import org.ws4d.pipes.module.annotation.InPort;
import org.ws4d.pipes.module.annotation.OutPort;

@Module(//
label = "Append", //
icon = "format.png",//
inPorts = {//
		@InPort(name = "text", label = "Text", fieldType = "text/textarea"),//
		@InPort(name = "appendix", label = "Appendix", fieldType = "text/textarea") },//
outPorts = {//
		@OutPort(name = "result", label = "Text") }//
)
public class Append extends DefaultModuleWorker {

	private String appendix = null;
	private String text = null;

	public void doWork() {

		final PortValue textV = getWiredOrConfiguredValue("text");
		final PortValue appendixV = getWiredOrConfiguredValue("appendix");

		if (textV.isNull() && appendixV.isNull()) {
			closeAllPorts();
			return;
		}

		if (!textV.isNull()) {
			text = textV.getObject().toString();
		}

		if (!appendixV.isNull()) {
			appendix = appendixV.getObject().toString();
		}

		setOutData("result", new String(text + appendix));
	}
}
