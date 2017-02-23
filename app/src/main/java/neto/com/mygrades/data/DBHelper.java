package neto.com.mygrades.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Neto on 22/02/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "gradesdb";
    public static final int DB_VERSION = 1;

    private static final String SQL_DROP = "DROP TABLE IF EXISTS " + GradesContract.TABLE_NAME;
    private static final String SQL_CREATE = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s DOUBLE NOT NULL, %s DOUBLE NOT NULL, %s DOUBLE NOT NULL)",
             GradesContract.TABLE_NAME, GradesContract.Columns._ID, GradesContract.Columns.DISCIPLINA, GradesContract.Columns.PROVA1, GradesContract.Columns.PROVA2, GradesContract.Columns.EDAD);

    private static DBHelper instance;

    public static DBHelper getInstance (Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    private DBHelper (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
