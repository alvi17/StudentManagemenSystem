package alvi17.studentmanagementsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import alvi17.studentmanagementsystem.DBHelper.DatabaseHelper;
import alvi17.studentmanagementsystem.R;

/**
 * Created by User on 6/21/2017.
 */

public class Batch_Attendance_ListAdapter extends BaseAdapter{

    ArrayList<String> names;
    ArrayList<Integer> student_ids;
    int batch_id;
    Context context;
    LayoutInflater layoutinflater;
    Integer[] attendance;
    DatabaseHelper databaseHelper;
    String time;

    public Batch_Attendance_ListAdapter(Context context,ArrayList<String> names,ArrayList<Integer> student_ids,int batch_id,DatabaseHelper databaseHelper)
    {
        this.names=names;
        this.student_ids=student_ids;
        this.batch_id=batch_id;
        this.context=context;
        layoutinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        attendance=new Integer[names.size()];
        for(int i=0;i<names.size();i++)
        {
            attendance[i]=0;
        }
        this.databaseHelper=databaseHelper;
        time=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    @Override
    public int getCount() {
        return names.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder listViewHolder;

        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.batch_attendance_list_item, parent, false);

            listViewHolder.student_names=(TextView)convertView.findViewById(R.id.attendance_list_item_nametextView8);
            listViewHolder.presentImageView=(ImageView)convertView.findViewById(R.id.batch_present);
            listViewHolder.absentImageView=(ImageView)convertView.findViewById(R.id.batch_absent);
            listViewHolder.linearLayout=(LinearLayout)convertView.findViewById(R.id.batch_attendance_list_item_mainlayout);

            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }

        listViewHolder.presentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendance[position]=1;
                //listViewHolder.linearLayout.setBackgroundColor(Color.GREEN);
                listViewHolder.presentImageView.setImageResource(R.drawable.ic_check_green_24dp);
                listViewHolder.absentImageView.setImageResource(R.drawable.ic_close_black_24dp);
                databaseHelper.insertIntoAttendanceTable(student_ids.get(position),batch_id,time,1);
            }
        });

        listViewHolder.absentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendance[position]=0;
                //listViewHolder.linearLayout.setBackgroundColor(Color.RED);
                listViewHolder.presentImageView.setImageResource(R.drawable.ic_check_black_24dp);
                listViewHolder.absentImageView.setImageResource(R.drawable.ic_close_green_24dp);
                databaseHelper.insertIntoAttendanceTable(student_ids.get(position),batch_id,time,0);
            }
        });

        listViewHolder.student_names.setText(names.get(position));


        return convertView;
    }


    static class ViewHolder{
        TextView student_names;
        ImageView presentImageView,absentImageView;
        LinearLayout linearLayout;

    }
}
