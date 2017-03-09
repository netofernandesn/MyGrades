package neto.com.mygrades.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import neto.com.mygrades.R;
import neto.com.mygrades.data.GradesContract;

/**
 * Created by Neto on 06/03/2017.
 */

public class ResultCursorAdapter extends CursorAdapter {

    private SQLiteDatabase db;

    public ResultCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.adapter_result_cursor, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView disc = (TextView) view.findViewById(R.id.result_disc);
        TextView media = (TextView) view.findViewById(R.id.result_media);
        TextView result = (TextView) view.findViewById(R.id.result);

        if (cursor.getPosition() % 2 == 0) {
            view.setBackgroundColor(Color.LTGRAY);
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }

        String disciplina = cursor.getString(cursor.getColumnIndex(GradesContract.Columns.DISCIPLINA));
        double prova1 = cursor.getDouble(cursor.getColumnIndex(GradesContract.Columns.PROVA1));
        double prova2 = cursor.getDouble(cursor.getColumnIndex(GradesContract.Columns.PROVA2));
        double edad = cursor.getDouble(cursor.getColumnIndex(GradesContract.Columns.EDAD));
        disc.setText(disciplina);
        double res = (0.4 * prova1) + (0.6 * ((prova2 * 0.95) + (edad * 0.05)));
        media.setText("Média: " + String.valueOf(String.format("%.2f", res)));
        if (res >= 5) {
            result.setTextColor(Color.GREEN);
            result.setText("APROVADO");
        } else {
            result.setTextColor(Color.RED);
            result.setText("REPROVADO");
        }


    }
}



