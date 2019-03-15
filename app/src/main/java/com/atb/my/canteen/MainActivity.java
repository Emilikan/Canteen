package com.atb.my.canteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button hs1 = findViewById(R.id.button8);
        hs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HS.class);
                intent.putExtra("Сanteen", "1");
                startActivity(intent);
                // здесь прописать передачу столовой
            }
        });
        Button hs2 = findViewById(R.id.button9);
        hs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HS.class);
                intent.putExtra("Сanteen", "2");
                startActivity(intent);

                // здесь прописать передачу столовой
            }
        });
        Button hs3 = findViewById(R.id.button10);
        hs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HS.class);
                intent.putExtra("Сanteen", "3");
                startActivity(intent);
                // здесь прописать передачу столовой
            }
        });


        Button bascket = findViewById(R.id.button2);
        bascket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Basket.class);
                startActivity(intent);
            }
        });
    }
}
