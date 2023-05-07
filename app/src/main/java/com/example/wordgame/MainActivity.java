package com.example.wordgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int i=5000,puan=0,yanlisGiris=0,oyunSonu=0;
    List<Integer> silinecekHarfler = new ArrayList<>();
    List<Integer> chosenWordsNumber = new ArrayList<>();
    List<Integer> harfSirasi = new ArrayList<>();
    String chosenWord="";
    Random rand = new Random();
    ImageView iv_11,iv_12,iv_13,iv_14,iv_15,iv_16,iv_17,iv_18,iv_21,iv_22,iv_23,iv_24,iv_25,iv_26,iv_27,iv_28,
              iv_31,iv_32,iv_33,iv_34,iv_35,iv_36,iv_37,iv_38,iv_41,iv_42,iv_43,iv_44,iv_45,iv_46,iv_47,iv_48,
              iv_51,iv_52,iv_53,iv_54,iv_55,iv_56,iv_57,iv_58,iv_61,iv_62,iv_63,iv_64,iv_65,iv_66,iv_67,iv_68,
              iv_71,iv_72,iv_73,iv_74,iv_75,iv_76,iv_77,iv_78,iv_81,iv_82,iv_83,iv_84,iv_85,iv_86,iv_87,iv_88,
              iv_91,iv_92,iv_93,iv_94,iv_95,iv_96,iv_97,iv_98,iv_01,iv_02,iv_03,iv_04,iv_05,iv_06,iv_07,iv_08;
    ImageView tick,cross;
    TextView text,score,wrong;
    //String[] wordList = {"a","b","c","ç","d","e","f","g","ğ","h","i","ı","j","k","l","m","n","o","ö","p","r","s","t","u","ü","v","y","z"};
    String[] wordList = {"A", "B", "C", "Ç", "D", "E", "F", "G", "Ğ", "H", "İ", "I", "J", "K", "L", "M", "N", "O", "Ö", "P", "R", "S", "T", "U", "Ü", "V", "Y", "Z"};
    int[] wordPoints = {1,3,4,4,3,1,7,5,8,5,2,1,10,1,1,2,1,2,7,5,1,2,4,1,2,3,7,3,4};
    int[] flag = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    int[] sesliHarfler = {0,5,10,11,17,18,23,24};
    int[] sessizHarfler = {1,2,3,4,6,7,8,9,12,13,14,15,16,19,20,21,22,25,26,27};
    private Handler mHandler = new Handler();
    private Handler mHandler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!isExternalStorageAvailableForRW()){
            tick.setEnabled(false);
        }

        //ImageView atamalari
        iv_11 = (ImageView) findViewById(R.id.iv_11);iv_11.setTag("");
        iv_12 = (ImageView) findViewById(R.id.iv_12);iv_12.setTag("");
        iv_13 = (ImageView) findViewById(R.id.iv_13);iv_13.setTag("");
        iv_14 = (ImageView) findViewById(R.id.iv_14);iv_14.setTag("");
        iv_15 = (ImageView) findViewById(R.id.iv_15);iv_15.setTag("");
        iv_16 = (ImageView) findViewById(R.id.iv_16);iv_16.setTag("");
        iv_17 = (ImageView) findViewById(R.id.iv_17);iv_17.setTag("");
        iv_18 = (ImageView) findViewById(R.id.iv_18);iv_18.setTag("");
        iv_21 = (ImageView) findViewById(R.id.iv_21);iv_21.setTag("");
        iv_22 = (ImageView) findViewById(R.id.iv_22);iv_22.setTag("");
        iv_23 = (ImageView) findViewById(R.id.iv_23);iv_23.setTag("");
        iv_24 = (ImageView) findViewById(R.id.iv_24);iv_24.setTag("");
        iv_25 = (ImageView) findViewById(R.id.iv_25);iv_25.setTag("");
        iv_26 = (ImageView) findViewById(R.id.iv_26);iv_26.setTag("");
        iv_27 = (ImageView) findViewById(R.id.iv_27);iv_27.setTag("");
        iv_28 = (ImageView) findViewById(R.id.iv_28);iv_28.setTag("");
        iv_31 = (ImageView) findViewById(R.id.iv_31);iv_31.setTag("");
        iv_32 = (ImageView) findViewById(R.id.iv_32);iv_32.setTag("");
        iv_33 = (ImageView) findViewById(R.id.iv_33);iv_33.setTag("");
        iv_34 = (ImageView) findViewById(R.id.iv_34);iv_34.setTag("");
        iv_35 = (ImageView) findViewById(R.id.iv_35);iv_35.setTag("");
        iv_36 = (ImageView) findViewById(R.id.iv_36);iv_36.setTag("");
        iv_37 = (ImageView) findViewById(R.id.iv_37);iv_37.setTag("");
        iv_38 = (ImageView) findViewById(R.id.iv_38);iv_38.setTag("");
        iv_41 = (ImageView) findViewById(R.id.iv_41);iv_41.setTag("");
        iv_42 = (ImageView) findViewById(R.id.iv_42);iv_42.setTag("");
        iv_43 = (ImageView) findViewById(R.id.iv_43);iv_43.setTag("");
        iv_44 = (ImageView) findViewById(R.id.iv_44);iv_44.setTag("");
        iv_45 = (ImageView) findViewById(R.id.iv_45);iv_45.setTag("");
        iv_46 = (ImageView) findViewById(R.id.iv_46);iv_46.setTag("");
        iv_47 = (ImageView) findViewById(R.id.iv_47);iv_47.setTag("");
        iv_48 = (ImageView) findViewById(R.id.iv_48);iv_48.setTag("");
        iv_51 = (ImageView) findViewById(R.id.iv_51);iv_51.setTag("");
        iv_52 = (ImageView) findViewById(R.id.iv_52);iv_52.setTag("");
        iv_53 = (ImageView) findViewById(R.id.iv_53);iv_53.setTag("");
        iv_54 = (ImageView) findViewById(R.id.iv_54);iv_54.setTag("");
        iv_55 = (ImageView) findViewById(R.id.iv_55);iv_55.setTag("");
        iv_56 = (ImageView) findViewById(R.id.iv_56);iv_56.setTag("");
        iv_57 = (ImageView) findViewById(R.id.iv_57);iv_57.setTag("");
        iv_58 = (ImageView) findViewById(R.id.iv_58);iv_58.setTag("");
        iv_61 = (ImageView) findViewById(R.id.iv_61);iv_61.setTag("");
        iv_62 = (ImageView) findViewById(R.id.iv_62);iv_62.setTag("");
        iv_63 = (ImageView) findViewById(R.id.iv_63);iv_63.setTag("");
        iv_64 = (ImageView) findViewById(R.id.iv_64);iv_64.setTag("");
        iv_65 = (ImageView) findViewById(R.id.iv_65);iv_65.setTag("");
        iv_66 = (ImageView) findViewById(R.id.iv_66);iv_66.setTag("");
        iv_67 = (ImageView) findViewById(R.id.iv_67);iv_67.setTag("");
        iv_68 = (ImageView) findViewById(R.id.iv_68);iv_68.setTag("");
        iv_71 = (ImageView) findViewById(R.id.iv_71);iv_71.setTag("");
        iv_72 = (ImageView) findViewById(R.id.iv_72);iv_72.setTag("");
        iv_73 = (ImageView) findViewById(R.id.iv_73);iv_73.setTag("");
        iv_74 = (ImageView) findViewById(R.id.iv_74);iv_74.setTag("");
        iv_75 = (ImageView) findViewById(R.id.iv_75);iv_75.setTag("");
        iv_76 = (ImageView) findViewById(R.id.iv_76);iv_76.setTag("");
        iv_77 = (ImageView) findViewById(R.id.iv_77);iv_77.setTag("");
        iv_78 = (ImageView) findViewById(R.id.iv_78);iv_78.setTag("");
        iv_81 = (ImageView) findViewById(R.id.iv_81);iv_81.setTag("");
        iv_82 = (ImageView) findViewById(R.id.iv_82);iv_82.setTag("");
        iv_83 = (ImageView) findViewById(R.id.iv_83);iv_83.setTag("");
        iv_84 = (ImageView) findViewById(R.id.iv_84);iv_84.setTag("");
        iv_85 = (ImageView) findViewById(R.id.iv_85);iv_85.setTag("");
        iv_86 = (ImageView) findViewById(R.id.iv_86);iv_86.setTag("");
        iv_87 = (ImageView) findViewById(R.id.iv_87);iv_87.setTag("");
        iv_88 = (ImageView) findViewById(R.id.iv_88);iv_88.setTag("");
        iv_91 = (ImageView) findViewById(R.id.iv_91);iv_91.setTag("");
        iv_92 = (ImageView) findViewById(R.id.iv_92);iv_92.setTag("");
        iv_93 = (ImageView) findViewById(R.id.iv_93);iv_93.setTag("");
        iv_94 = (ImageView) findViewById(R.id.iv_94);iv_94.setTag("");
        iv_95 = (ImageView) findViewById(R.id.iv_95);iv_95.setTag("");
        iv_96 = (ImageView) findViewById(R.id.iv_96);iv_96.setTag("");
        iv_97 = (ImageView) findViewById(R.id.iv_97);iv_97.setTag("");
        iv_98 = (ImageView) findViewById(R.id.iv_98);iv_98.setTag("");
        iv_01 = (ImageView) findViewById(R.id.iv_01);iv_01.setTag("");
        iv_02 = (ImageView) findViewById(R.id.iv_02);iv_02.setTag("");
        iv_03 = (ImageView) findViewById(R.id.iv_03);iv_03.setTag("");
        iv_04 = (ImageView) findViewById(R.id.iv_04);iv_04.setTag("");
        iv_05 = (ImageView) findViewById(R.id.iv_05);iv_05.setTag("");
        iv_06 = (ImageView) findViewById(R.id.iv_06);iv_06.setTag("");
        iv_07 = (ImageView) findViewById(R.id.iv_07);iv_07.setTag("");
        iv_08 = (ImageView) findViewById(R.id.iv_08);iv_08.setTag("");
        tick = (ImageView) findViewById(R.id.tick);
        cross = (ImageView) findViewById(R.id.cross);
        text = (TextView) findViewById(R.id.chosenwordarea);
        score = (TextView) findViewById(R.id.score);
        wrong = (TextView) findViewById(R.id.wrong);

        delay(0000);
        surekliKontrol.run();
        delay(1000);
        delay(2100);
        surekliHarfYolla.run();

        iv_01.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick2(iv_01,iv_21, 0);}});
        iv_11.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_11, iv_01,iv_21, 1);}});
        iv_21.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_21, iv_11,iv_31, 2);}});
        iv_31.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_31, iv_21,iv_41, 3);}});
        iv_41.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_41, iv_31,iv_51, 4);}});
        iv_51.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_51, iv_41,iv_61, 5);}});
        iv_61.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_61, iv_51,iv_71, 6);}});
        iv_71.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_71, iv_61,iv_81, 7);}});
        iv_81.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_81, iv_71,iv_91, 8);}});
        iv_02.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick2(iv_02,iv_22, 10);}});
        iv_12.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_12, iv_02,iv_22, 11);}});
        iv_22.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_22, iv_12,iv_32, 12);}});
        iv_32.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_32, iv_22,iv_42, 13);}});
        iv_42.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_42, iv_32,iv_52, 14);}});
        iv_52.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_52, iv_42,iv_62, 15);}});
        iv_62.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_62, iv_52,iv_72, 16);}});
        iv_72.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_72, iv_62,iv_82, 17);}});
        iv_82.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_82, iv_72,iv_92, 18);}});
        iv_03.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick2(iv_03,iv_23, 20);}});
        iv_13.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_13, iv_03,iv_23, 21);}});
        iv_23.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_23, iv_13,iv_33, 22);}});
        iv_33.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_33, iv_23,iv_43, 23);}});
        iv_43.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_43, iv_33,iv_53, 24);}});
        iv_53.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_53, iv_43,iv_63, 25);}});
        iv_63.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_63, iv_53,iv_73, 26);}});
        iv_73.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_73, iv_63,iv_83, 27);}});
        iv_83.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_83, iv_73,iv_93, 28);}});
        iv_04.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick2(iv_04,iv_24, 30);}});
        iv_14.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_14, iv_04,iv_24, 31);}});
        iv_24.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_24, iv_14,iv_34, 32);}});
        iv_34.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_34, iv_24,iv_44, 33);}});
        iv_44.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_44, iv_34,iv_54, 34);}});
        iv_54.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_54, iv_44,iv_64, 35);}});
        iv_64.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_64, iv_54,iv_74, 36);}});
        iv_74.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_74, iv_64,iv_84, 37);}});
        iv_84.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_84, iv_74,iv_94, 38);}});
        iv_05.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick2(iv_05,iv_25, 40);}});
        iv_15.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_15, iv_05,iv_25, 41);}});
        iv_25.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_25, iv_15,iv_35, 42);}});
        iv_35.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_35, iv_25,iv_45, 43);}});
        iv_45.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_45, iv_35,iv_55, 44);}});
        iv_55.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_55, iv_45,iv_65, 45);}});
        iv_65.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_65, iv_55,iv_75, 46);}});
        iv_75.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_75, iv_65,iv_85, 47);}});
        iv_85.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_85, iv_75,iv_95, 48);}});
        iv_06.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick2(iv_06,iv_26, 50);}});
        iv_16.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_16, iv_06,iv_26, 51);}});
        iv_26.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_26, iv_16,iv_36, 52);}});
        iv_36.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_36, iv_26,iv_46, 53);}});
        iv_46.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_46, iv_36,iv_56, 54);}});
        iv_56.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_56, iv_46,iv_66, 55);}});
        iv_66.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_66, iv_56,iv_76, 56);}});
        iv_76.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_76, iv_66,iv_86, 57);}});
        iv_86.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_86, iv_76,iv_96, 58);}});
        iv_07.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick2(iv_07,iv_27, 60);}});
        iv_17.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_17, iv_07,iv_27, 61);}});
        iv_27.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_27, iv_17,iv_37, 62);}});
        iv_37.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_37, iv_27,iv_47, 63);}});
        iv_47.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_47, iv_37,iv_57, 64);}});
        iv_57.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_57, iv_47,iv_67, 65);}});
        iv_67.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_67, iv_57,iv_77, 66);}});
        iv_77.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_77, iv_67,iv_87, 67);}});
        iv_87.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_87, iv_77,iv_97, 68);}});
        iv_08.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick2(iv_08,iv_28, 70);}});
        iv_18.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_18, iv_08,iv_28, 71);}});
        iv_28.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_28, iv_18,iv_38, 72);}});
        iv_38.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_38, iv_28,iv_48, 73);}});
        iv_48.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_48, iv_38,iv_58, 74);}});
        iv_58.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_58, iv_48,iv_68, 75);}});
        iv_68.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_68, iv_58,iv_78, 76);}});
        iv_78.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_78, iv_68,iv_88, 77);}});
        iv_88.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { onLetterClick(iv_88, iv_78,iv_98, 78);}});

        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!"".equals(chosenWord))
                {boolean wordFound = false;
                try {
                    int i=0;
                    for(i=0;i<wordList.length;i++){
                        if(chosenWord.charAt(0)!='ğ' && wordList[i].charAt(0)==chosenWord.charAt(0))break;
                    }
                    int dosyaId = getResources().getIdentifier("dosya"+i, "raw", getPackageName());
                    InputStream inputStream = getResources().openRawResource(dosyaId);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line=reader.readLine())!=null) {
                        // Kelimenin dosyadaki bir kelime ile tam eşleşme durumu
                        if (line.trim().equalsIgnoreCase(chosenWord.trim())) {
                            wordFound = true;
                            break;
                        }
                    }
                    reader.close();
                    inputStream.close();
                } catch (IOException e) { e.printStackTrace();}

                if (wordFound) {
                    int toplam=0;
                    Collections.sort(silinecekHarfler);
                    for (Integer i : silinecekHarfler) {
                        if     (i==0){iv_01.setImageResource(0);iv_01.setTag("");}
                        else if(i==1){iv_11.setImageResource(0);iv_11.setTag("");if(flag[1]==2||flag[1]==4||flag[1]==6){iv_01.setImageResource(getResources().getIdentifier("letter"+ iv_01.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==2){iv_21.setImageResource(0);iv_21.setTag("");if((flag[2]==2||flag[2]==4||flag[2]==6)&&(flag[0]!=2&&flag[0]!=4&&flag[0]!=6)&&(flag[1]!=2&&flag[1]!=4&&flag[1]!=6)){iv_11.setImageResource(getResources().getIdentifier("letter"+ iv_11.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==3){iv_31.setImageResource(0);iv_31.setTag("");if((flag[3]==2||flag[3]==4||flag[3]==6)&&(flag[1]!=2&&flag[1]!=4&&flag[1]!=6)&&(flag[2]!=2&&flag[2]!=4&&flag[2]!=6)){iv_21.setImageResource(getResources().getIdentifier("letter"+ iv_21.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==4){iv_41.setImageResource(0);iv_41.setTag("");if((flag[4]==2||flag[4]==4||flag[4]==6)&&(flag[2]!=2&&flag[2]!=4&&flag[2]!=6)&&(flag[3]!=2&&flag[3]!=4&&flag[3]!=6)){iv_31.setImageResource(getResources().getIdentifier("letter"+ iv_31.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==5){iv_51.setImageResource(0);iv_51.setTag("");if((flag[5]==2||flag[5]==4||flag[5]==6)&&(flag[3]!=2&&flag[3]!=4&&flag[3]!=6)&&(flag[4]!=2&&flag[4]!=4&&flag[4]!=6)){iv_41.setImageResource(getResources().getIdentifier("letter"+ iv_41.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==6){iv_61.setImageResource(0);iv_61.setTag("");if((flag[6]==2||flag[6]==4||flag[6]==6)&&(flag[4]!=2&&flag[4]!=4&&flag[4]!=6)&&(flag[5]!=2&&flag[5]!=4&&flag[5]!=6)){iv_51.setImageResource(getResources().getIdentifier("letter"+ iv_51.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==7){iv_71.setImageResource(0);iv_71.setTag("");if((flag[7]==2||flag[7]==4||flag[7]==6)&&(flag[5]!=2&&flag[5]!=4&&flag[5]!=6)&&(flag[6]!=2&&flag[6]!=4&&flag[6]!=6)){iv_61.setImageResource(getResources().getIdentifier("letter"+ iv_61.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==8){iv_81.setImageResource(0);iv_81.setTag("");if((flag[8]==2||flag[8]==4||flag[8]==6)&&(flag[6]!=2&&flag[6]!=4&&flag[6]!=6)&&(flag[7]!=2&&flag[7]!=4&&flag[7]!=6)){iv_71.setImageResource(getResources().getIdentifier("letter"+ iv_71.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==9){iv_91.setImageResource(0);iv_91.setTag("");if((flag[9]==2||flag[9]==4||flag[9]==6)&&(flag[7]!=2&&flag[7]!=4&&flag[7]!=6)&&(flag[8]!=2&&flag[8]!=4&&flag[8]!=6)){iv_81.setImageResource(getResources().getIdentifier("letter"+ iv_81.getTag()+"1", "drawable", getPackageName()));}}

                        if     (i==10){iv_02.setImageResource(0);iv_02.setTag("");}
                        else if(i==11){iv_12.setImageResource(0);iv_12.setTag("");if( flag[11]==2||flag[11]==4||flag[11]==6){iv_02.setImageResource(getResources().getIdentifier("letter"+ iv_02.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==12){iv_22.setImageResource(0);iv_22.setTag("");if((flag[12]==2||flag[12]==4||flag[12]==6)&&(flag[10]!=2&&flag[10]!=4&&flag[10]!=6)&&(flag[11]!=2&&flag[11]!=4&&flag[11]!=6)){iv_12.setImageResource(getResources().getIdentifier("letter"+ iv_12.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==13){iv_32.setImageResource(0);iv_32.setTag("");if((flag[13]==2||flag[13]==4||flag[13]==6)&&(flag[11]!=2&&flag[11]!=4&&flag[11]!=6)&&(flag[12]!=2&&flag[12]!=4&&flag[12]!=6)){iv_22.setImageResource(getResources().getIdentifier("letter"+ iv_22.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==14){iv_42.setImageResource(0);iv_42.setTag("");if((flag[14]==2||flag[14]==4||flag[14]==6)&&(flag[12]!=2&&flag[12]!=4&&flag[12]!=6)&&(flag[13]!=2&&flag[13]!=4&&flag[13]!=6)){iv_32.setImageResource(getResources().getIdentifier("letter"+ iv_32.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==15){iv_52.setImageResource(0);iv_52.setTag("");if((flag[15]==2||flag[15]==4||flag[15]==6)&&(flag[13]!=2&&flag[13]!=4&&flag[13]!=6)&&(flag[14]!=2&&flag[14]!=4&&flag[14]!=6)){iv_42.setImageResource(getResources().getIdentifier("letter"+ iv_42.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==16){iv_62.setImageResource(0);iv_62.setTag("");if((flag[16]==2||flag[16]==4||flag[16]==6)&&(flag[14]!=2&&flag[14]!=4&&flag[14]!=6)&&(flag[15]!=2&&flag[15]!=4&&flag[15]!=6)){iv_52.setImageResource(getResources().getIdentifier("letter"+ iv_52.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==17){iv_72.setImageResource(0);iv_72.setTag("");if((flag[17]==2||flag[17]==4||flag[17]==6)&&(flag[15]!=2&&flag[15]!=4&&flag[15]!=6)&&(flag[16]!=2&&flag[16]!=4&&flag[16]!=6)){iv_62.setImageResource(getResources().getIdentifier("letter"+ iv_62.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==18){iv_82.setImageResource(0);iv_82.setTag("");if((flag[18]==2||flag[18]==4||flag[18]==6)&&(flag[16]!=2&&flag[16]!=4&&flag[16]!=6)&&(flag[17]!=2&&flag[17]!=4&&flag[17]!=6)){iv_72.setImageResource(getResources().getIdentifier("letter"+ iv_72.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==19){iv_92.setImageResource(0);iv_92.setTag("");if((flag[19]==2||flag[19]==4||flag[19]==6)&&(flag[17]!=2&&flag[17]!=4&&flag[17]!=6)&&(flag[18]!=2&&flag[18]!=4&&flag[18]!=6)){iv_82.setImageResource(getResources().getIdentifier("letter"+ iv_82.getTag()+"1", "drawable", getPackageName()));}}

                        if     (i==20){iv_03.setImageResource(0);iv_03.setTag("");}
                        else if(i==21){iv_13.setImageResource(0);iv_13.setTag("");if( flag[21]==2||flag[21]==4||flag[21]==6){iv_03.setImageResource(getResources().getIdentifier("letter"+ iv_03.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==22){iv_23.setImageResource(0);iv_23.setTag("");if((flag[22]==2||flag[22]==4||flag[22]==6)&&(flag[20]!=2&&flag[20]!=4&&flag[20]!=6)&&(flag[21]!=2&&flag[21]!=4&&flag[21]!=6)){iv_13.setImageResource(getResources().getIdentifier("letter"+ iv_13.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==23){iv_33.setImageResource(0);iv_33.setTag("");if((flag[23]==2||flag[23]==4||flag[23]==6)&&(flag[21]!=2&&flag[21]!=4&&flag[21]!=6)&&(flag[22]!=2&&flag[22]!=4&&flag[22]!=6)){iv_23.setImageResource(getResources().getIdentifier("letter"+ iv_23.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==24){iv_43.setImageResource(0);iv_43.setTag("");if((flag[24]==2||flag[24]==4||flag[24]==6)&&(flag[22]!=2&&flag[22]!=4&&flag[22]!=6)&&(flag[23]!=2&&flag[23]!=4&&flag[23]!=6)){iv_33.setImageResource(getResources().getIdentifier("letter"+ iv_33.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==25){iv_53.setImageResource(0);iv_53.setTag("");if((flag[25]==2||flag[25]==4||flag[25]==6)&&(flag[23]!=2&&flag[23]!=4&&flag[23]!=6)&&(flag[24]!=2&&flag[24]!=4&&flag[24]!=6)){iv_43.setImageResource(getResources().getIdentifier("letter"+ iv_43.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==26){iv_63.setImageResource(0);iv_63.setTag("");if((flag[26]==2||flag[26]==4||flag[26]==6)&&(flag[24]!=2&&flag[24]!=4&&flag[24]!=6)&&(flag[25]!=2&&flag[25]!=4&&flag[25]!=6)){iv_53.setImageResource(getResources().getIdentifier("letter"+ iv_53.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==27){iv_73.setImageResource(0);iv_73.setTag("");if((flag[27]==2||flag[27]==4||flag[27]==6)&&(flag[25]!=2&&flag[25]!=4&&flag[25]!=6)&&(flag[26]!=2&&flag[26]!=4&&flag[26]!=6)){iv_63.setImageResource(getResources().getIdentifier("letter"+ iv_63.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==28){iv_83.setImageResource(0);iv_83.setTag("");if((flag[28]==2||flag[28]==4||flag[28]==6)&&(flag[26]!=2&&flag[26]!=4&&flag[26]!=6)&&(flag[27]!=2&&flag[27]!=4&&flag[27]!=6)){iv_73.setImageResource(getResources().getIdentifier("letter"+ iv_73.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==29){iv_93.setImageResource(0);iv_93.setTag("");if((flag[29]==2||flag[29]==4||flag[29]==6)&&(flag[27]!=2&&flag[27]!=4&&flag[27]!=6)&&(flag[28]!=2&&flag[28]!=4&&flag[28]!=6)){iv_83.setImageResource(getResources().getIdentifier("letter"+ iv_83.getTag()+"1", "drawable", getPackageName()));}}

                        if     (i==30){iv_04.setImageResource(0);iv_04.setTag("");}
                        else if(i==31){iv_14.setImageResource(0);iv_14.setTag("");if( flag[31]==2||flag[41]==4||flag[41]==6){iv_04.setImageResource(getResources().getIdentifier("letter"+ iv_04.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==32){iv_24.setImageResource(0);iv_24.setTag("");if((flag[32]==2||flag[42]==4||flag[42]==6)&&(flag[40]!=2&&flag[40]!=4&&flag[40]!=6)&&(flag[41]!=2&&flag[41]!=4&&flag[41]!=6)){iv_14.setImageResource(getResources().getIdentifier("letter"+ iv_14.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==33){iv_34.setImageResource(0);iv_34.setTag("");if((flag[33]==2||flag[43]==4||flag[43]==6)&&(flag[41]!=2&&flag[41]!=4&&flag[41]!=6)&&(flag[42]!=2&&flag[42]!=4&&flag[42]!=6)){iv_24.setImageResource(getResources().getIdentifier("letter"+ iv_24.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==34){iv_44.setImageResource(0);iv_44.setTag("");if((flag[34]==2||flag[44]==4||flag[44]==6)&&(flag[42]!=2&&flag[42]!=4&&flag[42]!=6)&&(flag[43]!=2&&flag[43]!=4&&flag[43]!=6)){iv_34.setImageResource(getResources().getIdentifier("letter"+ iv_34.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==35){iv_54.setImageResource(0);iv_54.setTag("");if((flag[35]==2||flag[45]==4||flag[45]==6)&&(flag[43]!=2&&flag[43]!=4&&flag[43]!=6)&&(flag[44]!=2&&flag[44]!=4&&flag[44]!=6)){iv_44.setImageResource(getResources().getIdentifier("letter"+ iv_44.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==36){iv_64.setImageResource(0);iv_64.setTag("");if((flag[36]==2||flag[46]==4||flag[46]==6)&&(flag[44]!=2&&flag[44]!=4&&flag[44]!=6)&&(flag[45]!=2&&flag[45]!=4&&flag[45]!=6)){iv_54.setImageResource(getResources().getIdentifier("letter"+ iv_54.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==37){iv_74.setImageResource(0);iv_74.setTag("");if((flag[37]==2||flag[47]==4||flag[47]==6)&&(flag[45]!=2&&flag[45]!=4&&flag[45]!=6)&&(flag[46]!=2&&flag[46]!=4&&flag[46]!=6)){iv_64.setImageResource(getResources().getIdentifier("letter"+ iv_64.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==38){iv_84.setImageResource(0);iv_84.setTag("");if((flag[38]==2||flag[48]==4||flag[48]==6)&&(flag[46]!=2&&flag[46]!=4&&flag[46]!=6)&&(flag[47]!=2&&flag[47]!=4&&flag[47]!=6)){iv_74.setImageResource(getResources().getIdentifier("letter"+ iv_74.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==39){iv_94.setImageResource(0);iv_94.setTag("");if((flag[39]==2||flag[49]==4||flag[49]==6)&&(flag[47]!=2&&flag[47]!=4&&flag[47]!=6)&&(flag[48]!=2&&flag[48]!=4&&flag[48]!=6)){iv_84.setImageResource(getResources().getIdentifier("letter"+ iv_84.getTag()+"1", "drawable", getPackageName()));}}

                        if     (i==40){iv_05.setImageResource(0);iv_05.setTag("");}
                        else if(i==41){iv_15.setImageResource(0);iv_15.setTag("");if( flag[41]==2||flag[41]==4||flag[41]==6){iv_05.setImageResource(getResources().getIdentifier("letter"+ iv_05.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==42){iv_25.setImageResource(0);iv_25.setTag("");if((flag[42]==2||flag[42]==4||flag[42]==6)&&(flag[40]!=2&&flag[40]!=4&&flag[40]!=6)&&(flag[41]!=2&&flag[41]!=4&&flag[41]!=6)){iv_15.setImageResource(getResources().getIdentifier("letter"+ iv_15.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==43){iv_35.setImageResource(0);iv_35.setTag("");if((flag[43]==2||flag[43]==4||flag[43]==6)&&(flag[41]!=2&&flag[41]!=4&&flag[41]!=6)&&(flag[42]!=2&&flag[42]!=4&&flag[42]!=6)){iv_25.setImageResource(getResources().getIdentifier("letter"+ iv_25.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==44){iv_45.setImageResource(0);iv_45.setTag("");if((flag[44]==2||flag[44]==4||flag[44]==6)&&(flag[42]!=2&&flag[42]!=4&&flag[42]!=6)&&(flag[43]!=2&&flag[43]!=4&&flag[43]!=6)){iv_35.setImageResource(getResources().getIdentifier("letter"+ iv_35.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==45){iv_55.setImageResource(0);iv_55.setTag("");if((flag[45]==2||flag[45]==4||flag[45]==6)&&(flag[43]!=2&&flag[43]!=4&&flag[43]!=6)&&(flag[44]!=2&&flag[44]!=4&&flag[44]!=6)){iv_45.setImageResource(getResources().getIdentifier("letter"+ iv_45.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==46){iv_65.setImageResource(0);iv_65.setTag("");if((flag[46]==2||flag[46]==4||flag[46]==6)&&(flag[44]!=2&&flag[44]!=4&&flag[44]!=6)&&(flag[45]!=2&&flag[45]!=4&&flag[45]!=6)){iv_55.setImageResource(getResources().getIdentifier("letter"+ iv_55.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==47){iv_75.setImageResource(0);iv_75.setTag("");if((flag[47]==2||flag[47]==4||flag[47]==6)&&(flag[45]!=2&&flag[45]!=4&&flag[45]!=6)&&(flag[46]!=2&&flag[46]!=4&&flag[46]!=6)){iv_65.setImageResource(getResources().getIdentifier("letter"+ iv_65.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==48){iv_85.setImageResource(0);iv_85.setTag("");if((flag[48]==2||flag[48]==4||flag[48]==6)&&(flag[46]!=2&&flag[46]!=4&&flag[46]!=6)&&(flag[47]!=2&&flag[47]!=4&&flag[47]!=6)){iv_75.setImageResource(getResources().getIdentifier("letter"+ iv_75.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==49){iv_95.setImageResource(0);iv_95.setTag("");if((flag[49]==2||flag[49]==4||flag[49]==6)&&(flag[47]!=2&&flag[47]!=4&&flag[47]!=6)&&(flag[48]!=2&&flag[48]!=4&&flag[48]!=6)){iv_85.setImageResource(getResources().getIdentifier("letter"+ iv_85.getTag()+"1", "drawable", getPackageName()));}}

                        if     (i==50){iv_06.setImageResource(0);iv_06.setTag("");}
                        else if(i==51){iv_16.setImageResource(0);iv_16.setTag("");if( flag[51]==2||flag[51]==4||flag[51]==6){iv_06.setImageResource(getResources().getIdentifier("letter"+ iv_06.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==52){iv_26.setImageResource(0);iv_26.setTag("");if((flag[52]==2||flag[52]==4||flag[52]==6)&&(flag[50]!=2&&flag[50]!=4&&flag[50]!=6)&&(flag[51]!=2&&flag[51]!=4&&flag[51]!=6)){iv_16.setImageResource(getResources().getIdentifier("letter"+ iv_16.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==53){iv_36.setImageResource(0);iv_36.setTag("");if((flag[53]==2||flag[53]==4||flag[53]==6)&&(flag[51]!=2&&flag[51]!=4&&flag[51]!=6)&&(flag[52]!=2&&flag[52]!=4&&flag[52]!=6)){iv_26.setImageResource(getResources().getIdentifier("letter"+ iv_26.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==54){iv_46.setImageResource(0);iv_46.setTag("");if((flag[54]==2||flag[54]==4||flag[54]==6)&&(flag[52]!=2&&flag[52]!=4&&flag[52]!=6)&&(flag[53]!=2&&flag[53]!=4&&flag[53]!=6)){iv_36.setImageResource(getResources().getIdentifier("letter"+ iv_36.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==55){iv_56.setImageResource(0);iv_56.setTag("");if((flag[55]==2||flag[55]==4||flag[55]==6)&&(flag[53]!=2&&flag[53]!=4&&flag[53]!=6)&&(flag[54]!=2&&flag[54]!=4&&flag[54]!=6)){iv_46.setImageResource(getResources().getIdentifier("letter"+ iv_46.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==56){iv_66.setImageResource(0);iv_66.setTag("");if((flag[56]==2||flag[56]==4||flag[56]==6)&&(flag[54]!=2&&flag[54]!=4&&flag[54]!=6)&&(flag[55]!=2&&flag[55]!=4&&flag[55]!=6)){iv_56.setImageResource(getResources().getIdentifier("letter"+ iv_56.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==57){iv_76.setImageResource(0);iv_76.setTag("");if((flag[57]==2||flag[57]==4||flag[57]==6)&&(flag[55]!=2&&flag[55]!=4&&flag[55]!=6)&&(flag[56]!=2&&flag[56]!=4&&flag[56]!=6)){iv_66.setImageResource(getResources().getIdentifier("letter"+ iv_66.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==58){iv_86.setImageResource(0);iv_86.setTag("");if((flag[58]==2||flag[58]==4||flag[58]==6)&&(flag[56]!=2&&flag[56]!=4&&flag[56]!=6)&&(flag[57]!=2&&flag[57]!=4&&flag[57]!=6)){iv_76.setImageResource(getResources().getIdentifier("letter"+ iv_76.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==59){iv_96.setImageResource(0);iv_96.setTag("");if((flag[59]==2||flag[59]==4||flag[59]==6)&&(flag[57]!=2&&flag[57]!=4&&flag[57]!=6)&&(flag[58]!=2&&flag[58]!=4&&flag[58]!=6)){iv_86.setImageResource(getResources().getIdentifier("letter"+ iv_86.getTag()+"1", "drawable", getPackageName()));}}

                        if     (i==60){iv_07.setImageResource(0);iv_07.setTag("");}
                        else if(i==61){iv_17.setImageResource(0);iv_17.setTag("");if( flag[61]==2||flag[61]==4||flag[61]==6){iv_07.setImageResource(getResources().getIdentifier("letter"+ iv_07.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==62){iv_27.setImageResource(0);iv_27.setTag("");if((flag[62]==2||flag[62]==4||flag[62]==6)&&(flag[60]!=2&&flag[60]!=4&&flag[60]!=6)&&(flag[61]!=2&&flag[61]!=4&&flag[61]!=6)){iv_17.setImageResource(getResources().getIdentifier("letter"+ iv_17.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==63){iv_37.setImageResource(0);iv_37.setTag("");if((flag[63]==2||flag[63]==4||flag[63]==6)&&(flag[61]!=2&&flag[61]!=4&&flag[61]!=6)&&(flag[62]!=2&&flag[62]!=4&&flag[62]!=6)){iv_27.setImageResource(getResources().getIdentifier("letter"+ iv_27.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==64){iv_47.setImageResource(0);iv_47.setTag("");if((flag[64]==2||flag[64]==4||flag[64]==6)&&(flag[62]!=2&&flag[62]!=4&&flag[62]!=6)&&(flag[63]!=2&&flag[63]!=4&&flag[63]!=6)){iv_37.setImageResource(getResources().getIdentifier("letter"+ iv_37.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==65){iv_57.setImageResource(0);iv_57.setTag("");if((flag[65]==2||flag[65]==4||flag[65]==6)&&(flag[63]!=2&&flag[63]!=4&&flag[63]!=6)&&(flag[64]!=2&&flag[64]!=4&&flag[64]!=6)){iv_47.setImageResource(getResources().getIdentifier("letter"+ iv_47.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==66){iv_67.setImageResource(0);iv_67.setTag("");if((flag[66]==2||flag[66]==4||flag[66]==6)&&(flag[64]!=2&&flag[64]!=4&&flag[64]!=6)&&(flag[65]!=2&&flag[65]!=4&&flag[65]!=6)){iv_57.setImageResource(getResources().getIdentifier("letter"+ iv_57.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==67){iv_77.setImageResource(0);iv_77.setTag("");if((flag[67]==2||flag[67]==4||flag[67]==6)&&(flag[65]!=2&&flag[65]!=4&&flag[65]!=6)&&(flag[66]!=2&&flag[66]!=4&&flag[66]!=6)){iv_67.setImageResource(getResources().getIdentifier("letter"+ iv_67.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==68){iv_87.setImageResource(0);iv_87.setTag("");if((flag[68]==2||flag[68]==4||flag[68]==6)&&(flag[66]!=2&&flag[66]!=4&&flag[66]!=6)&&(flag[67]!=2&&flag[67]!=4&&flag[67]!=6)){iv_77.setImageResource(getResources().getIdentifier("letter"+ iv_77.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==69){iv_97.setImageResource(0);iv_97.setTag("");if((flag[69]==2||flag[69]==4||flag[69]==6)&&(flag[67]!=2&&flag[67]!=4&&flag[67]!=6)&&(flag[68]!=2&&flag[68]!=4&&flag[68]!=6)){iv_87.setImageResource(getResources().getIdentifier("letter"+ iv_87.getTag()+"1", "drawable", getPackageName()));}}

                        if     (i==70){iv_08.setImageResource(0);iv_08.setTag("");}
                        else if(i==71){iv_18.setImageResource(0);iv_18.setTag("");if( flag[71]==2||flag[71]==4||flag[71]==6){iv_08.setImageResource(getResources().getIdentifier("letter"+ iv_08.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==72){iv_28.setImageResource(0);iv_28.setTag("");if((flag[72]==2||flag[72]==4||flag[72]==6)&&(flag[70]!=2&&flag[70]!=4&&flag[70]!=6)&&(flag[71]!=2&&flag[71]!=4&&flag[71]!=6)){iv_18.setImageResource(getResources().getIdentifier("letter"+ iv_18.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==73){iv_38.setImageResource(0);iv_38.setTag("");if((flag[73]==2||flag[73]==4||flag[73]==6)&&(flag[71]!=2&&flag[71]!=4&&flag[71]!=6)&&(flag[72]!=2&&flag[72]!=4&&flag[72]!=6)){iv_28.setImageResource(getResources().getIdentifier("letter"+ iv_28.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==74){iv_48.setImageResource(0);iv_48.setTag("");if((flag[74]==2||flag[74]==4||flag[74]==6)&&(flag[72]!=2&&flag[72]!=4&&flag[72]!=6)&&(flag[73]!=2&&flag[73]!=4&&flag[73]!=6)){iv_38.setImageResource(getResources().getIdentifier("letter"+ iv_38.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==75){iv_58.setImageResource(0);iv_58.setTag("");if((flag[75]==2||flag[75]==4||flag[75]==6)&&(flag[73]!=2&&flag[73]!=4&&flag[73]!=6)&&(flag[74]!=2&&flag[74]!=4&&flag[74]!=6)){iv_48.setImageResource(getResources().getIdentifier("letter"+ iv_48.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==76){iv_68.setImageResource(0);iv_68.setTag("");if((flag[76]==2||flag[76]==4||flag[76]==6)&&(flag[74]!=2&&flag[74]!=4&&flag[74]!=6)&&(flag[75]!=2&&flag[75]!=4&&flag[75]!=6)){iv_58.setImageResource(getResources().getIdentifier("letter"+ iv_58.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==77){iv_78.setImageResource(0);iv_78.setTag("");if((flag[77]==2||flag[77]==4||flag[77]==6)&&(flag[75]!=2&&flag[75]!=4&&flag[75]!=6)&&(flag[76]!=2&&flag[76]!=4&&flag[76]!=6)){iv_68.setImageResource(getResources().getIdentifier("letter"+ iv_68.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==78){iv_88.setImageResource(0);iv_88.setTag("");if((flag[78]==2||flag[78]==4||flag[78]==6)&&(flag[76]!=2&&flag[76]!=4&&flag[76]!=6)&&(flag[77]!=2&&flag[77]!=4&&flag[77]!=6)){iv_78.setImageResource(getResources().getIdentifier("letter"+ iv_78.getTag()+"1", "drawable", getPackageName()));}}
                        else if(i==79){iv_98.setImageResource(0);iv_98.setTag("");if((flag[79]==2||flag[79]==4||flag[79]==6)&&(flag[77]!=2&&flag[77]!=4&&flag[77]!=6)&&(flag[78]!=2&&flag[78]!=4&&flag[78]!=6)){iv_88.setImageResource(getResources().getIdentifier("letter"+ iv_88.getTag()+"1", "drawable", getPackageName()));}}

                    }

                    for (Integer i : chosenWordsNumber) {
                        toplam=toplam+wordPoints[i];
                    }
                    puan=puan+toplam;
                    score.setText(puan+"");
                    silinecekHarfler.clear();
                    chosenWordsNumber.clear();
                    text.setText("");
                    chosenWord="";
                } else {
                    // Kelime dosyada bulunmaz ise
                    text.setText("");
                    chosenWordsNumber.clear();
                    chosenWord="";
                    silinecekHarfler.clear();
                    for (int i = 0; i < flag.length; i++) {
                        if     (flag[i]==4||flag[i]==6)flag[i] = 2;
                        else if(flag[i]==3||flag[i]==5)flag[i] = 1;
                        else if(flag[i]==0)flag[i] = 1;
                    }
                    unselectWords();
                    yanlisGiris++;
                    if(wrong.getText().equals("")){wrong.setText("x");}
                    else if(wrong.getText().equals("x")){wrong.setText("xx");}
                    else if(wrong.getText().equals("xx")){wrong.setText("xxx");}
                    if(yanlisGiris==3){ // her 3 yanlıs cevap durumunda harfler iner
                        harfYolla();
                        yanlisGiris=0;
                    }
                }
                wordFound = false;

            }}
        });

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!"".equals(chosenWord)) {
                    text.setText("");
                    chosenWordsNumber.clear();
                    chosenWord="";
                    silinecekHarfler.clear();
                    for (int i = 0; i < flag.length; i++) {
                       if     (flag[i]==4||flag[i]==6)flag[i] = 2;
                       else if(flag[i]==3||flag[i]==5)flag[i] = 1;
                       else if(flag[i]==0)flag[i] = 1;
                    }
                unselectWords();
            }}
        });

    }

    private void onLetterClick(ImageView imageView,ImageView imageView2,ImageView imageView3,int index) {
        if( ((flag[index-1]==4||flag[index-1]==2||flag[index-1]==6) || flag[index]==2 || ((flag[index+1]==2 || flag[index+1]==4 ||flag[index+1]==6)&&!"".equals((String)imageView3.getTag()) ) ) && flag[index]!=3 && flag[index]!=4 && flag[index]!=5 &&flag[index]!=6 &&  !"".equals((String)imageView2.getTag())){
            silinecekHarfler.add(index);
            harfSirasi.add(index);
            if(chosenWord.equals("")){chosenWord=""+wordList[Integer.parseInt(imageView.getTag().toString())];}
            else                     {chosenWord = chosenWord.concat(""+wordList[Integer.parseInt(imageView.getTag().toString())]);}
            text.setText(chosenWord);
            chosenWordsNumber.add(Integer.parseInt(imageView.getTag().toString()));
            imageView.setImageResource(getResources().getIdentifier("letter"+imageView.getTag().toString()+"1" , "drawable", getPackageName()));
            if     (flag[index]==2)flag[index]=4;
            else if(flag[index]==1)flag[index]=3;
            //Log.i("TAG", ""+flag[index]);
        }
        else if((flag[index]==3 || flag[index]==4 || flag[index]==1) && !"".equals((String)imageView2.getTag())){
            silinecekHarfler.add(index);
            harfSirasi.add(index);
            if(chosenWord.equals("")){chosenWord=""+wordList[Integer.parseInt(imageView.getTag().toString())];}
            else                     {chosenWord = chosenWord.concat(""+wordList[Integer.parseInt(imageView.getTag().toString())]);}
            text.setText(chosenWord);
            chosenWordsNumber.add(Integer.parseInt(imageView.getTag().toString()));
            imageView.setImageResource(getResources().getIdentifier("letter"+imageView.getTag().toString()+"0" , "drawable", getPackageName()));
            if     (flag[index]==4)flag[index]=6;
            else if(flag[index]==3)flag[index]=5;
            else if(flag[index]==1)flag[index]=0;
        }
        else if((flag[index]==6 || flag[index]==5 || flag[index]==0) && !"".equals((String)imageView2.getTag())){
            if(flag[index]==6 || flag[index]==5){
                String s="";
                for (int i = 0; i < silinecekHarfler.size(); i++) {
                    if(silinecekHarfler.get(i)!=index)s=s+chosenWord.charAt(i);
                }
                silinecekHarfler.remove(Integer.valueOf(index));silinecekHarfler.remove(Integer.valueOf(index));
                chosenWord=s;
                text.setText(s);
                imageView.setImageResource(getResources().getIdentifier("letter"+imageView.getTag().toString()+"2" , "drawable", getPackageName()));
            }
            else if(flag[index]==0){
                String s="";
                for (int i = 0; i < silinecekHarfler.size(); i++) {
                    if(silinecekHarfler.get(i)!=index)s=s+chosenWord.charAt(i);
                }
                silinecekHarfler.remove(Integer.valueOf(index));
                chosenWord=s;
                text.setText(s);
                imageView.setImageResource(getResources().getIdentifier("letter"+imageView.getTag().toString()+"1" , "drawable", getPackageName()));
            }
            if     (flag[index]==6)flag[index]=2;
            else if(flag[index]==5)flag[index]=1;
            else if(flag[index]==0)flag[index]=1;
        }
    }
    private void onLetterClick2(ImageView imageView,ImageView ust,int index) {
        if( ( flag[index]==2 || ((flag[index+1]==2 || flag[index+1]==4 ||flag[index+1]==6)&&!"".equals((String)ust.getTag()) ) ) && flag[index]!=3 && flag[index]!=5 && flag[index]!=6 && flag[index]!=4){
            silinecekHarfler.add(index);
            if(chosenWord.equals("")){chosenWord=""+wordList[Integer.parseInt(imageView.getTag().toString())];}
            else                     {chosenWord = chosenWord.concat(""+wordList[Integer.parseInt(imageView.getTag().toString())]);}
            text.setText(chosenWord);
            chosenWordsNumber.add(Integer.parseInt(imageView.getTag().toString()));
            imageView.setImageResource(getResources().getIdentifier("letter"+imageView.getTag().toString()+"1" , "drawable", getPackageName()));
            if     (flag[index]==2)flag[index]=4;
            else if(flag[index]==1)flag[index]=3;
        }
        else if(flag[index]==3 || flag[index]==4 || flag[index]==1){
            silinecekHarfler.add(index);
            if(chosenWord.equals("")){chosenWord=""+wordList[Integer.parseInt(imageView.getTag().toString())];}
            else                     {chosenWord = chosenWord.concat(""+wordList[Integer.parseInt(imageView.getTag().toString())]);}
            text.setText(chosenWord);
            chosenWordsNumber.add(Integer.parseInt(imageView.getTag().toString()));
            imageView.setImageResource(getResources().getIdentifier("letter"+imageView.getTag().toString()+"0" , "drawable", getPackageName()));
            if     (flag[index]==4)flag[index]=6;
            else if(flag[index]==3)flag[index]=5;
            else if(flag[index]==1)flag[index]=0;
        }
        else if((flag[index]==6 || flag[index]==5 || flag[index]==0)) {
            if(flag[index]==6 || flag[index]==5){
                String s="";
                for (int i = 0; i < silinecekHarfler.size(); i++) {
                    if(silinecekHarfler.get(i)!=index)s=s+chosenWord.charAt(i);
                }
                silinecekHarfler.remove(Integer.valueOf(index));silinecekHarfler.remove(Integer.valueOf(index));
                chosenWord=s;
                text.setText(s);
                imageView.setImageResource(getResources().getIdentifier("letter"+imageView.getTag().toString()+"2" , "drawable", getPackageName()));
            }
            else if(flag[index]==0){
                String s="";
                for (int i = 0; i < silinecekHarfler.size(); i++) {
                    if(silinecekHarfler.get(i)!=index)s=s+chosenWord.charAt(i);
                }
                silinecekHarfler.remove(Integer.valueOf(index));
                chosenWord=s;
                text.setText(s);
                imageView.setImageResource(getResources().getIdentifier("letter"+imageView.getTag().toString()+"1" , "drawable", getPackageName()));
            }
            if     (flag[index]==6)flag[index]=2;
            else if(flag[index]==5)flag[index]=1;
            else if(flag[index]==0)flag[index]=1;
        }
    }

    private Runnable surekliHarfYolla = new Runnable() {
        @Override
        public void run() {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {

                    tekHarfYolla();

                }
            }, 1000);
            mHandler.postDelayed(this,i);
        }
    };
    public void harfYolla(){
        ImageView[] imageViews = {iv_91, iv_92, iv_93, iv_94, iv_95, iv_96, iv_97, iv_98};
        ImageView[] alt = {iv_81, iv_82, iv_83, iv_84, iv_85, iv_86, iv_87, iv_88};

        int index=9,i=0;
        for (ImageView iv : imageViews) {
            if ("".equals((String)iv.getTag())) {
                int[] selectedArray = rand.nextInt(10) < 5 ? sesliHarfler : sessizHarfler;
                int harf = selectedArray[rand.nextInt(selectedArray.length)];
                int sans = rand.nextInt(27);
                if (sans == 0) {
                    flag[index] = 2;
                } else {
                    flag[index] = 1;
                }
                if(!"".equals((String)alt[i].getTag()) && (flag[index-1]==2 || flag[index-1]==4 ||flag[index-1]==6)){
                    iv.setImageResource(getResources().getIdentifier("letter" + harf + 2, "drawable", getPackageName()));
                }
                else iv.setImageResource(getResources().getIdentifier("letter" + harf + flag[index], "drawable", getPackageName()));
                iv.setTag(harf + "");
                if(!"".equals((String)alt[i].getTag()) && (flag[index]==2)){
                    alt[i].setImageResource(getResources().getIdentifier("letter" + alt[i].getTag() + "2", "drawable", getPackageName()));
                }
                index=index+10;i++;
            }
        oyunSonuKontrol();
        }

    }
    public void oyunSonuKontrol(){
        ImageView[] imageViews={ iv_01,iv_11,iv_21,iv_31,iv_41,iv_51,iv_61,iv_71,iv_81,iv_91,iv_02,iv_12,iv_22,iv_32,iv_42,iv_52,iv_62,iv_72,iv_82,iv_92,
                                 iv_03,iv_13,iv_23,iv_33,iv_43,iv_53,iv_63,iv_73,iv_83,iv_93,iv_04,iv_14,iv_24,iv_34,iv_44,iv_54,iv_64,iv_74,iv_84,iv_94,
                                 iv_01,iv_11,iv_22,iv_33,iv_44,iv_55,iv_66,iv_77,iv_85,iv_95,iv_06,iv_16,iv_26,iv_36,iv_46,iv_56,iv_66,iv_76,iv_86,iv_96,
                                 iv_01,iv_11,iv_22,iv_33,iv_44,iv_55,iv_66,iv_77,iv_87,iv_97,iv_08,iv_18,iv_28,iv_38,iv_48,iv_58,iv_68,iv_78,iv_88,iv_98};

        /*ImageView[] imageViews={ iv_11,iv_12,iv_13,iv_14,iv_15,iv_16,iv_17,iv_18,iv_21,iv_22,iv_23,iv_24,iv_25,iv_26,iv_27,iv_28,
                                 iv_31,iv_32,iv_33,iv_34,iv_35,iv_36,iv_37,iv_38,iv_41,iv_42,iv_43,iv_44,iv_45,iv_46,iv_47,iv_48,
                                 iv_51,iv_52,iv_53,iv_54,iv_55,iv_56,iv_57,iv_58,iv_61,iv_62,iv_63,iv_64,iv_65,iv_66,iv_67,iv_68,
                                 iv_71,iv_72,iv_73,iv_74,iv_75,iv_76,iv_77,iv_78,iv_81,iv_82,iv_83,iv_84,iv_85,iv_86,iv_87,iv_88,
                                 iv_91,iv_92,iv_93,iv_94,iv_95,iv_96,iv_97,iv_98,iv_01,iv_02,iv_03,iv_04,iv_05,iv_06,iv_07,iv_08};*/
        int dolu=0,k=0;
        for (ImageView iv : imageViews){
            k++;
            if(!"".equals(iv.getTag()))dolu++;
            if(dolu==10 && k%10==0 && oyunSonu==0){
                mHandler.removeCallbacksAndMessages(null);
                mHandler2.removeCallbacksAndMessages(null);
                oyunSonu=1;dolu=0;
                mesaj();
            }
            else if(dolu!=10 && k%10==0) dolu=0;
        }

    }
    private void mesaj(){
        Intent intent2 = new Intent(this, giris.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Oyun bitti. Yeniden başlamak ister misiniz?")
                .setCancelable(false) // Kullanıcının pencereyi kapatmasını engelleme
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {  //Evet seçeneği
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = getIntent();
                        finish(); // Mevcut activity'yi sonlandır
                        startActivity(intent); // Yeni activity'yi başlat
                    }
                })
                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {  //Hayır seçeneği
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        startActivity(intent2);
                        dialog.cancel();
                    }
                });
        File dosya = new File(getExternalFilesDir(null), "puanlar.txt");
        List<String> puanlar = new ArrayList<>();
        // dosyadaki tüm puanları oku
        try (BufferedReader br = new BufferedReader(new FileReader(dosya))) {
            String line;
            while ((line = br.readLine()) != null) {
                puanlar.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        puanlar.add(""+puan);  // yeni puanı ekle
        // tüm puanları dosyaya yeniden yaz
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dosya))) {
            for (String puan : puanlar) {
                bw.write(puan);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void tekHarfYolla(){
        ImageView[] imageViews = {iv_91, iv_92, iv_93, iv_94, iv_95, iv_96, iv_97, iv_98};
        ImageView[] alt = {iv_81, iv_82, iv_83, iv_84, iv_85, iv_86, iv_87, iv_88};

        int index=9,i=0;
        int k = rand.nextInt(7);
            if ("".equals((String)imageViews[k].getTag())) {
                int[] selectedArray = rand.nextInt(10) < 4 ? sesliHarfler : sessizHarfler;
                int harf = selectedArray[rand.nextInt(selectedArray.length)];
                int sans = rand.nextInt(30);
                if (sans == 0) {
                    flag[index] = 2;
                } else {
                    flag[index] = 1;
                }
                if(!"".equals((String)alt[i].getTag()) && (flag[index-1]==2 || flag[index-1]==4 ||flag[index-1]==6)){
                    imageViews[k].setImageResource(getResources().getIdentifier("letter" + harf + 2, "drawable", getPackageName()));
                }
                else imageViews[k].setImageResource(getResources().getIdentifier("letter" + harf + flag[index], "drawable", getPackageName()));
                imageViews[k].setTag(harf + "");
                if(!"".equals((String)alt[i].getTag()) && (flag[index]==2)){
                    alt[i].setImageResource(getResources().getIdentifier("letter" + alt[i].getTag() + "2", "drawable", getPackageName()));
                }
                index=index+10;i++;
            }
            oyunSonuKontrol();
    }

    private Runnable surekliKontrol= new Runnable() {
        @Override
        public void run() {

            if(puan>=100 && puan<200)i=4000;
            else if(puan>=200 && puan<300)i=3000;
            else if(puan>=300 && puan<400)i=2000;
            else if(puan>=400)i=1000;
            boslukKontrol();
            mHandler2.postDelayed(this,1000);
            if(wrong.getText().equals("xxx")){wrong.setText("");}
            oyunSonuKontrol();
        }
    };
    private void boslukKontrol(){
        bk0(iv_01,iv_11, 0);bk0(iv_02,iv_12, 10);bk0(iv_03,iv_13, 20);bk0(iv_04,iv_14, 30);bk0(iv_05,iv_15, 40);bk0(iv_06,iv_16, 50);bk0(iv_07,iv_17, 60);bk0(iv_08,iv_18, 70);
        bk(iv_11,iv_01,iv_21, 1);bk(iv_12,iv_02,iv_22, 11);bk(iv_13,iv_03,iv_23, 21);bk(iv_14,iv_04,iv_24, 31);bk(iv_15,iv_05,iv_25, 41);bk(iv_16,iv_06,iv_26, 51);bk(iv_17,iv_07,iv_27, 61);bk(iv_18,iv_02,iv_28, 71);
        bk(iv_21,iv_11,iv_31, 2);bk(iv_22,iv_12,iv_32, 12);bk(iv_23,iv_13,iv_33, 22);bk(iv_24,iv_14,iv_34, 32);bk(iv_25,iv_15,iv_35, 42);bk(iv_26,iv_16,iv_36, 52);bk(iv_27,iv_17,iv_37, 62);bk(iv_28,iv_12,iv_38, 72);
        bk(iv_31,iv_21,iv_41, 3);bk(iv_32,iv_22,iv_42, 13);bk(iv_33,iv_23,iv_43, 23);bk(iv_34,iv_24,iv_44, 33);bk(iv_35,iv_25,iv_45, 43);bk(iv_36,iv_26,iv_46, 53);bk(iv_37,iv_27,iv_47, 63);bk(iv_38,iv_22,iv_48, 73);
        bk(iv_41,iv_31,iv_51, 4);bk(iv_42,iv_32,iv_52, 14);bk(iv_43,iv_33,iv_53, 24);bk(iv_44,iv_34,iv_54, 34);bk(iv_45,iv_35,iv_55, 44);bk(iv_46,iv_36,iv_56, 54);bk(iv_47,iv_37,iv_57, 64);bk(iv_48,iv_32,iv_58, 74);
        bk(iv_51,iv_41,iv_61, 5);bk(iv_52,iv_42,iv_62, 15);bk(iv_53,iv_43,iv_63, 25);bk(iv_54,iv_44,iv_64, 35);bk(iv_55,iv_45,iv_65, 45);bk(iv_56,iv_46,iv_66, 55);bk(iv_57,iv_47,iv_67, 65);bk(iv_58,iv_42,iv_68, 75);
        bk(iv_61,iv_51,iv_71, 6);bk(iv_62,iv_52,iv_72, 16);bk(iv_63,iv_53,iv_73, 26);bk(iv_64,iv_54,iv_74, 36);bk(iv_65,iv_55,iv_75, 46);bk(iv_66,iv_56,iv_76, 56);bk(iv_67,iv_57,iv_77, 66);bk(iv_68,iv_52,iv_78, 76);
        bk(iv_71,iv_61,iv_81, 7);bk(iv_72,iv_62,iv_82, 17);bk(iv_73,iv_63,iv_83, 27);bk(iv_74,iv_64,iv_84, 37);bk(iv_75,iv_65,iv_85, 47);bk(iv_76,iv_66,iv_86, 57);bk(iv_77,iv_67,iv_87, 67);bk(iv_78,iv_62,iv_88, 77);
        bk(iv_81,iv_71,iv_91, 8);bk(iv_82,iv_72,iv_92, 18);bk(iv_83,iv_73,iv_93, 28);bk(iv_84,iv_74,iv_94, 38);bk(iv_85,iv_75,iv_95, 48);bk(iv_86,iv_76,iv_96, 58);bk(iv_87,iv_77,iv_97, 68);bk(iv_88,iv_72,iv_98, 78);

    }
    public void bk(ImageView imageView,ImageView alt,ImageView ust,int index){
        if("".equals((String)imageView.getTag()) && !"".equals((String)ust.getTag())){
            if(flag[index-1]==2||flag[index-1]==6||flag[index-1]==4||flag[index+1]==2||flag[index+1]==6||flag[index+1]==4){
                imageView.setImageResource(getResources().getIdentifier("letter"+ ust.getTag()+"2", "drawable", getPackageName()));
                if(flag[index-1]==1)     {alt.setImageResource(getResources().getIdentifier("letter"+ alt.getTag()+"2", "drawable", getPackageName()));}
                else if(flag[index-1]==0){alt.setImageResource(getResources().getIdentifier("letter"+ alt.getTag()+"1", "drawable", getPackageName()));flag[index-1]=3;}}
            else{imageView.setImageResource(getResources().getIdentifier("letter"+ ust.getTag()+"1", "drawable", getPackageName()));}
            imageView.setTag(ust.getTag());ust.setImageResource(0);ust.setTag("");flag[index]=flag[index+1];flag[index+1]=1;}

    }
    public void bk0(ImageView imageView,ImageView ust,int index){
        if("".equals((String)imageView.getTag()) && !"".equals((String)ust.getTag())){
            if(flag[index+1]==2||flag[index+1]==4||flag[index+1]==6){imageView.setImageResource(getResources().getIdentifier("letter"+ ust.getTag()+"2", "drawable", getPackageName()));}
            else{imageView.setImageResource(getResources().getIdentifier("letter"+ ust.getTag()+"1", "drawable", getPackageName()));}
            imageView.setTag(ust.getTag());ust.setImageResource(0);ust.setTag("");flag[index]=flag[index+1];flag[index+1]=1;}
        else if(!"".equals((String)imageView.getTag()) && !"".equals((String)ust.getTag())) {
            if((flag[index+1]!=2&&flag[index+1]!=4&&flag[index+1]!=6) && (flag[index]!=0) && flag[index]!=2 && flag[index]!=4&& flag[index]!=6){
                imageView.setImageResource(getResources().getIdentifier("letter"+ imageView.getTag()+"1", "drawable", getPackageName()));}
        }
    }

    public void unselectWords() {
        setWordImage(iv_01, 0);setWordImage(iv_02, 10);setWordImage(iv_03, 20);setWordImage(iv_04, 30);setWordImage(iv_05, 40);setWordImage(iv_06, 50);setWordImage(iv_07, 60);setWordImage(iv_08, 70);
        setWordImage(iv_11, 1);setWordImage(iv_12, 11);setWordImage(iv_13, 21);setWordImage(iv_14, 31);setWordImage(iv_15, 41);setWordImage(iv_16, 51);setWordImage(iv_17, 61);setWordImage(iv_18, 71);
        setWordImage(iv_21, 2);setWordImage(iv_22, 12);setWordImage(iv_23, 22);setWordImage(iv_24, 32);setWordImage(iv_25, 42);setWordImage(iv_26, 52);setWordImage(iv_27, 62);setWordImage(iv_28, 72);
        setWordImage(iv_31, 3);setWordImage(iv_32, 13);setWordImage(iv_33, 23);setWordImage(iv_34, 33);setWordImage(iv_35, 43);setWordImage(iv_36, 53);setWordImage(iv_37, 63);setWordImage(iv_38, 73);
        setWordImage(iv_41, 4);setWordImage(iv_42, 14);setWordImage(iv_43, 24);setWordImage(iv_44, 34);setWordImage(iv_45, 44);setWordImage(iv_46, 54);setWordImage(iv_47, 64);setWordImage(iv_48, 74);
        setWordImage(iv_51, 5);setWordImage(iv_52, 15);setWordImage(iv_53, 25);setWordImage(iv_54, 35);setWordImage(iv_55, 45);setWordImage(iv_56, 55);setWordImage(iv_57, 65);setWordImage(iv_58, 75);
        setWordImage(iv_61, 6);setWordImage(iv_62, 16);setWordImage(iv_63, 26);setWordImage(iv_64, 36);setWordImage(iv_65, 46);setWordImage(iv_66, 56);setWordImage(iv_67, 66);setWordImage(iv_68, 76);
        setWordImage(iv_71, 7);setWordImage(iv_72, 17);setWordImage(iv_73, 27);setWordImage(iv_74, 37);setWordImage(iv_75, 47);setWordImage(iv_76, 57);setWordImage(iv_77, 67);setWordImage(iv_78, 77);
        setWordImage(iv_81, 8);setWordImage(iv_82, 18);setWordImage(iv_83, 28);setWordImage(iv_84, 38);setWordImage(iv_85, 48);setWordImage(iv_86, 58);setWordImage(iv_87, 68);setWordImage(iv_88, 78);
    }
    private void setWordImage(@NonNull ImageView imageView, int index) {
        if (!"".equals((String) imageView.getTag())) {
            if (((index%10)!=0) && (flag[index-1] == 2|| flag[index-1]==4 || flag[index-1]==6 || flag[index]==3 || flag[index]==5 || flag[index]==4 || flag[index]==6 || flag[index]==2 || flag[index+1]==4 || flag[index+1]==6 || flag[index+1]==2)) {
                imageView.setImageResource(getResources().getIdentifier("letter" + imageView.getTag() + "2", "drawable", getPackageName()));
            }
            else if (((index%10)==0) && (flag[index]==3 || flag[index]==5 || flag[index]==4 || flag[index]==6 || flag[index]==2 || flag[index+1]==4 || flag[index+1]==6 || flag[index+1]==2)) {
                imageView.setImageResource(getResources().getIdentifier("letter" + imageView.getTag() + "2", "drawable", getPackageName()));
            }
            else if (flag[index]==1) {
                imageView.setImageResource(getResources().getIdentifier("letter" + imageView.getTag() + "1", "drawable", getPackageName()));
            }
        }
    }

    public void onBackPressed() {
        // Geri tuşuna basıldığında gerçekleşecekler
        mHandler.removeCallbacksAndMessages(null);
        mHandler2.removeCallbacksAndMessages(null);
        finish();
    }

    private boolean isExternalStorageAvailableForRW(){
        String extStorageState = Environment.getExternalStorageState();
        if(extStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }

    private void delay(int m){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                harfYolla();
            }
        }, m);
    }
}