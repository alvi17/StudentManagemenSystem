package alvi17.studentmanagementsystem;

/**
 * Created by User on 6/26/2017.
 */

public class Payments {

    String payment_month,payment_type,payment_date;
    int student_id,amount;

    public Payments(int student_id,String payment_month,String payment_type,String payment_date,int amount)
    {
        this.student_id=student_id;
        this.payment_month=payment_month;
        this.payment_type=payment_type;
        this.payment_date=payment_date;
        this.amount=amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public void setPayment_month(String payment_month) {
        this.payment_month = payment_month;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getAmount() {
        return amount;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public String getPayment_month() {
        return payment_month;
    }

    public String getPayment_type() {
        return payment_type;
    }
}
