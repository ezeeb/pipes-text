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

import org.ws4d.pipes.appkit.client.helper.LogHelper;
import org.ws4d.pipes.appkit.client.placeholder.PlaceholderDialog;
import org.ws4d.pipes.appkit.client.placeholder.PlaceholderManager;
import org.ws4d.pipes.appkit.client.placeholder.model.ModuleInstanceModel;
import org.ws4d.pipes.appkit.client.ui.TextField;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.History;

public class TextPlaceholder extends PlaceholderDialog {

	public static final String ID = "org.ws4d.pipes.modules.text.TextPlaceholder";
	
	private final TextField textField;
	private String nextHref = null;

	public TextPlaceholder() {
		super(ID);

		textField = new TextField("name");
		textField.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if ((textField.getText() != null)
						&& (textField.getText().length() > 0)) {
					TextPlaceholder.this.markReady();
				} else {
					TextPlaceholder.this.needsInput();
				}
			}
		});
		getContent().add(textField);

		next.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				event.preventDefault();

				JSONObject joData = new JSONObject();
				joData.put("text", new JSONString(textField.getText()));

				try {
					Placeholder
							.getInstance()
							.getPlaceholderManager()
							.setPlaceholder(getInstance(),
									getModuleInstance().getId(), joData);
				} catch (RequestException e) {
					LogHelper.log("TextPlaceholder", LogHelper.LOG_LEVEL_ERROR,
							"Error setting text", e);
				}

				if (nextHref != null) {
					History.newItem(nextHref);
				}
			}
		});
	}

	protected void loadText(String instance, JavaScriptObject moduleInstance)
			throws RequestException {
		ModuleInstanceModel module = getModule(moduleInstance);
		Placeholder
				.getInstance()
				.getPlaceholderManager()
				.getPlaceholder(instance, module.getId(),
						new PlaceholderManager.GetPlaceholderCB() {
							public void onSuccess(JSONObject data) {
								if (data.containsKey("text")) {
									textField.setText(data.get("text")
											.isString().stringValue(), false);
									markReady();
								}
							}

							public void onError(Throwable e) {
								LogHelper.log("TextPlaceholder",
										LogHelper.LOG_LEVEL_ERROR,
										"Error loading text", e);
							}
						});
	}

	@Override
	public void show(String instanceUri, JavaScriptObject moduleInstance,
			String previousHref, String cancelHref, String nextHref) {

		try {
			loadText(instanceUri, moduleInstance);
		} catch (Exception e) {
			LogHelper.log("TextPlaceholder", LogHelper.LOG_LEVEL_INFO,
					"Error loading text", e);
		}

		this.nextHref = nextHref;
		
		setQuestion(moduleInstance, "question", "question missing");

		super.show(instanceUri, moduleInstance, previousHref, cancelHref,
				nextHref);
	}

}
