package com.example.alertdialogmultiplechoice;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
TextView textView;
Button btn;
String[] listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btnClick);
        textView=findViewById(R.id.txt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                //String array for multichoice items
                String[] NamesArray=new String[] {"Shivam", "Megha", "Syed", "Aryan", "Asmita","Diksha"};
                final boolean[] CheckedNamesArray=new boolean[] {
                     true,//Shivam
                     false,//Megha
                     false,//Syed
                     false,//Aryan
                     true,//Asmita
                     false,//Diksha
                };
               // String namesArray;
                final List<String> namelist= Arrays.asList(NamesArray);
                builder.setTitle("Select Names");
                builder.setIcon(R.drawable.icon);
                builder.setMultiChoiceItems(NamesArray, CheckedNamesArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        CheckedNamesArray[which]=isChecked;
                        String currentItem=namelist.get(which);
                        Toast.makeText(MainActivity.this,currentItem+ ""+ isChecked,Toast.LENGTH_LONG).show();

                    }
                });
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        textView.setText("Your preffered names..\n");
                        //textView.setAutoSizeTextTypeUniformWithConfiguration(0b1010, 0b1010, 0b1);
                        for (int i=0;i<CheckedNamesArray.length;i++){
                            boolean checked=CheckedNamesArray[i];
                            if (checked){
                                textView.setText(textView.getText()+namelist.get(i)+"\n");
                            }

                        }
                    }
                });
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

    }
}
