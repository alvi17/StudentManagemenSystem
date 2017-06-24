package alvi17.studentmanagementsystem.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import alvi17.studentmanagementsystem.Batch;

/**
 * Created by User on 6/16/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student_management_system.db";

    String batch_table="batch";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+batch_table+" (id integer primary key autoincrement,name text,college_name text,year text,starting_date date,farewell_date date)");
        db.execSQL("create table student (id integer primary key autoincrement,name text,admission_date date,batch_id integer, foreign key (batch_id) references batch (id))");

        db.execSQL("create table attendance (id integer primary key autoincrement,student_id integer,batch_id integer," +
                "class_date date,present_absent integer)");

        Log.e("DBHelper","Table created");

    }

    public void insertNewBatch(String name,String collegeName,String year,String starting_date,String farewell_date)
    {
//        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
//        Log.e("DBHelper","Time: "+time);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("college_name",collegeName);
        contentValues.put("year",year);
        contentValues.put("starting_date",starting_date);
        contentValues.put("farewell_date","");
        db.insert(batch_table,null,contentValues);
        db.close();
        Log.e("DBHelper","data inserted : batch: "+name+" time:"+starting_date);

    }


    public ArrayList<Batch> getAllBatches()
    {
        ArrayList<Batch> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+batch_table +" order by id desc", null );
        res.moveToFirst();

        while(!res.isAfterLast()){

            Batch batch=new Batch(res.getInt(res.getColumnIndex("id")),
                    res.getString(res.getColumnIndex("name")),res.getString(res.getColumnIndex("college_name")),res.getString(res.getColumnIndex("year")),
                    res.getString(res.getColumnIndex("starting_date")),res.getString(res.getColumnIndex("farewell_date"))
                    );
            array_list.add(batch);

            res.moveToNext();
        }

        res.close();
        Log.e("DBHelper","Batch List: "+ array_list.toString());
        db.close();
        return array_list;
    }



    public void createBatch_StudentTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table batch_student ()");
        db.close();
    }


    public void createStudentTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP table if exists student");
        db.execSQL("create table student (id integer primary key autoincrement,name text,college text," +
                "admission_date date,mobile_number text,batch_id integer, foreign key (batch_id) references batch (id))");
        Log.e("DBHelper","student table created");
        db.close();
    }

    public void insertIntoStudentTable(String name,String college,String batch_id,String admission_date,String mobile_number)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("college",college);
        contentValues.put("batch_id",batch_id);
        contentValues.put("admission_date",admission_date);
        contentValues.put("mobile_number",mobile_number);
        db.insert("student",null,contentValues);
        db.close();
        Log.e("DBHelper","data inserted : Student: "+name+" time:"+admission_date);

    }


    public ArrayList<String> getAllMobileForBatch(int batch_id)
    {
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select mobile_number from student where batch_id="+batch_id+" order by id desc", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndex("mobile_number")));
            res.moveToNext();

        }

        res.close();
        Log.e("DBHelper","Student mobile List: "+ array_list.toString());
        db.close();
        return array_list;
    }

    public ArrayList<String> getAllStudetsForBatch(int batch_id){
        ArrayList<String> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from student where batch_id="+batch_id+" order by name desc", null );
        res.moveToFirst();

        while(!res.isAfterLast()){

//            Batch batch=new Batch(res.getInt(res.getColumnIndex("id")),
//                    res.getString(res.getColumnIndex("name")),res.getString(res.getColumnIndex("college_name")),res.getString(res.getColumnIndex("year")),
//                    res.getString(res.getColumnIndex("starting_date")),res.getString(res.getColumnIndex("farewell_date"))
//            );
//            array_list.add(batch);
            array_list.add(res.getString(res.getColumnIndex("name")));
            res.moveToNext();

        }

        res.close();
        Log.e("DBHelper","Student List: "+ array_list.toString());
        db.close();
        return array_list;
    }

    public ArrayList<Integer> getAllStudetsIdsForBatch(int batch_id){
        ArrayList<Integer> array_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from student where batch_id="+batch_id+" order by name desc", null );
        res.moveToFirst();

        while(!res.isAfterLast()){

//            Batch batch=new Batch(res.getInt(res.getColumnIndex("id")),
//                    res.getString(res.getColumnIndex("name")),res.getString(res.getColumnIndex("college_name")),res.getString(res.getColumnIndex("year")),
//                    res.getString(res.getColumnIndex("starting_date")),res.getString(res.getColumnIndex("farewell_date"))
//            );
//            array_list.add(batch);
            array_list.add(res.getInt(res.getColumnIndex("id")));
            res.moveToNext();

        }

        res.close();
        Log.e("DBHelper","Student List: "+ array_list.toString());
        db.close();
        return array_list;
    }

    public void createAttendanceTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP table if exists attendance");
        db.execSQL("create table attendance (id integer primary key autoincrement,student_id integer,batch_id integer," +
                "class_date date,present_absent integer)");
        Log.e("DBHelper","attendance table created");
        db.close();
    }

    public void insertIntoAttendanceTable(int student_id,int batch_id,String class_date,int present_absent)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("student_id",student_id);
        contentValues.put("batch_id",batch_id);
        contentValues.put("class_date",class_date);
        contentValues.put("present_absent",present_absent);
        db.insertWithOnConflict("attendance",null,contentValues,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
        Log.e("DBHelper","Data inserted: "+student_id+" batch_id: "+batch_id+" date: "+class_date+" P/A: "+present_absent);
    }

    public ArrayList<String> getAttendanceOnbatchDate(int batch_id,String date)
    {
        return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS batch");
        db.execSQL("DROP TABLE IF EXISTS student_batch");
        db.execSQL("DROP TABLE IF EXISTS student");
        onCreate(db);
    }
}
