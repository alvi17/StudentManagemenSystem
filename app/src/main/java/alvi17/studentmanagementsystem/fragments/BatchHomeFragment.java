package alvi17.studentmanagementsystem.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import alvi17.studentmanagementsystem.DBHelper.DatabaseHelper;
import alvi17.studentmanagementsystem.R;
import alvi17.studentmanagementsystem.Util;
import alvi17.studentmanagementsystem.adapters.Batch_Student_ListAdapter;

/**
 * Created by User on 6/21/2017.
 */

public class BatchHomeFragment extends android.support.v4.app.Fragment{

    String batch_name,batch_college,batch_year,batch_starting_date,batch_farewell_date;
    int batch_id;

    TextView batch_nameTextView,batch_college_nameTextView,batch_yearTextView,batch_starting_dateTextView,batch_farewell_dateTextView;

    ListView batch_studentListview;
    DatabaseHelper databaseHelper ;
    Batch_Student_ListAdapter batch_student_listAdapter;


    public BatchHomeFragment()
    {
        super();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_batch_home,container,false);
        batch_name=getArguments().getString("Batch_Name");
        batch_college=getArguments().getString("Batch_College_Name");
        batch_year=getArguments().getString("Batch_Year");
        batch_starting_date=getArguments().getString("Batch_Start_Date");
        batch_farewell_date=getArguments().getString("Batch_Farewell_Date");
        batch_id=getArguments().getInt("Batch_Id",0);

        batch_nameTextView=(TextView)rootView.findViewById(R.id.batch_main_batch_name);
        batch_college_nameTextView=(TextView)rootView.findViewById(R.id.batch_main_college_name);
        batch_nameTextView.setText(batch_name);
        batch_college_nameTextView.setText(batch_college);

        batch_studentListview=(ListView)rootView.findViewById(R.id.batch_student_listview);
        if(databaseHelper==null) {
            databaseHelper = new DatabaseHelper(getActivity());
        }
        setBatchList();


        return rootView;
    }

    public void setBatchList()
    {

        ArrayList<String> names=databaseHelper.getAllStudetsForBatch(batch_id);
        if(batch_student_listAdapter==null) {
            batch_student_listAdapter = new Batch_Student_ListAdapter(getActivity(), names);
            batch_studentListview.setAdapter(batch_student_listAdapter);
        }
        else{
            batch_student_listAdapter.setStudents(getActivity(),names);
            batch_student_listAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.batch_menu_main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_add_student:
                final Dialog dialog=new Dialog(getActivity(),R.style.AppTheme);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.add_student_to_batch);

                final EditText student_name=(EditText)dialog.findViewById(R.id.add_student_name_editText);
                final EditText student_college=(EditText)dialog.findViewById(R.id.add_student_college_editText2);
                final EditText student_batch=(EditText)dialog.findViewById(R.id.add_student_batch_editText3);
                student_batch.setText(batch_name);
                final EditText student_admission_date=(EditText)dialog.findViewById(R.id.add_student_admissiondae_editText4);
                String time=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
                student_admission_date.setText(time);
                final EditText student_mobile=(EditText)dialog.findViewById(R.id.add_student_mobile_editText2);

                Button add_studentButton=(Button)dialog.findViewById(R.id.student_add_button);
                add_studentButton.setOnClickListener(
                        new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseHelper.insertIntoStudentTable(student_name.getText().toString(),student_college.getText().toString(),
                                batch_id+"", student_admission_date.getText().toString(),student_mobile.getText().toString());
                        Util.showToast(getActivity(),"Student added in "+student_batch.getText().toString()+" batch.", Color.WHITE);
                        dialog.dismiss();
                        setBatchList();
                    }
                });


                dialog.setCancelable(true);
                dialog.show();

                return true;

        }


        return super.onOptionsItemSelected(item);
    }
}
