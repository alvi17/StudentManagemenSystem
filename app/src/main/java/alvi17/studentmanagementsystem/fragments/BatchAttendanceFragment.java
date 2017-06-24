package alvi17.studentmanagementsystem.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import alvi17.studentmanagementsystem.DBHelper.DatabaseHelper;
import alvi17.studentmanagementsystem.R;
import alvi17.studentmanagementsystem.adapters.Batch_Attendance_ListAdapter;

/**
 * Created by User on 6/21/2017.
 */

public class BatchAttendanceFragment extends Fragment{

    public BatchAttendanceFragment()
    {
        super();
    }

    TextView batch_nameTextView,batch_college_nameTextView,attendance_date;
    String batch_name,batch_college,batch_year,batch_starting_date,batch_farewell_date;
    int batch_id;

    DatabaseHelper databaseHelper ;
    Batch_Attendance_ListAdapter batch_attendance_listAdapter;

    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_attendance,container,false);

        batch_name=getArguments().getString("Batch_Name");
        batch_college=getArguments().getString("Batch_College_Name");
        batch_year=getArguments().getString("Batch_Year");
        batch_starting_date=getArguments().getString("Batch_Start_Date");
        batch_farewell_date=getArguments().getString("Batch_Farewell_Date");
        batch_id=getArguments().getInt("Batch_Id",0);

        String time=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        attendance_date=(TextView)rootView.findViewById(R.id.batch_main_date_textView);
        attendance_date.setText(time);

        batch_nameTextView=(TextView)rootView.findViewById(R.id.batch_main_batch_name);
        batch_college_nameTextView=(TextView)rootView.findViewById(R.id.batch_main_college_name);
        batch_nameTextView.setText(batch_name);
        batch_college_nameTextView.setText(batch_college);

        listView=(ListView)rootView.findViewById(R.id.batch_student_attendance_listview);

        if(databaseHelper==null) {
            databaseHelper = new DatabaseHelper(getActivity());
        }

        setAttendanceList();

        return rootView;
    }

    public void setAttendanceList()
    {

        ArrayList<String> names=databaseHelper.getAllStudetsForBatch(batch_id);
        ArrayList<Integer> ids=databaseHelper.getAllStudetsIdsForBatch(batch_id);
        if(batch_attendance_listAdapter==null) {
            batch_attendance_listAdapter = new Batch_Attendance_ListAdapter(getActivity(), names,ids,batch_id,databaseHelper);
            listView.setAdapter(batch_attendance_listAdapter);
        }
        else{
            //batch_attendance_listAdapter.setStudents(getActivity(),names);
            batch_attendance_listAdapter.notifyDataSetChanged();
        }

    }
}
