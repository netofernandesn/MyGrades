package neto.com.mygrades.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neto on 22/02/2017.
 */

public class GradesDAO {

    private static GradesDAO instance;

    private SQLiteDatabase db;

    public static GradesDAO getInstance (Context context) {
        if (instance == null) {
            instance = new GradesDAO(context.getApplicationContext());
        }
        return instance;
    }

    private GradesDAO (Context context) {
        DBHelper dbHelper = DBHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Grades> list() {
        String[] columns = {
                GradesContract.Columns._ID,
                GradesContract.Columns.DISCIPLINA,
                GradesContract.Columns.PROVA1,
                GradesContract.Columns.PROVA2,
                GradesContract.Columns.EDAD,
        };

        List<Grades> grades = new ArrayList<>();

        try (Cursor c = db.query(GradesContract.TABLE_NAME, columns, null, null, null, null, GradesContract.Columns.DISCIPLINA)) {
            if (c.moveToFirst()) {
                do {
                    Grades g = GradesDAO.fromCursor(c);
                    grades.add(g);
                } while (c.moveToNext());
            }
            return grades;
        }
    }

    public static Grades fromCursor (Cursor c) {
        int id = c.getInt(c.getColumnIndex(GradesContract.Columns._ID));
        String disciplina = c.getString(c.getColumnIndex(GradesContract.Columns.DISCIPLINA));
        double prova1 = c.getDouble(c.getColumnIndex(GradesContract.Columns.PROVA1));
        double prova2 = c.getDouble(c.getColumnIndex(GradesContract.Columns.PROVA2));
        double edad = c.getDouble(c.getColumnIndex(GradesContract.Columns.EDAD));

        return new Grades(id, disciplina, prova1, prova2, edad);
    }

    public void save (Grades grades) {
        ContentValues values = new ContentValues();
        values.put(GradesContract.Columns.DISCIPLINA, grades.getNome());
        values.put(GradesContract.Columns.PROVA1, grades.getP1());
        values.put(GradesContract.Columns.PROVA2, grades.getP2());
        values.put(GradesContract.Columns.EDAD, grades.getEdad());
        long id = db.insert(GradesContract.TABLE_NAME, null, values);
        grades.setId((int) id);
    }

    public void update (Grades grades) {
        ContentValues values = new ContentValues();
        values.put(GradesContract.Columns.DISCIPLINA, grades.getNome());
        values.put(GradesContract.Columns.PROVA1, grades.getP1());
        values.put(GradesContract.Columns.PROVA2, grades.getP2());
        values.put(GradesContract.Columns.EDAD, grades.getEdad());
        db.update(GradesContract.TABLE_NAME, values, GradesContract.Columns._ID + " = ?", new String[] {String.valueOf(grades.getId())});
    }

    public void delete (Grades grades) {
        db.delete(GradesContract.TABLE_NAME, GradesContract.Columns._ID + " = ?", new String[] {String.valueOf(grades.getId())});
    }
}
