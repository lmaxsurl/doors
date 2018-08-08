package logunov.maxim.data.database;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.database.Cursor;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import logunov.maxim.data.entity.DescriptionResponse;

@SuppressWarnings("unchecked")
public class UserDAO_Impl implements UserDAO {
  private final RoomDatabase __db;

  public UserDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public Flowable<List<DescriptionResponse>> getAll() {
    final String _sql = "SELECT * FROM descriptions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"descriptions"}, new Callable<List<DescriptionResponse>>() {
      @Override
      public List<DescriptionResponse> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
          final List<DescriptionResponse> _result = new ArrayList<DescriptionResponse>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DescriptionResponse _item;
            _item = new DescriptionResponse();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item.setDescription(_tmpDescription);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
