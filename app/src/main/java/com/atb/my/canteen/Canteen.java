package com.atb.my.canteen;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.security.AccessController.getContext;

public class Canteen extends AppCompatActivity {

    private String canteen;
    private String numberOfDay;

    private ProgressBar progressBar;

    private String[] arrayOfTypeOfDishes = {"Каши", "Супы"};
    private RecyclerView recyclerView;
    private Spinner spinnerTypeOfDishes;
    private String selectedDish;
    private List<ForRecycleDish> dishes = new ArrayList<>();
    private ArrayList<Integer> realIdOfBook= new ArrayList<>(); // тут под id в новом списке хранится настоящий id книги

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen);

        canteen = getIntent().getStringExtra("Сanteen");
        numberOfDay = getIntent().getStringExtra("Day");

        spinnerTypeOfDishes = findViewById(R.id.spinnerSubject);
        recyclerView = findViewById(R.id.list);
        realIdOfBook.add(0);
        progressBar = findViewById(R.id.progressBarInDownload);

        // спинер
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayOfTypeOfDishes);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeOfDishes.setAdapter(adapter1);

        // обработчики спинеров
        AdapterView.OnItemSelectedListener itemSelectedListenerForClass = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                progressBar.setVisibility(ProgressBar.VISIBLE);
                selectedDish = (String)parent.getItemAtPosition(position);
                sorting();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinnerTypeOfDishes.setOnItemSelectedListener(itemSelectedListenerForClass);
        //dishes.add(new ForRecycleDish(this,"Манка", "Каша", "340p", "250г", realIdOfBook, 45, 45));
    }


    // функция для сортировки блюд
    private void sorting(){
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                dishes = new ArrayList<>();
                // заменям дни с цифр на буквы
                String thisDay = "Mon";
                int nuberOfDayInt = Integer.parseInt(numberOfDay);
                if(nuberOfDayInt == 1){
                    thisDay = "Mon";
                } else if(nuberOfDayInt == 2){
                    thisDay = "Tue";
                } else if(nuberOfDayInt == 3){
                    thisDay = "Wedn";
                } else if(nuberOfDayInt == 4){
                    thisDay = "Thur";
                } else if(nuberOfDayInt == 5){
                    thisDay = "Fri";
                } else if(nuberOfDayInt == 6){
                    thisDay = "Sat";
                }


                String counterString = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child("counter").getValue(String.class);
                int counterInt = 0;
                if(counterString!=null) {
                    counterInt = Integer.parseInt(counterString);
                    counterInt++;
                }
                else {
                    Toast.makeText(Canteen.this, "Ошибка на стороне сервера. Вероятнее всего, такого блюда нет", Toast.LENGTH_SHORT).show();
                }

                for(int i = 0; i < counterInt; i++){
                    String name = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Name").getValue(String.class);
                    String price = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Price").getValue(String.class);
                    String type = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Type").getValue(String.class);
                    String weight = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Weight").getValue(String.class);
                    String pictures = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Icon").getValue(String.class);
                    String calorie = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Calorie").getValue(String.class);
                    String mTrients = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("MTrients").getValue(String.class);
                    dishes.add(new ForRecycleDish(Canteen.this,
                            name, type, price, weight, canteen+"/" + thisDay + "/" + selectedDish + "/" + Integer.toString(i),
                            calorie, mTrients,
                            realIdOfBook, pictures, i));
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
    }
}
