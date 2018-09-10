package logunov.maxim.data.entity;

import com.google.gson.annotations.SerializedName;

import logunov.maxim.domain.entity.DomainModel;

public class TypeResponse implements DataModel {

    @SerializedName("type")
    private String type;

    public String getType() {
        return type;
    }
}
