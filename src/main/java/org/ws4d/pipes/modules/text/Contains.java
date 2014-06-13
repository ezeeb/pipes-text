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
label = "Text Contains", //
icon = "contains.png",//
inPorts = {//
        @InPort(name = "text", label = "Text", fieldType = "text/textarea"),//
        @InPort(name = "contains", label = "Contains", fieldType = "text/textarea") },//
outPorts = {//
        @OutPort(name = "result", label = "Result") }//
)
public class Contains extends DefaultModuleWorker {

    private String contains = null;

    public void doWork() {

        PortValue textV = getWiredOrConfiguredValue("text");
        PortValue containsV = getWiredOrConfiguredValue("contains");

        if (!containsV.isNull() && containsV.isString()) {
            contains = containsV.getString();
        }

        if (textV.isNull()) {
            closeAllPorts();
            return;
        }

        if (textV.isString() && textV.getString().contains(contains)) {
            setOutData("result", new Boolean(true));
        } else {
            setOutData("result", new Boolean(false));
        }
    }

}
