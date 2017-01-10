package bwie.todayhistory.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 1.类的用途
 * 2.lishaocong
 * 3.Create on @ 2016/12/9.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String CREAT_NEWS="create table history("
            +"id integer primary key autoincrement,"
            +"db_mId text,"
            +"db_id text,"
            +"db_day text,"
            +"db_title text)";


    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
