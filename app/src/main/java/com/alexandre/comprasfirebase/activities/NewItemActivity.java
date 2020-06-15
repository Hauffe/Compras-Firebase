package com.alexandre.comprasfirebase.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alexandre.comprasfirebase.R;
import com.alexandre.comprasfirebase.entity.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class NewItemActivity extends AppCompatActivity {

    private EditText name;
    private EditText quantity;
    private EditText price;

    private Integer quantity_;
    private Double price_;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        name = findViewById(R.id.input_product_name);
        quantity = findViewById(R.id.input_product_quantity);
        price =  findViewById(R.id.input_product_price);

        initFirebase();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void save_product(View view) {
        try {
            String uid = UUID.randomUUID().toString();
            quantity_ = Integer.parseInt(quantity.getText().toString());
            price_ = Double.parseDouble(price.getText().toString());
            Product product = new Product(uid, name.getText().toString(), quantity_, price_);
            databaseReference.child("Produto").child(product.getUid()).setValue(product);

            Toast.makeText(this, "Item Salvo", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }catch (Exception e){
            Toast.makeText(this, "Quantidade e Preço devem ser numeros!" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
