package com.example.loginretrofit.list_adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.ByteArrayLoader;
import com.example.loginretrofit.Api;
import com.example.loginretrofit.HomeActivity;
import com.example.loginretrofit.R;
import com.example.loginretrofit.model.ProdectReqModel;
import com.example.loginretrofit.model.ProdectRetriveModel;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.MyViewHolder> {

    private List<ProdectRetriveModel> prm;
    private Context context;
    private ImageView imageView;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Pro_name, Pro_price, Off_price, Off_ratio;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            Pro_name = itemView.findViewById(R.id.pro_name);
            Pro_price = itemView.findViewById(R.id.pro_price);
            strikeThroughText(Pro_price);
            Off_price = itemView.findViewById(R.id.off_price);
            Off_ratio = itemView.findViewById(R.id.off_ratioGet);


        }
    }

    public ListViewAdapter(HomeActivity homeActivity, List<ProdectRetriveModel> arrayList) {

        this.context = homeActivity;
        this.prm = arrayList;
    }

    @NonNull
    @Override
    public ListViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ListViewAdapter.MyViewHolder holder, int position) {

        final ProdectRetriveModel prtvmodel = prm.get(position);
        Glide.with(context).load(Api.BASE_URL + prtvmodel.getCover_image()).into(holder.imageView);
        holder.Pro_name.setText(prtvmodel.getName());
        holder.Pro_price.setText("$ " + prtvmodel.getMrp());
        holder.Off_price.setText("$ " + prtvmodel.getPrice());

        Log.e(ListViewAdapter.class.getSimpleName(), "value=" + (((prtvmodel.getMrp() - prtvmodel.getPrice()) / prtvmodel.getMrp()) * 100));
        double a = prtvmodel.getMrp() - prtvmodel.getPrice();
        Log.e("val", String.valueOf(a));
        double b = a / prtvmodel.getMrp();
        Log.e("val 1", String.valueOf(b));
        int perce = (int) (b * 100);
        Log.e("val 2", String.valueOf(perce));
        holder.Off_ratio.setText(perce+"%");


    }

    @Override
    public int getItemCount() {
        return prm.size();
    }

    private void strikeThroughText(TextView price) {
        price.setPaintFlags(price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

}
