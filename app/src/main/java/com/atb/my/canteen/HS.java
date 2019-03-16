package com.atb.my.canteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HS extends AppCompatActivity {
    /**
     * класс для активити, которая идет после выбора столовой (активити с днями)
     */
    private String canteen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hs);

        canteen = getIntent().getStringExtra("Сanteen"); // получаем номер столовой

        Button menuTest = findViewById(R.id.button);
        menuTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HS.this, Canteen.class);
                intent.putExtra("Сanteen", canteen); // передаем значения о выбранном дне и о номере столовой в класс Canteen
                intent.putExtra("Day", "1");
                startActivity(intent);
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
            }
        });
    }
}
