package logunov.maxim.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import logunov.maxim.data.entity.DescriptionResponse;

/**
 * @see DescriptionResponse
 */
@Dao
public interface UserDAO {

    String TABLE_NAME = "descriptions";

    @Query("SELECT * FROM " + TABLE_NAME)
    Flowable<List<DescriptionResponse>> getAll();

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE id = :id")
    Flowable<DescriptionResponse> getDescription(int id);

}
