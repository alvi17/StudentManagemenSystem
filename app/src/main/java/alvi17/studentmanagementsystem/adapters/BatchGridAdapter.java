package alvi17.studentmanagementsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import alvi17.studentmanagementsystem.Batch;
import alvi17.studentmanagementsystem.R;

/**
 * Created by User on 6/17/2017.
 */

public class BatchGridAdapter extends BaseAdapter{

    ArrayList<Batch> batches;
    Context context;
    LayoutInflater layoutinflater;
    public BatchGridAdapter(Context context,ArrayList<Batch> batches)
    {
        this.context=context;
        this.batches=batches;
        layoutinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void setBatches(ArrayList<Batch> batches)
    {
        this.batches=batches;
    }

    @Override
    public int getCount() {
        return batches.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;

        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.batch_grid_item, parent, false);

            listViewHolder.batchName=(TextView)convertView.findViewById(R.id.batch_name_textView);
            listViewHolder.imageInListView=(ImageView)convertView.findViewById(R.id.batch_imageView);

            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }

        listViewHolder.batchName.setText(batches.get(position).getName());


        return convertView;
    }



    static class ViewHolder{
        TextView batchName;
        ImageView imageInListView;

    }

}
