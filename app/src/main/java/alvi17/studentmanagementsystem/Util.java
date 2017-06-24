package alvi17.studentmanagementsystem;

import android.app.Activity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 6/17/2017.
 */

public class Util {

    static  String TAG="Util";
    public static Toast toast;

    public static void showToast(Activity context, String message, int color) {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) context.findViewById(R.id.toast_layout_root));
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(context);
        TextView textView = (TextView) layout.findViewById(R.id.toast_textView);
        textView.setText(message);
        textView.setTextColor(color);
        toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 23);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static void sendSms(ArrayList<String> recipientPhoneNo, String smsMessage) {
        try {
            SmsManager smsManager = SmsManager.getDefault();

            for(int i=0;i<recipientPhoneNo.size();i++) {
                smsManager.sendTextMessage(recipientPhoneNo.get(i), null, smsMessage, null, null);
                Log.e(TAG, "Sms sent: " + smsMessage);
            }
        } catch (Exception ex) {
            Log.e(TAG, "sendSms " + ex + "");
        }
    }

}
