package com.example.cuelogic.interview_test.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cuelogic.interview_test.Model.Animal;
import com.example.cuelogic.interview_test.R;

import java.util.ArrayList;
import java.util.List;

public class RecycledViewAdapter extends RecyclerView.Adapter<RecycledViewAdapter.ViewHolder>{
    //private ArrayList<String> mDataset;


    private List<Animal> mDataset;
    private Context mContext;

    public RecycledViewAdapter(Context context, List<Animal> feedItemList) {
        this.mDataset = feedItemList;
        this.mContext = context;
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public ImageView imageAnimal;


        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            imageAnimal=(ImageView)v.findViewById(R.id.thumb_image);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycledViewAdapter(ArrayList<Animal> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecycledViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Animal animalItem = mDataset.get(position);

        //final String name = mDataset.get(position);

        holder.txtHeader.setText(animalItem.getName()+position);

//        holder.txtHeader.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                remove(name);
//            }
//        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (null != mDataset ? mDataset.size() : 0);
        //return mDataset.size();
    }

}

