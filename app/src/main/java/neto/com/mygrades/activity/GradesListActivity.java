package neto.com.mygrades.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import neto.com.mygrades.R;
import neto.com.mygrades.adapter.GradesAdapter;
import neto.com.mygrades.data.Grades;
import neto.com.mygrades.data.GradesDAO;
import neto.com.mygrades.dialog.ExitDialog;

public class GradesListActivity extends ListActivity implements AbsListView.MultiChoiceModeListener {

    private static final int REQ_CODE = 100;

    private GradesAdapter adapter;
    private GradesDAO gradesDAO;
    private ListView listView;
    private List<Grades> selected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_list);

        Spinner spi = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        spinner.add("Semestre 1");
        spinner.add("Semestre 2");
        spinner.add("Semestre 3");
        spinner.add("Semestre 4");
        spinner.add("Semestre 5");
        spinner.add("Semestre 6");
        spinner.add("Semestre 7");
        spinner.add("Semestre 8");

        spi.setAdapter(spinner);
        spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter = new GradesAdapter(this);
        setListAdapter(adapter);

        listView = getListView();

        listView.setMultiChoiceModeListener(this);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);

        gradesDAO = GradesDAO.getInstance(this);

        updateList();
    }

    public void updateList() {
        List<Grades> grades = gradesDAO.list();
        adapter.setItems(grades);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_addgrades, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(this, GradesEditActivity.class);
            startActivityForResult(intent, REQ_CODE);
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            updateList();
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), GradesEditActivity.class);
        intent.putExtra("grades", (Serializable) adapter.getItem(position));
        startActivityForResult(intent, REQ_CODE);
    }


    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        Grades s = (Grades) adapter.getItem(position);
        View view = listView.getChildAt(position);

        if (checked) {
            view.setBackgroundColor(Color.GREEN);
            selected.add(s);
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
            selected.remove(s);
        }
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        getMenuInflater().inflate(R.menu.action_mode, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        if (item.getItemId() == R.id.act_delete) {
            for (Grades s : selected) {
                gradesDAO.delete(s);
                updateList();
            }
            mode.finish();
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        int count = listView.getChildCount();

        for (int i = 0; i < count; i++) {
            View view = listView.getChildAt(i);
            view.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onBackPressed() {
        ExitDialog exitDialog = new ExitDialog();
        exitDialog.show(getFragmentManager(), "exit");
    }
}
