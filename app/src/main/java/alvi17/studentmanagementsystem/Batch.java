package alvi17.studentmanagementsystem;

/**
 * Created by User on 6/17/2017.
 */

public class Batch {

    public Batch()
    {

    }

    String name,year,starting_time,farewell_time,college_name;
    int id;

    public Batch(int id,String name,String collegeName,String year,String starting_time,String fare_well_time)
    {
        this.id=id;
        this.name=name;
        this.year=year;
        this.starting_time=starting_time;
        this.farewell_time=fare_well_time;
        this.college_name=collegeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollege_name() {
        return college_name;
    }

    public String getFarewell_time() {
        return farewell_time;
    }

    public String getName() {
        return name;
    }

    public String getStarting_time() {
        return starting_time;
    }

    public String getYear() {
        return year;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public void setFarewell_time(String farewell_time) {
        this.farewell_time = farewell_time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStarting_time(String starting_time) {
        this.starting_time = starting_time;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
