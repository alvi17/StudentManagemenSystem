package alvi17.studentmanagementsystem.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import alvi17.studentmanagementsystem.DBHelper.DatabaseHelper;
import alvi17.studentmanagementsystem.R;
import alvi17.studentmanagementsystem.Util;

/**
 * Created by User on 6/22/2017.
 */

public class Batch_Send_SMS_Fragment extends Fragment implements View.OnClickListener{
    String batch_name,batch_college,batch_year,batch_starting_date,batch_farewell_date;
    int batch_id;

    TextView batch_name_textView,college_name_textView;
    EditText sms_messageEditText;
    Button send_smsButton;


    public Batch_Send_SMS_Fragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View rootView=inflater.inflate(R.layout.fragment_batch_send_sms,container,false);

        batch_name=getArguments().getString("Batch_Name");
        batch_college=getArguments().getString("Batch_College_Name");
        batch_year=getArguments().getString("Batch_Year");
        batch_starting_date=getArguments().getString("Batch_Start_Date");
        batch_farewell_date=getArguments().getString("Batch_Farewell_Date");
        batch_id=getArguments().getInt("Batch_Id",0);

        batch_name_textView=(TextView)rootView.findViewById(R.id.batch_main_batch_name);
        batch_name_textView.setText(batch_name);

        college_name_textView=(TextView)rootView.findViewById(R.id.batch_main_college_name);
        college_name_textView.setText(batch_college);

        sms_messageEditText=(EditText)rootView.findViewById(R.id.smseditText);
        send_smsButton=(Button)rootView.findViewById(R.id.send_sms_button);
        send_smsButton.setOnClickListener(this);

        return rootView;

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.send_sms_button:
                if(sms_messageEditText.getText().toString().trim().length()>0) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                    ArrayList<String> mobiles = databaseHelper.getAllMobileForBatch(batch_id);
                    Util.sendSms(mobiles, sms_messageEditText.getText().toString());
                    Util.showToast(getActivity(),"SMS Sent", Color.WHITE);
                }
                else
                {
                    Util.showToast(getActivity(),"Type a message", Color.WHITE);
                }

                break;
        }
    }


}
