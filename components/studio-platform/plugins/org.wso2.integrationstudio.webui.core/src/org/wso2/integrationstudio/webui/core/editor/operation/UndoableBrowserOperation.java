/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.wso2.integrationstudio.webui.core.editor.operation;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.wso2.integrationstudio.webui.core.editor.AbstractWebBasedEditor;
import org.wso2.integrationstudio.webui.core.editor.Constants;
import org.wso2.integrationstudio.webui.core.exception.WebUIException;
import org.wso2.integrationstudio.webui.core.model.BrowserScript;
import org.wso2.integrationstudio.webui.core.util.ScriptFactory;

public class UndoableBrowserOperation extends AbstractOperation {

	protected String uniqueOperationID;
	protected AbstractWebBasedEditor browserEditor;
	
	public UndoableBrowserOperation(String label, String uniqueOperationID, AbstractWebBasedEditor browserEditor) {
		super(label);
		this.uniqueOperationID = uniqueOperationID;
		this.browserEditor = browserEditor;
	}

	@Override 	
	public IStatus execute(IProgressMonitor arg0, IAdaptable arg1)
			throws ExecutionException {
		return Status.OK_STATUS;
	}

	@Override
	public IStatus redo(IProgressMonitor arg0, IAdaptable arg1)
			throws ExecutionException {	
		BrowserScript undoTask = ScriptFactory.createFunctionCallScript(
				Constants.APP_REDO_FUNCTION,
				new String[] { uniqueOperationID });
		try {
			browserEditor.executeScriptOnBrowser(undoTask);
		} catch (WebUIException e) {
			throw new ExecutionException("Error while redoing operation:" + uniqueOperationID, e);
		}
		return Status.OK_STATUS;
	}

	@Override
	public IStatus undo(IProgressMonitor arg0, IAdaptable arg1)
			throws ExecutionException {
		BrowserScript undoTask = ScriptFactory.createFunctionCallScript(
				Constants.APP_UNDO_FUNCTION,
				new String[] { uniqueOperationID });
		try {
			browserEditor.executeScriptOnBrowser(undoTask);
		} catch (WebUIException e) {
			throw new ExecutionException("Error while undoing operation:" + uniqueOperationID, e);
		}
		return Status.OK_STATUS;
	}

	
}
