package alvi17.studentmanagementsystem;

/**
 * Created by User on 6/19/2017.
 */

public class Student {

    String name,college,batch,admission_date,mobile;

    public Student(String name,String college,String batch,String adimission_date,String mobile)
    {
        this.name=name;
        this.college=college;
        this.batch=batch;
        this.admission_date=adimission_date;
        this.mobile=mobile;

    }

    public String getName() {
        return name;
    }

    public String getAdmission_date() {
        return admission_date;
    }

    public String getBatch() {
        return batch;
    }

    public String getCollege() {
        return college;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdmission_date(String admission_date) {
        this.admission_date = admission_date;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
