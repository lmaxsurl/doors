package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

public class DoorResponse implements DataModel {

    @SerializedName("title")
    private String title;

    @SerializedName("door_url")
    private String doorUrl;

    @SerializedName("hq_door_url")
    private String highQualityDoorUrl;

    @SerializedName("description_id")
    private int descriptionId;

    public String getTitle() {
        return title;
    }

    public String getDoorUrl() {
        return doorUrl;
    }

    public String getHighQualityDoorUrl() {
        return highQualityDoorUrl;
    }

    public int getDescriptionId() {
        return descriptionId;
    }
}
