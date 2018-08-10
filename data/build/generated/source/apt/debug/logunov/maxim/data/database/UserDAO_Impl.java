package logunov.maxim.data.database;

import android.arch.persistence.room.RoomDatabase;
import java.lang.SuppressWarnings;

@SuppressWarnings("unchecked")
public class UserDAO_Impl implements UserDAO {
  private final RoomDatabase __db;

  public UserDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
  }
}
