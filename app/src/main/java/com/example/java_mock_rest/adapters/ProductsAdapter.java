package com.example.java_mock_rest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.java_mock_rest.R;
import com.example.java_mock_rest.models.ProductsDataModel;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ApiHolder> {

    private List<ProductsDataModel> productsList;

    private Context context;

    public ProductsAdapter(List<ProductsDataModel> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    public ProductsAdapter() {

    }

    @Override
    public ApiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.product_item_layout, parent,false);
        ApiHolder mh = new ApiHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ApiHolder holder, int position) {

        final ProductsDataModel item = productsList.get(position);

        holder.tTitle.setText(item.getName());
        holder.tColor.setText(item.getColor());
        holder.tIdProduct.setText(item.getPantoneValue());

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ApiHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView tTitle;
        TextView tColor;
        TextView    tIdProduct;

        public ApiHolder(View v) {
            super(v);

            cv = itemView.findViewById(R.id.cv);
            tTitle = v.findViewById(R.id.tTitle);
            tColor = v.findViewById(R.id.tColor);
            tIdProduct = v.findViewById(R.id.tIdProduct);

        }
    }
}
