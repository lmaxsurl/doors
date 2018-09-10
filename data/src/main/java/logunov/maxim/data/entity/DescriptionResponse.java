package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

public class DescriptionResponse implements DataModel {

    @SerializedName("id")
    private int id;

    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }
}
