package com.course.millix.mescourses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

public class main_display extends AppCompatActivity {

    private String m_element = "";
    private int y =0;

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
        LinearLayout ll = findViewById(R.id.layout_text);
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.HORIZONTAL);
        EditText et = new EditText(this );
        et.setText(stuff);
        l.addView(et);
        ll.addView(l);
    }

    private void generateTextInputDialog(String titre) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titre);

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
}
