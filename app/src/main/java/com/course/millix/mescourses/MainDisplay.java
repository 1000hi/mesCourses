package com.course.millix.mescourses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainDisplay extends AppCompatActivity {

    private String m_element = "";
    private SharedPreferences mPrefs;
    private static final String PREFS_LIST_NAME = "PREFS_LIST_NAME";
    private static final String PREFS_LIST_QTY = "PREFS_LIST_QTY";
    private static final String PREFS = "PREFS";
    private List<ItemCourse> items = new ArrayList<>();
    ArrayList<String> listItems= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_display);
        FloatingActionButton addActionButton = findViewById(R.id.addActionButton);
        addActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateTextInputDialog();
            }
        });
        CustomListViewAdaptateur adapter = new CustomListViewAdaptateur((ArrayList<ItemCourse>) items, this);

        //handle listview and assign adapter
        ListView lView = findViewById(R.id.list_item_todo);
        lView.setAdapter(adapter);

        mPrefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        if (mPrefs.contains(PREFS_LIST_NAME) && mPrefs.contains(PREFS_LIST_QTY)) {

            listItems.addAll(mPrefs.getStringSet(PREFS_LIST_NAME, null));
        }
    }

    private void generateTextInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Que voulez-vous ajouter à votre liste?");
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
                items.add(new ItemCourse(m_element,new Date(),false,1));
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
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
        Set<String> setName = new HashSet<>();
        Set<String> setQty = new HashSet<>();
        for(ItemCourse item : items){
            setName.add(item.getDenomination());
            setQty.add(String.valueOf(item.getQuantite()));
        }

        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putStringSet(PREFS_LIST_NAME, setName);
        ed.putStringSet(PREFS_LIST_NAME, setQty);
        ed.apply();
    }

}
