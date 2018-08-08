package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

public class DoorResponse {

    @SerializedName("title")
    private String title;

    @SerializedName("door_url")
    private String doorUrl;

    @SerializedName("glazed_door_url")
    private String glazedDoorUrl;

    @SerializedName("type")
    private String type;

    @SerializedName("des_id")
    private int descriptionId;

    public String getTitle() {
        return title;
    }

    public String getDoorUrl() {
        return doorUrl;
    }

    public String getGlazedDoorUrl() {
        return glazedDoorUrl;
    }

    public String getType() {
        return type;
    }

    public int getDescriptionId() {
        return descriptionId;
    }
}
