package com.example.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainServiceAndProduct.ProductDetail;
import com.example.myapplication.MainServiceAndProduct.VendorProductDashboard;
import com.example.myapplication.Model.ShoppingCartGroup;
import com.example.myapplication.Model.VendorProduct;
import com.example.myapplication.R;
import com.example.myapplication.ShoppingCart.ActivityShoppingCart;
import com.example.myapplication.Support.Support;

import java.util.List;

public class ShoppingCartGroupAdapter extends RecyclerView.Adapter<ShoppingCartGroupAdapter.ViewHolder> {

    RecyclerView.LayoutManager layoutManager;
    ShoppingCartItemAdapter itemAdapter;
    private List<ShoppingCartGroup> dataList;
    private Context context;

    public ShoppingCartGroupAdapter(Context context, List<ShoppingCartGroup> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.shopping_cart_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ShoppingCartGroup v = dataList.get(position);
        holder.vendor_name.setText(v.getVendor_name());

        // recycler view
        layoutManager = new LinearLayoutManager(context);
        itemAdapter = new ShoppingCartItemAdapter(context, v.getVendor_products());
        holder.vendor_products.setLayoutManager(layoutManager);
        holder.vendor_products.setAdapter(itemAdapter);

        // pergi ke menu utama halaman vendor
        holder.vendor_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), VendorProductDashboard.class);
                mIntent.putExtra("vendor_id", v.getVendor_id());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView vendor_name;
        RecyclerView vendor_products;
        ViewHolder(@NonNull View itemView){
            super(itemView);
            vendor_name = itemView.findViewById(R.id.vendor_name);
            vendor_products = itemView.findViewById(R.id.rv_shopping_cart_item);
        }
    }

}
