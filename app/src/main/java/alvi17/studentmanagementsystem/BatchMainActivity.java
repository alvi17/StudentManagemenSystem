package alvi17.studentmanagementsystem;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import alvi17.studentmanagementsystem.DBHelper.DatabaseHelper;
import alvi17.studentmanagementsystem.adapters.Batch_Student_ListAdapter;
import alvi17.studentmanagementsystem.fragments.BatchAttendanceFragment;
import alvi17.studentmanagementsystem.fragments.BatchHomeFragment;
import alvi17.studentmanagementsystem.fragments.Batch_Send_SMS_Fragment;

public class BatchMainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Bundle args;
                    BatchHomeFragment homeFragment=new BatchHomeFragment();
                    args=new Bundle();
                    args.putString("Batch_Name",batch_name);
                    args.putString("Batch_College_Name",batch_college);
                    args.putString("Batch_Year",batch_year);
                    args.putString("Batch_Start_Date",batch_starting_date);
                    args.putString("Batch_Farewell_Date",batch_farewell_date);
                    args.putInt("Batch_Id",batch_id);
                    homeFragment.setArguments(args);

                    getSupportFragmentManager().beginTransaction().replace(R.id.content,homeFragment).commit();
                    return true;
                case R.id.navigation_attendance:
                    Bundle args1;
                    BatchAttendanceFragment attendanceFragment=new BatchAttendanceFragment();
                    args1=new Bundle();
                    args1.putString("Batch_Name",batch_name);
                    args1.putString("Batch_College_Name",batch_college);
                    args1.putString("Batch_Year",batch_year);
                    args1.putString("Batch_Start_Date",batch_starting_date);
                    args1.putString("Batch_Farewell_Date",batch_farewell_date);
                    args1.putInt("Batch_Id",batch_id);
                    attendanceFragment.setArguments(args1);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,attendanceFragment).commit();
                    return true;
                case R.id.navigation_send_sms:
                    Bundle args2;
                    Batch_Send_SMS_Fragment smsFragment=new Batch_Send_SMS_Fragment();
                    args2=new Bundle();
                    args2.putString("Batch_Name",batch_name);
                    args2.putString("Batch_College_Name",batch_college);
                    args2.putString("Batch_Year",batch_year);
                    args2.putString("Batch_Start_Date",batch_starting_date);
                    args2.putString("Batch_Farewell_Date",batch_farewell_date);
                    args2.putInt("Batch_Id",batch_id);
                    smsFragment.setArguments(args2);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content,smsFragment).commit();
                    return true;
            }
            return false;
        }

    };


    String batch_name,batch_college,batch_year,batch_starting_date,batch_farewell_date;
    int batch_id;

    TextView batch_nameTextView,batch_college_nameTextView,batch_yearTextView,batch_starting_dateTextView,batch_farewell_dateTextView;

    ListView batch_studentListview;
    DatabaseHelper databaseHelper ;
    Batch_Student_ListAdapter batch_student_listAdapter;
    ArrayList<Batch> batches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_main);

        batch_name=getIntent().getStringExtra("Batch_Name");
        batch_college=getIntent().getStringExtra("Batch_College_Name");
        batch_year=getIntent().getStringExtra("Batch_Year");
        batch_starting_date=getIntent().getStringExtra("Batch_Start_Date");
        batch_farewell_date=getIntent().getStringExtra("Batch_Farewell_Date");
        batch_id=getIntent().getIntExtra("Batch_Id",0);


        if(savedInstanceState==null)
        {
            Bundle args;
            BatchHomeFragment homeFragment=new BatchHomeFragment();
            args=new Bundle();
            args.putString("Batch_Name",batch_name);
            args.putString("Batch_College_Name",batch_college);
            args.putString("Batch_Year",batch_year);
            args.putString("Batch_Start_Date",batch_starting_date);
            args.putString("Batch_Farewell_Date",batch_farewell_date);
            args.putInt("Batch_Id",batch_id);
            homeFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction().replace(R.id.content,homeFragment).commit();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //setBatchList();
    }





}
