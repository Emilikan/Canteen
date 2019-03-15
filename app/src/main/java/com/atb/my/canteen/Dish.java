package com.atb.my.canteen;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Dish extends AppCompatActivity {
    private ImageView imageView;
    private TextView name;
    private TextView type;
    private TextView weight;
    private TextView calorie;
    private TextView mTrients;
    private TextView price;

    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        mRef = FirebaseDatabase.getInstance().getReference();

        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.textView);
        type = findViewById(R.id.textView2);
        weight = findViewById(R.id.textView3);
        calorie = findViewById(R.id.textView4);
        mTrients = findViewById(R.id.textView5);
        price = findViewById(R.id.textView6);

        name.setText(getIntent().getStringExtra("name"));
        type.setText(getIntent().getStringExtra("type"));
        weight.setText(getIntent().getStringExtra("weight"));
        calorie.setText(getIntent().getStringExtra("calorie"));
        mTrients.setText(getIntent().getStringExtra("mTrients"));
        price.setText(getIntent().getStringExtra("price"));

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(getIntent().getStringExtra("picture"));
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //progressBar.setVisibility(ProgressBar.INVISIBLE);
                Picasso.with(Dish.this).load(uri).into(imageView);
            }
        });

        Button buy = findViewById(R.id.button11);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // добавление в корзину
            }
        });
    }
}
