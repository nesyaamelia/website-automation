package automation.pages.website.homepage.popupwhatsapp.api.request;

import automation.network.BaseRequest;
import automation.pages.website.homepage.popupwhatsapp.PopupWhatsappModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class EnrollUserRequest extends BaseRequest<EnrollUserRequest> {

    public int programId;
    public int relationId;
    public String relationType;
    public List<Integer> userId;

    @Override
    public EnrollUserRequest getBody() {
        return this;
    }

    public EnrollUserRequest(PopupWhatsappModel popupWhatsappModel) {
        this.programId = popupWhatsappModel.programId;
        this.relationId = popupWhatsappModel.programId;
        this.relationType = "program";
        this.userId = Collections.singletonList(popupWhatsappModel.userId);
    }
}