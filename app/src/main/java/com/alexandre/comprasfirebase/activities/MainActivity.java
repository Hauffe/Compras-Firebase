package com.alexandre.comprasfirebase.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.alexandre.comprasfirebase.R;
import com.alexandre.comprasfirebase.activities.adapters.ProductsAdapter;
import com.alexandre.comprasfirebase.entity.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductsAdapter adapter;
    private RecyclerView recyclerView;
    private List<Product> products = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFirebase();
        //go to databaseListener
        databaseListener();
    }

    private void databaseListener() {
        databaseReference.child("Produto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products.clear();
                for(DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Product product = objSnapshot.getValue(Product.class);
                    products.add(product);
                    Log.e("Produtos", product.toString());

                    //go to generateDataList
                    generateDataList(products);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void generateDataList(List<Product> products){
        recyclerView = findViewById(R.id.lstProducts);
        adapter = new ProductsAdapter(this, products);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_produtos:
                openNewItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void openNewItem() {
        Intent intent = new Intent(this, NewItemActivity.class);
        startActivity(intent);
    }
}
