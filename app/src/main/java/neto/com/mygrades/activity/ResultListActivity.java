package neto.com.mygrades.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import neto.com.mygrades.R;
import neto.com.mygrades.adapter.ResultCursorAdapter;
import neto.com.mygrades.data.DBHelper;

public class ResultListActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        listView = (ListView) findViewById(android.R.id.list);
        DBHelper helper = DBHelper.getInstance(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM grade", null);
        ResultCursorAdapter resultCursorAdapter = new ResultCursorAdapter(this, cursor);
        listView.setAdapter(resultCursorAdapter);
    }
}
