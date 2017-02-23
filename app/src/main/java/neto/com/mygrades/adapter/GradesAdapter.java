package neto.com.mygrades.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import neto.com.mygrades.R;
import neto.com.mygrades.data.Grades;

/**
 * Created by Neto on 22/02/2017.
 */

public class GradesAdapter extends BaseAdapter {

    private Context context;
    private List<Grades> grades = new ArrayList<>();

    public GradesAdapter (Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return grades.size();
    }

    @Override
    public Object getItem(int position) {
        return grades.get(position);
    }

    @Override
    public long getItemId(int position) {
        return grades.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.adapter_listgrades, parent, false);
            holder = new ViewHolder();
            holder.txtDisciplina = (TextView) view.findViewById(R.id.txt_adapterdisc);
            holder.txtP1 = (TextView) view.findViewById(R.id.txt_adapterp1);
            holder.txtP2 = (TextView) view.findViewById(R.id.txt_adapterp2);
            holder.txtEdad = (TextView) view.findViewById(R.id.txt_adapteredad);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Grades grade = grades.get(position);

        holder.txtDisciplina.setText(grade.getNome());
        holder.txtP1.setText("Prova 1: " + String.format("%.2f", grade.getP1()));
        holder.txtP2.setText("Prova 2: " + String.format("%.2f", grade.getP2()));
        holder.txtEdad.setText("EDAD: " + String.format("%.2f", grade.getEdad()));

        return view;
    }

    public void setItems (List<Grades> grades) {
        this.grades = grades;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView txtDisciplina;
        TextView txtP1;
        TextView txtP2;
        TextView txtEdad;
    }
}
