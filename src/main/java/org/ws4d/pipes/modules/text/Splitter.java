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
import org.ws4d.pipes.module.annotation.InPort;
import org.ws4d.pipes.module.annotation.Module;
import org.ws4d.pipes.module.annotation.OutPort;

@Module(//
label = "Text splitter", //
icon = "splitter.png", //
inPorts = {//
        @InPort(name = "text", label = "Text to split", fieldType = "text/textarea"), //
        @InPort(name = "regex", label = "Regular expression", fieldType = "text/textarea") }, //
outPorts = {//
        @OutPort(name = "out", label = "Result") } //
)
public class Splitter extends DefaultModuleWorker {

    String regex = null;

    public void doWork() {

        PortValue textV = getWiredOrConfiguredValue("text");
        PortValue regexV = getWiredOrConfiguredValue("regex");

        if (!regexV.isNull() && regexV.isString()) {
            regex = regexV.getString();
        }

        if (textV.isNull()) {
            closeAllPorts();
            return;
        }

        if (textV.isString()) {
            String[] splits = textV.getString().split(regex);
            for (String s : splits) {
                setOutData("result", s);
            }
        }
    }

}
