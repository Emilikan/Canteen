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

public class Canteen extends AppCompatActivity {
    /**
     * класс для отображения меню
     */

    private String canteen;
    private String numberOfDay;

    private ProgressBar progressBar;

    private String[] arrayOfTypeOfDishes = {"Каши", "Супы", "Второе"}; // блюда сортировки
    private RecyclerView recyclerView;
    private Spinner spinnerTypeOfDishes;
    private String selectedDish;
    private List<ForRecycleDish> dishes = new ArrayList<>();
    private ArrayList<Integer> realIdOfBook= new ArrayList<>(); // тут под id в новом списке хранится настоящий id книги

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen);

        canteen = getIntent().getStringExtra("Сanteen"); // получаем номер столовой (под таким же номером хранится инфа в бд)
        numberOfDay = getIntent().getStringExtra("Day"); // получаем номер дня, который выбрал пользователь

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
                progressBar.setVisibility(ProgressBar.VISIBLE); // ставим прогресбар
                selectedDish = (String)parent.getItemAtPosition(position); // получаем значение спинера
                sorting(); // сортировка (отображение блюд отсортированных по типу)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinnerTypeOfDishes.setOnItemSelectedListener(itemSelectedListenerForClass);
    }


    // функция для сортировки блюд
    private void sorting(){
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(ProgressBar.INVISIBLE); // бд загрузилась, а значит убираем прогресбар
                dishes = new ArrayList<>();
                // заменям дни с цифр на буквы (именно под такими днями бляюда записаны в бд)
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

                String counterString = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child("counter").getValue(String.class); // количество блюд (нужно для цикла)
                int counterInt = 0;
                if(counterString!=null) {
                    counterInt = Integer.parseInt(counterString);
                    counterInt++;
                }
                else {
                    Toast.makeText(Canteen.this, "Ошибка на стороне сервера. Вероятнее всего, такого типа блюд нет", Toast.LENGTH_SHORT).show();
                }

                for(int i = 0; i < counterInt; i++){
                    // получаем все значения и создаем новый элемент списка, который затем будет отдан для RecycleView
                    String name = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Name").getValue(String.class);
                    String price = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Price").getValue(String.class);
                    String type = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Type").getValue(String.class);
                    String weight = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Weight").getValue(String.class);
                    String pictures = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Icon").getValue(String.class);
                    String calorie = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("Calorie").getValue(String.class);
                    String mTrients = dataSnapshot.child(canteen).child(thisDay).child(selectedDish).child(Integer.toString(i)).child("MTrients").getValue(String.class);
                    dishes.add(new ForRecycleDish(Canteen.this,
                            name, type, price, weight, canteen+"/" + thisDay + "/" + selectedDish + "/" + Integer.toString(i),
                            calorie, mTrients, pictures, i, true));
                }
                // обновляем UI
                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            // тут можно обработать ошибки с сервера
            }
        });



    }

    public void updateUI() {
        DataAdapter adapter = new DataAdapter(this, dishes);
        recyclerView.setAdapter(adapter);
    }
}
