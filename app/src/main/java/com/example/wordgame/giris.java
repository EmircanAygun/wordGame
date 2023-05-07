package com.example.wordgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class giris extends AppCompatActivity {

    MaterialButton yenioyun,puantablosu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        yenioyun = (MaterialButton) findViewById(R.id.yenioyun);
        puantablosu = (MaterialButton) findViewById(R.id.puantablosu);

        yenioyun.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View view) {
                openGame();
            }
        });
        puantablosu.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View view) {
                puanSayfasi();
            }
        });

    }

    public void openGame(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void puanSayfasi(){
        Intent intent = new Intent(this, puanTablosu.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        finish();
    }

}