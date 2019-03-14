package com.atb.my.canteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.security.AccessController.getContext;

public class Canteen extends AppCompatActivity {

    private String[] arrayOfTypeOfDishes = {"Каши", "Cупы"};
    private RecyclerView recyclerView;
    private Spinner spinnerTypeOfDishes;
    private String selectedDish;
    private List<ForRecycleDish> dishes = new ArrayList<>();
    private ArrayList<Integer> realIdOfBook= new ArrayList<>(); // тут под id в новом списке хранится настоящий id книги

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen);
        spinnerTypeOfDishes = findViewById(R.id.spinnerSubject);
        recyclerView = findViewById(R.id.list);
        realIdOfBook.add(0);


        // спинер
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayOfTypeOfDishes);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeOfDishes.setAdapter(adapter1);

        // обработчики спинеров
        AdapterView.OnItemSelectedListener itemSelectedListenerForClass = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDish = (String)parent.getItemAtPosition(position);
                sorting();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinnerTypeOfDishes.setOnItemSelectedListener(itemSelectedListenerForClass);
        dishes.add(new ForRecycleDish(this,"Манка", "Каша", "340p", "250г", realIdOfBook, 45, 45));
        updateUI();
    }


    // функция для сортировки блюд
    private void sorting(){

    }

    public void updateUI() {
        DataAdapter adapter = new DataAdapter(this, dishes);
        recyclerView.setAdapter(adapter);
    }
}
