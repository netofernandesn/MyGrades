package neto.com.mygrades.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import neto.com.mygrades.R;
import neto.com.mygrades.data.Grades;
import neto.com.mygrades.data.GradesDAO;

public class GradesEditActivity extends Activity {

    private EditText edt_disciplina;
    private EditText edt_p1;
    private EditText edt_p2;
    private EditText edt_edad;
    private Grades grades;
    private GradesDAO gradesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades_edit);

        edt_disciplina = (EditText) findViewById(R.id.edt_disciplina);
        edt_p1 = (EditText) findViewById(R.id.edt_p1);
        edt_p2 = (EditText) findViewById(R.id.edt_p2);
        edt_edad = (EditText) findViewById(R.id.edt_edad);

        gradesDAO = GradesDAO.getInstance(this);

        grades = (Grades) getIntent().getSerializableExtra("grades");

        if (grades != null) {
            edt_disciplina.setText(grades.getNome());
            edt_p1.setText(String.valueOf(grades.getP1()));
            edt_p2.setText(String.valueOf(grades.getP2()));
            edt_edad.setText(String.valueOf(grades.getEdad()));
        }
    }

    public void process(View view) {

        try {
            String disciplina = edt_disciplina.getText().toString();
            double p1 = Double.parseDouble(edt_p1.getText().toString());
            double p2 = Double.parseDouble(edt_p2.getText().toString());
            double edad = Double.parseDouble(edt_edad.getText().toString());

            if (grades == null && !disciplina.trim().isEmpty()) {
                Grades grades = new Grades(disciplina, p1, p2, edad);
                gradesDAO.save(grades);
                Toast.makeText(this, "Disciplina gravada com sucesso! ", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else if (grades != null && !disciplina.trim().isEmpty()) {
                grades.setNome(disciplina);
                grades.setP1(p1);
                grades.setP2(p2);
                grades.setEdad(edad);
                gradesDAO.update(grades);
                Toast.makeText(this, "Disciplina atualizada com sucesso! ", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
        }


    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }


}
