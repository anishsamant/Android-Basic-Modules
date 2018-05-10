package game.com.databasepractice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ANISH on 27-08-2017.
 */

public class MemberDb extends SQLiteOpenHelper {
    public MemberDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ACCOUNT(Id Integer AUTO INCREMENT,Name Text,Email Text PRIMARY KEY,Password Text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS ACCOUNT");
    }
}
