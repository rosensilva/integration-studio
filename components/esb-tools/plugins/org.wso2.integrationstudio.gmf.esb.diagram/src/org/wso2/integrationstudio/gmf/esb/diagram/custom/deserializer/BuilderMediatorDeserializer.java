package org.wso2.integrationstudio.gmf.esb.diagram.custom.deserializer;

import java.util.List;

import org.apache.synapse.mediators.AbstractMediator;
import org.eclipse.core.runtime.Assert;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.ui.forms.editor.FormEditor;
import org.wso2.integrationstudio.gmf.esb.BuilderMediator;
import org.wso2.integrationstudio.gmf.esb.MessageBuilder;
import org.wso2.integrationstudio.gmf.esb.diagram.providers.EsbElementTypes;
import org.wso2.integrationstudio.gmf.esb.internal.persistence.custom.BuilderMediatorExt;

public class BuilderMediatorDeserializer extends AbstractEsbNodeDeserializer<AbstractMediator, BuilderMediator> {

    public BuilderMediator createNode(IGraphicalEditPart part, AbstractMediator mediator) {

        Assert.isTrue(mediator instanceof BuilderMediatorExt,
                "Unsupported mediator passed in for deserialization at " + this.getClass());
        BuilderMediatorExt builderMediatorExt = (BuilderMediatorExt) mediator;

        final BuilderMediator visualBuilderMediator = (BuilderMediator) DeserializerUtils.createNode(part,
                EsbElementTypes.BuilderMediator_3591);
        setElementToEdit(visualBuilderMediator);
        setCommonProperties(builderMediatorExt, visualBuilderMediator);

        if (builderMediatorExt.getMessageBuilderList() != null
                && !builderMediatorExt.getMessageBuilderList().isEmpty()) {

            List<MessageBuilder> msgbuilderlist = builderMediatorExt.getMessageBuilderList();

            for (MessageBuilder msgBuilder : msgbuilderlist) {

                executeAddValueCommand(visualBuilderMediator.getMessageBuilders(), msgBuilder, false);
            }
        }

        return visualBuilderMediator;
    }

    @Override
    public void createNode(FormEditor part, AbstractMediator object) {
        // TODO Auto-generated method stub
    }

}
