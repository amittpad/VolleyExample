package com.example.amittpad.volleyexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amittpad.volleyexample.GlideConnector;
import com.example.amittpad.volleyexample.R;
import com.example.amittpad.volleyexample.pojo.Value;

import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Value> valueList;

    public CustomRecyclerAdapter(Context context, List valueList) {
        this.context = context;
        this.valueList = valueList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_details_child_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(valueList.get(position));

        Value value = valueList.get(position);
        holder.productName.setText(value.getProductName());
        holder.productDes.setText(value.getDescription());
        holder.productPrice.setText(value.getPrice());
       // holder.productName.setText(value.getProductName());
        String image = value.getImages().get(0);
        GlideConnector.getInstance().loadImageDirectly(context, image, holder.productImg);

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


        public ViewHolder(View itemView) {
            super(itemView);

            productName = (TextView) itemView.findViewById(R.id.product_name);
            productDes = (TextView) itemView.findViewById(R.id.product_des);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);
            productImg = (ImageView) itemView.findViewById(R.id.product_image_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Value cpu = (Value) view.getTag();

                   // Toast.makeText(view.getContext(), cpu.getPersonFirstName()+" "+cpu.getPersonLastName()+" is "+ cpu.getJobProfile(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

}
