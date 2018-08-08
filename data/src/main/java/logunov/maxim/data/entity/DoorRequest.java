package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

import logunov.maxim.domain.entity.DomainModel;

public class DoorRequest implements DomainModel {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("door_url")
    private String doorUrl;

    @SerializedName("glazed_door_url")
    private String glazedDoorUrl;

    @SerializedName("des_id")
    private String descriptionId;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDoorUrl(String doorUrl) {
        this.doorUrl = doorUrl;
    }

    public void setGlazedDoorUrl(String glazedDoorUrl) {
        this.glazedDoorUrl = glazedDoorUrl;
    }

    public void setDescriptionId(String descriptionId) {
        this.descriptionId = descriptionId;
    }
}
