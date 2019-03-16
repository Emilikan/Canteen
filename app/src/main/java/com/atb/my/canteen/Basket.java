package com.atb.my.canteen;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Basket extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ForRecycleDish> dishes = new ArrayList<>();
    private DatabaseReference mRef;
    private TextView endOfPrice;
    private List<String> allPaths = new ArrayList<>();
    private int conterOfValueOfAllPath = 0;
    private int sumOfDishes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        mRef = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.list1);
        endOfPrice = findViewById(R.id.textView7);

        Button deleteBasket = findViewById(R.id.button12);
        deleteBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Basket.this);
                SharedPreferences.Editor editor = preferences.edit();
                int thisCounter = -2;
                try {
                    if(preferences.getString("counterOfProducts", "-2") != null) {
                        thisCounter = Integer.parseInt(preferences.getString("counterOfProducts", "-2"));
                    }
                } catch (Exception e){
                    editor.putString("counterOfProducts", "0");
                    editor.apply();
                }

                if (thisCounter < 0){
                    editor.putString("counterOfProducts", "0");
                    editor.apply();
                } else {
                    thisCounter++;
                    for(int i = 1; i <= thisCounter; i++){
                        editor.putString("product_" + i, null);
                        editor.apply();
                    }
                    editor.putString("counterOfProducts", "0");
                    editor.apply();
                }
                dishes = new ArrayList<>();
                sumOfDishes = 0;
                updateUI();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Basket.this);
        SharedPreferences.Editor editor = preferences.edit();
        int thisCounter = -2;
        try {
            if(preferences.getString("counterOfProducts", "-2") != null) {
                thisCounter = Integer.parseInt(preferences.getString("counterOfProducts", "-2"));
            }
        } catch (Exception e){
            editor.putString("counterOfProducts", "0");
            editor.apply();
        }
        if(thisCounter > 0) {
            for (int i = 1; i <= thisCounter; i++) {
                if (preferences.getString("product_" + i, null) != null) {
                    allPaths.add(preferences.getString("product_" + i, null));
                    conterOfValueOfAllPath++;
                } else {
                    break;
                }
            }
            parsingForRecycle();
        }

    }

    private void parsingForRecycle(){
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dishes = new ArrayList<>();
                for (int i = 0; i < conterOfValueOfAllPath; i++) {
                    String name = dataSnapshot.child(allPaths.get(i)).child("Name").getValue(String.class);
                    String price = dataSnapshot.child(allPaths.get(i)).child("Price").getValue(String.class);
                    String type = dataSnapshot.child(allPaths.get(i)).child("Type").getValue(String.class);
                    String weight = dataSnapshot.child(allPaths.get(i)).child("Weight").getValue(String.class);
                    String pictures = dataSnapshot.child(allPaths.get(i)).child("Icon").getValue(String.class);
                    String calorie = dataSnapshot.child(allPaths.get(i)).child("Calorie").getValue(String.class);
                    String mTrients = dataSnapshot.child(allPaths.get(i)).child("MTrients").getValue(String.class);

                    dishes.add(new ForRecycleDish(Basket.this,
                            name, type, price, weight, allPaths.get(i),
                            calorie, mTrients, pictures, i, false));
                    try {
                        sumOfDishes += Integer.parseInt(price);
                    } catch (Exception e){}
                }
                updateUI();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateUI() {
        DataAdapter adapter = new DataAdapter(this, dishes);
        recyclerView.setAdapter(adapter);
        endOfPrice.setText("Итого: " + sumOfDishes);
    }
}
