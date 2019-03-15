package com.atb.my.canteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HS extends AppCompatActivity {
    private String canteen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hs);
        // получать номер столовой

        canteen = getIntent().getStringExtra("Сanteen");
        Button menuTest = findViewById(R.id.button);
        menuTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HS.this, Canteen.class);
                intent.putExtra("Сanteen", canteen);
                intent.putExtra("Day", "1");
                startActivity(intent);
                // кидать день недели и номер столовой
            }
        });

        Button tuesday = findViewById(R.id.button3);
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HS.this, Canteen.class);
                intent.putExtra("Сanteen", canteen);
                intent.putExtra("Day", "2");
                startActivity(intent);
                // кидать день недели и номер столовой
            }
        });
        Button wedn = findViewById(R.id.button4);
        wedn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HS.this, Canteen.class);
                intent.putExtra("Сanteen", canteen);
                intent.putExtra("Day", "3");
                startActivity(intent);
                // кидать день недели и номер столовой
            }
        });
        Button thur = findViewById(R.id.button5);
        thur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HS.this, Canteen.class);
                intent.putExtra("Сanteen", canteen);
                intent.putExtra("Day", "4");
                startActivity(intent);
                // кидать день недели и номер столовой
            }
        });
        Button fri = findViewById(R.id.button6);
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HS.this, Canteen.class);
                intent.putExtra("Сanteen", canteen);
                intent.putExtra("Day", "5");
                startActivity(intent);
                // кидать день недели и номер столовой
            }
        });
        Button sec = findViewById(R.id.button7);
        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HS.this, Canteen.class);
                intent.putExtra("Сanteen", canteen);
                intent.putExtra("Day", "6");
                startActivity(intent);
                // кидать день недели и номер столовой
            }
        });
    }
}
