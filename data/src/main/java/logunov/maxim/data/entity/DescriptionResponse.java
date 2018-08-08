package logunov.maxim.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import logunov.maxim.data.database.UserDAO;
import logunov.maxim.domain.entity.DomainModel;

/**
 *@see UserDAO
 */

@Entity(tableName = UserDAO.TABLE_NAME)
public class DescriptionResponse implements DomainModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int id;

    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
