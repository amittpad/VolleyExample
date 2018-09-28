package com.example.amittpad.volleyexample.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amittpad.volleyexample.GlideConnector;
import com.example.amittpad.volleyexample.ImageDetails;
import com.example.amittpad.volleyexample.R;
import com.example.amittpad.volleyexample.pojo.Value;

import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private Activity activity;
    private List<Value> valueList;

    public CustomRecyclerAdapter(Activity activity, List<Value> valueList) {
        this.activity = activity;
        this.valueList = valueList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_details_child_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setTag(valueList.get(position));

        final Value value = valueList.get(position);
        holder.productName.setText(value.getProductName());
        holder.productDes.setText(value.getDescription());
        holder.productPrice.setText(value.getPrice());
        final String image = value.getImages().get(0);
        GlideConnector.getInstance().loadImageDirectly(activity, image, holder.productImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ImageDetails.class);
                intent.putExtra("image_position", valueList.get(position).getImages().get(0));
                activity.startActivity(intent);
            }
        });

        }

    @Override
    public int getItemCount() {
        return valueList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView productName;
        private final TextView productDes;
        private final TextView productPrice;
        private final ImageView productImg;


        public ViewHolder(final View itemView) {
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.product_name);
            productDes = (TextView) itemView.findViewById(R.id.product_des);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
            productImg = (ImageView) itemView.findViewById(R.id.product_image_id);


        }
    }

}
