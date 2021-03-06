/*
 * Copyright 2012-2015 WSO2, Inc. (http://wso2.com)
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

package org.wso2.integrationstudio.gmf.esb.diagram.custom;

import static org.wso2.integrationstudio.gmf.esb.diagram.custom.EditorUtils.SYNAPSE_CONFIG_DIR;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.wso2.integrationstudio.esb.core.utils.ESBMediaTypeConstants;
import org.wso2.integrationstudio.gmf.esb.ArtifactType;
import org.wso2.integrationstudio.gmf.esb.EsbPackage;
import org.wso2.integrationstudio.gmf.esb.FailoverEndPoint;
import org.wso2.integrationstudio.gmf.esb.LoadBalanceEndPoint;
import org.wso2.integrationstudio.gmf.esb.ParentEndPoint;
import org.wso2.integrationstudio.gmf.esb.RecipientListEndPoint;
import org.wso2.integrationstudio.gmf.esb.diagram.custom.deserializer.Deserializer;
import org.wso2.integrationstudio.gmf.esb.diagram.custom.utils.OpenEditorUtils;
import org.wso2.integrationstudio.gmf.esb.diagram.part.EsbEditorInput;
import org.wso2.integrationstudio.logging.core.IIntegrationStudioLog;
import org.wso2.integrationstudio.logging.core.Logger;
import org.wso2.integrationstudio.project.extensions.templates.ArtifactTemplate;
import org.wso2.integrationstudio.project.extensions.templates.ArtifactTemplateHandler;
import org.wso2.integrationstudio.platform.core.utils.CSProviderConstants;
import org.wso2.integrationstudio.platform.core.utils.IntegrationStudioProviderUtils;
import org.wso2.integrationstudio.platform.ui.editor.Openable;
import org.wso2.integrationstudio.platform.ui.startup.ESBGraphicalEditor;

public class ComplexFiguredAbstractEndpoint extends AbstractEndpoint {

    private static IIntegrationStudioLog log = Logger.getLog("org.wso2.integrationstudio.gmf.esb.diagram");

    public ComplexFiguredAbstractEndpoint(View view) {
        super(view);
    }

    protected NodeFigure createMainFigure() {
        return null;
    }

    public void openPage(String epName) {

        final EObject endpoint = (ParentEndPoint) ((org.eclipse.gmf.runtime.notation.impl.NodeImpl) getModel())
                .getElement();
        String name;

        long lDateTime = new Date().getTime();
        
        final String endpointName;
        if (epName == null || epName.isEmpty()) {
            endpointName = String.valueOf(lDateTime);
        } else {
            endpointName = epName;
        }

        if ((((ParentEndPoint) endpoint).getName() == null)
                || ((ParentEndPoint) endpoint).getName().trim().equals("")) {

            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    SetRequest setRequest = new SetRequest(getEditingDomain(), endpoint,
                            EsbPackage.eINSTANCE.getParentEndPoint_Name(), endpointName);
                    SetValueCommand operation = new SetValueCommand(setRequest) {

                        public boolean canUndo() {
                            return true;
                        }

                        public boolean canRedo() {
                            return true;
                        }
                    };

                    getEditDomain().getCommandStack().execute(new ICommandProxy(operation));
                }
            });

            name = endpointName;
            createFiles(endpoint, name, "complex_endpoint_" + name + ".esb_diagram",
                    "complex_endpoint_" + name + ".esb");
        } else {
            name = ((ParentEndPoint) endpoint).getName();
            createFiles(endpoint, name, "complex_endpoint_" + name + ".esb_diagram",
                    "complex_endpoint_" + name + ".esb");
        }
    }

    public String getName() {
        EObject tempEP = (ParentEndPoint) ((org.eclipse.gmf.runtime.notation.impl.NodeImpl) getModel())
                .getElement();
        return ((ParentEndPoint) tempEP).getName();
    }
    
    public boolean createFiles(EObject endpoint, String name, String fileURI1, String fileURI2) {
        /*
         * Resource diagram;
         * String basePath = "platform:/resource/" + currentProject.getName()
         * + "/" + COMPLEX_ENDPOINT_RESOURCE_DIR + "/";
         */
        IProject currentProject = getActiveProject();
        /*
         * IFile file = currentProject.getFile(COMPLEX_ENDPOINT_RESOURCE_DIR + "/"
         * + fileURI1);
         */

        IFile fileTobeOpened = null;
        // if (!file.exists()) {
        try {
            IFolder iFolder = currentProject.getFolder(SYNAPSE_CONFIG_DIR + "/endpoints/");
            if (!iFolder.exists()) {
                iFolder.create(IResource.NONE, true, null);
            }
            fileTobeOpened = iFolder.getFile(name + ".xml");

            if (fileTobeOpened.exists()) {
                OpenEditorUtils oeUtils = new OpenEditorUtils();
                oeUtils.openSeparateEditor(fileTobeOpened);
            } else {
                String path = fileTobeOpened.getParent().getFullPath() + "/";
                ArtifactTemplate complexEndpointArtifactTemplate = null;
                ArtifactTemplate[] endpointTemplates = getEndpointTemplates();
                if (endpoint instanceof FailoverEndPoint) {
                    complexEndpointArtifactTemplate = endpointTemplates[4];
                } else if (endpoint instanceof LoadBalanceEndPoint) {
                    complexEndpointArtifactTemplate = endpointTemplates[5];
                } else if (endpoint instanceof RecipientListEndPoint) {
                    complexEndpointArtifactTemplate = endpointTemplates[6];
                }
                fileTobeOpened.create(complexEndpointArtifactTemplate.getTemplateDataStream(), true,
                        new NullProgressMonitor());
                String source = org.wso2.integrationstudio.utils.file.FileUtils
                        .getContentAsString(complexEndpointArtifactTemplate.getTemplateDataStream());
                source = source.replaceAll("\\{", "<").replaceAll("\\}", ">");
                source = StringUtils.replace(source, "<ep.name>", name);
                source = MessageFormat.format(source, name);
                
                InputStream is = new ByteArrayInputStream(source.getBytes());
                if (fileTobeOpened.exists()) {
                    fileTobeOpened.setContents(is, true, true, null);
                } else {
                    fileTobeOpened.create(is, true, null);
                }
                
                Openable openable = ESBGraphicalEditor.getOpenable();
                openable.editorOpen(fileTobeOpened.getName(), ArtifactType.ENDPOINT.getLiteral(), path, source);
            }
        } catch (Exception e) {
            log.error("Cannot open file " + fileTobeOpened, e);
            return false;
        }
        String path = fileTobeOpened.getParent().getFullPath() + "/";

        /*
         * diagram = EsbDiagramEditorUtil.createResource(
         * URI.createURI(basePath + fileURI1),
         * URI.createURI(basePath + fileURI2),
         * new NullProgressMonitor(), "complex_endpoint", name, null);
         */
        /*
         * String source;
         * try {
         * source = FileUtils.readFileToString(fileTobeOpened.getLocation().toFile());
         * Openable openable = ESBGraphicalEditor.getOpenable();
         * openable.editorOpen(fileTobeOpened.getName(), ArtifactType.COMPLEX_ENDPOINT.getLiteral(), path, source);
         * } catch (IOException e1) {
         * log.error("Error while reading the file : "+fileTobeOpened, e1);
         * return false;
         * } catch (Exception e) {
         * log.error("Error while opening the file : "+fileTobeOpened, e);
         * return false;
         * }
         */
        /*
         * try {
         * EsbDiagramEditorUtil.openDiagram(diagram);
         * 
         * } catch (PartInitException e) {
         * log.error("Cannot init editor", e);
         * }
         */
        return true;
        // }

        /*
         * else {
         * IWorkbenchPage page = PlatformUI.getWorkbench()
         * .getActiveWorkbenchWindow().getActivePage();
         * IEditorDescriptor desc = PlatformUI.getWorkbench()
         * .getEditorRegistry().getDefaultEditor(file.getName());
         * try {
         * page.openEditor(new FileEditorInput(file), desc.getId());
         * } catch (PartInitException e) {
         * log.error("Cannot init editor", e);
         * }
         * return true;
         * }
         */
    }

    private ArtifactTemplate[] getEndpointTemplates() {
        Map<String, List<String>> filters = new HashMap<String, List<String>>();
        IntegrationStudioProviderUtils.addFilter(filters, CSProviderConstants.FILTER_MEDIA_TYPE,
                ESBMediaTypeConstants.MEDIA_TYPE_ENDPOINT);
        return ArtifactTemplateHandler.getArtifactTemplates(filters);
    }

    private IProject getActiveProject() {
        IEditorPart editorPart = null;
        IProject activeProject = null;
        IEditorReference editorReferences[] = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getEditorReferences();
        for (int i = 0; i < editorReferences.length; i++) {
            IEditorPart editor = editorReferences[i].getEditor(false);

            if (editor != null) {
                editorPart = editor.getSite().getWorkbenchWindow().getActivePage().getActiveEditor();
            }

            if (editorPart != null) {
                EsbEditorInput input = (EsbEditorInput) editorPart.getEditorInput();
                IFile file = input.getXmlResource();
                activeProject = file.getProject();
            }
        }
        return activeProject;
    }

}
