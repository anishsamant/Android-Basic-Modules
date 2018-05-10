package dbms.com.databaseapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ANISH on 26-08-2017.
 */

public class AddressDb extends SQLiteOpenHelper {
    public AddressDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ADDRESSBOOK(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Age INTEGER,Address TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS ADDRESSBOOK");
    }
}
