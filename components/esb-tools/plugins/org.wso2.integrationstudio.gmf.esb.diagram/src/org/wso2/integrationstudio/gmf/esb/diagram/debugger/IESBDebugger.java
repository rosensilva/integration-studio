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
package org.wso2.integrationstudio.gmf.esb.diagram.debugger;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.wso2.integrationstudio.gmf.esb.diagram.debugger.exception.DebugPointMarkerNotFoundException;
import org.wso2.integrationstudio.gmf.esb.diagram.debugger.messages.IEventMessage;
import org.wso2.integrationstudio.gmf.esb.diagram.debugger.messages.IResponseMessage;
import org.wso2.integrationstudio.gmf.esb.diagram.debugger.messages.event.DebugPointEventMessage;

/**
 * ESB Debugger should implement this interface methods in the implementation
 *
 */
public interface IESBDebugger {

    /**
     * This method gets and handle Event messages from {@link IESBDebuggerInterface} came from ESB Server Debugger
     * 
     * @param iEventMessage
     */
    public void notifyEvent(IEventMessage iEventMessage);

    /**
     * This method gets and handle command response messages from {@link IESBDebuggerInterface} came from ESB Server
     * Debugger
     * 
     * @param iResponseMessage
     */
    public void notifyResponse(IResponseMessage iResponseMessage);

    /**
     * This method send event for {@link IESBDebugTarget} informing ESB Server
     * Started for Debugging
     */
    public void fireLoadedEvent();

    /**
     * This method send event for {@link IESBDebugTarget} informing ESB Server
     * Terminated
     * 
     * @throws IOException
     * @throws CoreException
     * @throws DebugPointMarkerNotFoundException
     */
    public void fireTerminatedEvent() throws IOException, DebugPointMarkerNotFoundException, CoreException;

    /**
     * This method send event for {@link IESBDebugTarget} informing ESB Server
     * Resumed
     */
    public void fireResumedEvent();

    public IESBDebuggerInterface getESBDebuggerInterface();

    /**
     * This method send event for {@link IESBDebugTarget} informing ESB Server
     * Suspended
     * 
     * @param event
     */
    public void fireSuspendedEvent(DebugPointEventMessage event);

}
