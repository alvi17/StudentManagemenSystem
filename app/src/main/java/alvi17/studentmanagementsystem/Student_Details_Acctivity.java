package alvi17.studentmanagementsystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

/**
 * Created by User on 6/26/2017.
 */

public class Student_Details_Acctivity extends AppCompatActivity{

    Button student_payment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_details_layout);

        Log.e("Student details","ID: "+getIntent().getIntExtra("ID",0));

    }
}
