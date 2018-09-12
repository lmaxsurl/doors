package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

import logunov.maxim.domain.entity.DomainModel;

public class TypeResponse implements DataModel {

    @SerializedName("type")
    private String type;

    @SerializedName("id")
    private int id;

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
