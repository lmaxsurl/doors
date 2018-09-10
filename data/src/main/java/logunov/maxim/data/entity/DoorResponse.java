package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

public class DoorResponse implements DataModel {

    @SerializedName("title")
    private String title;

    @SerializedName("door_url")
    private String doorUrl;

    @SerializedName("hq_door_url")
    private String highQualityDoorUrl;

    @SerializedName("type")
    private String type;

    @SerializedName("description_id")
    private int description_id;

    public String getTitle() {
        return title;
    }

    public String getDoorUrl() {
        return doorUrl;
    }

    public String getType() {
        return type;
    }

    public String getHighQualityDoorUrl() {
        return highQualityDoorUrl;
    }

    public int getDescription_id() {
        return description_id;
    }
}
