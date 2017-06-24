package alvi17.studentmanagementsystem;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

import alvi17.studentmanagementsystem.DBHelper.DatabaseHelper;
import alvi17.studentmanagementsystem.adapters.BatchGridAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    GridView batchGridView;
    BatchGridAdapter batchGridAdapter;

    DatabaseHelper databaseHelper ;
    ArrayList<Batch> batches;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        databaseHelper = new DatabaseHelper(MainActivity.this);
        //databaseHelper.createAttendanceTable();
        batchGridView=(GridView)findViewById(R.id.batchgridView);
        batches=databaseHelper.getAllBatches();
        batchGridAdapter=new BatchGridAdapter(MainActivity.this,batches);
        batchGridView.setAdapter(batchGridAdapter);

        batchGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,BatchMainActivity.class);
                intent.putExtra("Batch_Id",batches.get(position).getId());
                intent.putExtra("Batch_Name",batches.get(position).getName());
                intent.putExtra("Batch_Year",batches.get(position).getYear());
                intent.putExtra("Batch_College_Name",batches.get(position).getCollege_name());
                intent.putExtra("Batch_Start_Date",batches.get(position).getStarting_time());
                intent.putExtra("Batch_Farewell_Date",batches.get(position).getFarewell_time());
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            final Dialog dialog=new Dialog(MainActivity.this,R.style.AppTheme);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.add_new_batch_dialog);

            final EditText batch_name=(EditText)dialog.findViewById(R.id.batchname_editText);
            final EditText batch_college=(EditText)dialog.findViewById(R.id.collegename_editText);
            final EditText batch_year=(EditText)dialog.findViewById(R.id.batchyear_editText);
            final EditText batch_start_date=(EditText)dialog.findViewById(R.id.batchstart_editText);


            Button add_new =(Button)dialog.findViewById(R.id.add_new_batch_button);
            add_new.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(batch_name.getText().toString().trim().length()>0 && batch_college.getText().toString().trim().length()>0 &&
                            batch_year.getText().toString().trim().length()>0 && batch_start_date.getText().toString().trim().length()>0) {

                        databaseHelper.insertNewBatch(batch_name.getText().toString().trim(),
                                batch_college.getText().toString().trim(),
                                batch_year.getText().toString().trim(),
                                batch_start_date.getText().toString().trim(),"");
                        Util.showToast(MainActivity.this,"Batch Added", Color.WHITE);

                        if(batchGridAdapter!=null)
                        {
                            batches=databaseHelper.getAllBatches();
                            batchGridAdapter.setBatches(batches);
                            batchGridAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            batches=databaseHelper.getAllBatches();
                            batchGridAdapter=new BatchGridAdapter(MainActivity.this,batches);
                            batchGridView.setAdapter(batchGridAdapter);

                        }

                        dialog.dismiss();
                    }
                }
            });

            dialog.setCancelable(true);
            dialog.show();



            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
