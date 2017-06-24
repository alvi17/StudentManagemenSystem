package alvi17.studentmanagementsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import alvi17.studentmanagementsystem.R;

/**
 * Created by User on 6/17/2017.
 */

public class Batch_Student_ListAdapter extends BaseAdapter{

    ArrayList<String> students;
    Context context;
    LayoutInflater layoutinflater;

    public Batch_Student_ListAdapter(Context context,ArrayList<String> students)
    {
        this.context=context;
        this.students=students;
        layoutinflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setStudents(Context context,ArrayList<String> students)
    {
        this.context=context;
        this.students=students;
        layoutinflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder listViewHolder;

        if(convertView == null){
            listViewHolder = new Batch_Student_ListAdapter.ViewHolder();
            convertView = layoutinflater.inflate(R.layout.batch_student_list_item, parent, false);

            listViewHolder.studentName=(TextView)convertView.findViewById(R.id.student_nametextView3);

            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (Batch_Student_ListAdapter.ViewHolder)convertView.getTag();
        }

        listViewHolder.studentName.setText(students.get(position));

        return convertView;
    }

    static class ViewHolder{
        TextView studentName;
    }
}
