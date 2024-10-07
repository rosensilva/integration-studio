/*
 Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.integrationstudio.esb.synapse.unit.test.constant;

/**
 * Constants for unit test and mock services form views.
 */
public class Constants {
    private Constants() {
    }

    public static final String ESB_PROJECT_NATURE = "org.wso2.developerstudio.eclipse.esb.project.nature";
    public static final String CONNECTOR_PROJECT_NATURE = "org.wso2.developerstudio.eclipse.artifact.connector.project.nature";
    public static final String REGISTRY_PROJECT_NATURE = "org.wso2.developerstudio.eclipse.general.project.nature";
    public static final String ESB_PROJECT = "esb_project";
    public static final String REGISTRY_PROJECT = "registry_project";
    public static final String CONNECTOR_PROJECT = "connector_project";

    public static final String SYNAPSE_LEVEL = "synapse";
    public static final String AXIS2_LEVEL = "axis2";
    public static final String TRANSPORT_LEVEL = "transport";
    public static final String SYNAPSE_EXPRESSION = "$ctx:";
    public static final String AXIS2_EXPRESSION = "$axis2:";
    public static final String TRANSPORT_EXPRESSION = "$trp:";
    public static final String ASSERT_BODY_EXPRESSION = "$body";
    public static final String API_ARTIFACT = "api";
    public static final String SEQUENCE_ARTIFACTS = "sequences";
    public static final String ASSERT_EQUAL_TYPE = "AssertEquals";
    public static final String ASSERT_NOTNULL_TYPE = "AssertNotNull";

    public static final String SUPPORTIVE_ARTIFACT_TYPE = "SUPPORTIVE_ARTIFACTS";
    public static final String MOCK_SERVICE_TYPE = "MOCK_SERVICE";
    public static final String TEST_ARTIFACT_TYPE = "TEST_ARTIFACT";

    public static final String SYNAPSE_UNIT_TEST = "unit-test";
    public static final String ARTIFACTS = "artifacts";
    public static final String TEST_ARTIFACT = "test-artifact";
    public static final String ARTIFACT = "artifact";
    public static final String SUPPORTIVE_ARTIFACTS = "supportive-artifacts";
    public static final String REGISTRY_RESOURCES = "registry-resources";
    public static final String REGISTRY_RESOURCE = "registry-resource";
    public static final String CONNECTOR_RESOURCES = "connector-resources";
    public static final String CONNECTOR_RESOURCE = "connector-resource";
    public static final String REGISTRY_NAME = "file-name";
    public static final String REGISTRY_PATH = "registry-path";
    public static final String REGISTRY_MEDIA_TYPE = "media-type";

    public static final String TEST_CASES = "test-cases";
    public static final String TEST_CASE = "test-case";
    public static final String INPUT = "input";
    public static final String PAYLOAD = "payload";
    public static final String REQUEST_PATH = "request-path";
    public static final String REQUEST_METHOD = "request-method";
    public static final String PROPERTIES = "properties";
    public static final String PROPERTY = "property";
    public static final String NAME = "name";
    public static final String VALUE = "value";
    public static final String PROPERTY_SCOPE = "scope";

    public static final String ASSERTIONS = "assertions";
    public static final String ASSERTION = "assertion";
    public static final String ASSERT_EQUALS = "assertEquals";
    public static final String ASSERT_NOTNULL = "assertNotNull";
    public static final String ASSERT_ACTUAL = "actual";
    public static final String ASSERT_EXPECTED = "expected";
    public static final String ASSERT_MESSAGE = "message";

    public static final String MOCK_SERVICES = "mock-services";
    public static final String MOCK_SERVICE = "mock-service";
    public static final String SERVICE_NAME = "service-name";
    public static final String SERVICE_PORT = "port";
    public static final String SERVICE_CONTEXT = "context";
    public static final String SERVICE_RESOURCES = "resources";
    public static final String SERVICE_RESOURCE = "resource";
    public static final String SERVICE_RESOURCE_SUB_CONTEXT = "sub-context";
    public static final String SERVICE_RESOURCE_METHOD = "method";
    public static final String SERVICE_RESOURCE_REQUEST = "request";
    public static final String SERVICE_RESOURCE_HEADERS = "headers";
    public static final String SERVICE_RESOURCE_HEADER = "header";
    public static final String SERVICE_RESOURCE_RESPONSE = "response";
    public static final String SERVICE_RESOURCE_RESPONSE_CODE = "status-code";

    public static final String REGISTRY_RESOURCE_FILE = "artifact.xml";
    public static final String REGISTRY_ITEM = "item";
    public static final String REGISTRY_FILE = "file";
    public static final String REGISTRY_FILE_PATH = "path";
    public static final String REGISTRY_FILE_MEDIA_TYPE = "mediaType";
    public static final String REGISTRY_PROPERTIES = "properties";
    public static final String REGISTRY_PROPERTY = "property";
    public static final String REGISTRY_PROPERTY_KEY = "key";
    public static final String REGISTRY_PROPERTY_VALUE = "value";
    public static final String PROPPERTIES_FILE_SUFFIX = ".properties";

    public static final String PATH_PREFIX = "/";
    public static final String SPACE_STRING = " ";
    public static final String XML_PREFIX = "<";
    public static final String JSON_PREFIX = "{";
    public static final String XML_EXTENSION = "xml";
    public static final String ZIP_EXTENSION = "zip";
    public static final String TEST_FOLDER = "test";
    public static final String FILE_SEPERATOR = "file.separator";
    public static final String CONTEXT_VALIDATE_REGEX = ".*([/])\\1{1,}.*";
}
