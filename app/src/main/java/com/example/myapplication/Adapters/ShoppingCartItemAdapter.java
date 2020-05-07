package com.example.myapplication.Adapters;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainServiceAndProduct.ProductDetail;
import com.example.myapplication.Model.ShoppingCartGroup;
import com.example.myapplication.Model.VendorProduct;
import com.example.myapplication.R;
import com.example.myapplication.Support.Support;

import java.util.List;

import static com.example.myapplication.R.id.shopping_item_remove;

public class ShoppingCartItemAdapter extends RecyclerView.Adapter<ShoppingCartItemAdapter.ViewHolder> {

    private List<VendorProduct> dataList;
    private Context context;
    private AdapterView.OnItemClickListener listener;

    public ImageView shopping_item_remove;

    private ArrayAdapter getAdapterPosition;
    private Notification.MessagingStyle.Message editText;

    public ShoppingCartItemAdapter(Context context, List<VendorProduct> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.shopping_cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final VendorProduct v = dataList.get(position);
        String price = v.getProduct_qty() + "@" + Support.rupiahFormat(v.getProduct_price()) + " koin";

        holder.product_name.setText(v.getProduct_name());
        holder.product_price.setText(price);
        holder.setListener();

        // ke product detail
        holder.shopping_item_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), ProductDetail.class);
                mIntent.putExtra("product_id", v.getProduct_id());
                view.getContext().startActivity(mIntent);
            }
        });

        holder.shopping_item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), ProductDetail.class);
                mIntent.putExtra("product_id", v.getProduct_id());
                view.getContext().startActivity(mIntent);
            }
        });

        // hapus item dari cart
        holder.shopping_item_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void removeItem(int position) {
        dataList.remove(position);
        notifyItemRangeChanged(position, dataList.size());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView product_name, product_price;
        ImageView shopping_item_remove, shopping_item_image;
        ConstraintLayout shopping_item_container;

        ViewHolder(@NonNull View itemView){
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            shopping_item_remove = itemView.findViewById(R.id.shopping_item_remove);
            shopping_item_image = itemView.findViewById(R.id.shopping_item_image);
            shopping_item_container = itemView.findViewById(R.id.shopping_item_container);
        }

        public void setListener() {
        }
    }
}
