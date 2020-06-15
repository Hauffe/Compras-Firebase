package com.alexandre.comprasfirebase.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alexandre.comprasfirebase.R;
import com.alexandre.comprasfirebase.entity.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsHolder> {

    private List<Product> products;
    private Context context;

    public ProductsAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }


    @NonNull
    @Override
    public ProductsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_products, viewGroup, false);

        return new ProductsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsHolder productsHolder, int position) {
        Product product = products.get(position);

        productsHolder.product_title_name.setText("NOME: "+product.getNome());
        productsHolder.product_quantity.setText("Quantidade: "+product.getQuantidade().toString());
        productsHolder.product_price.setText("PREÇO: " /*product.getPreco().toString()*/);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductsHolder extends RecyclerView.ViewHolder {
        TextView product_title_name;
        TextView product_quantity;
        TextView product_price;

        public ProductsHolder(@NonNull View itemView) {
            super(itemView);
            product_title_name = itemView.findViewById(R.id.product_title_name);
            product_quantity = itemView.findViewById(R.id.product_quantity);
            product_price = itemView.findViewById(R.id.product_price);
        }
    }
}
