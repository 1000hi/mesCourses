package com.course.millix.mescourses;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.HashMap;

public class MainDisplay extends AppCompatActivity {

    private String m_element = "";
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);

        FloatingActionButton addActionButton = findViewById(R.id.addActionButton);

        addActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                generateTextInputDialog("Que voulez-vous ajouter à votre liste?");
            }
        });

    }

    private void addTextArea(String stuff) {
        LinearLayout mainL = findViewById(R.id.layout_text);
        final LinearLayout subL = new LinearLayout(this);
        subL.setOrientation(LinearLayout.HORIZONTAL);
        final LinearLayout subsubL = new LinearLayout(this  );
        subsubL.setOrientation(LinearLayout.HORIZONTAL);
        EditText et = new EditText(this );
        FloatingActionButton ft = new FloatingActionButton(this);
        ft.setImageResource(R.drawable.checked);
        ft.setBackgroundColor(Color.BLUE);
        ft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subL.removeAllViews();
            }
        });
        et.setText(stuff);
        et.setKeyListener(null);
        subsubL.addView(et);
        subsubL.addView(ft);
        subL.addView(subsubL);
        mainL.addView(subL);
}


    private void generateTextInputDialog(String titre) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titre);
        builder.setCancelable(false);

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_element = input.getText().toString();
                addTextArea(m_element);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}