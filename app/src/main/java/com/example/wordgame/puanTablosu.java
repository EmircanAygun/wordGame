package com.example.wordgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class puanTablosu extends AppCompatActivity {

    List<Integer> puanlar = new ArrayList<>();
    TextView puan0,puan1,puan2,puan3,puan4,puan5;
    ImageView trash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puan_tablosu);

        puan0 = (TextView) findViewById(R.id.puan0);
        puan1 = (TextView) findViewById(R.id.puan1);
        puan2 = (TextView) findViewById(R.id.puan2);
        puan3 = (TextView) findViewById(R.id.puan3);
        puan4 = (TextView) findViewById(R.id.puan4);
        puan5 = (TextView) findViewById(R.id.puan5);
        trash = (ImageView) findViewById(R.id.trash);

        trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dosya = new File(getExternalFilesDir(null), "puanlar.txt");
                if(dosya.exists()){
                    try {
                        FileWriter fileWriter = new FileWriter(dosya);
                        fileWriter.write("");
                        fileWriter.flush();
                        fileWriter.close();
                        finish();
                        Toast.makeText(getApplicationContext(), "Puanlar Silindi", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        listele();

    }

    public void listele(){
        File dosya = new File(getExternalFilesDir(null), "puanlar.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(dosya))) {
            String line;
            while ((line = br.readLine()) != null) {
                puanlar.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // puanların sıralanıp ilk 6 texte yazılması
        Collections.sort(puanlar, Collections.reverseOrder());
        TextView[] puantext={puan0,puan1,puan2,puan3,puan4,puan5};
        for (int i = 0; i < puanlar.size() && i < puantext.length; i++) {
            puantext[i].setText(puanlar.get(i)+"");
        }
    }

    public void onBackPressed() {
        finish();
    }
}