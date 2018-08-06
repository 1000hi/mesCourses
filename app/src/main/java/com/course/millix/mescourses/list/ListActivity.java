package com.course.millix.mescourses.list;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;

import com.course.millix.mescourses.Article;
import com.course.millix.mescourses.R;
import com.course.millix.mescourses.dialog.AddArticleListDialog;
import com.course.millix.mescourses.history.HistoryActivity;
import com.course.millix.mescourses.history.persistence.HistoryManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String PREFS_LIST = "PREFS_LIST";
    private static final String PREFS = "PREFS";
    private String m_element = "";
    private SharedPreferences mPrefs;
    private List<Article> items = new ArrayList<>();
    private HistoryManager hm = new HistoryManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_display);
        FloatingActionButton addActionButton = findViewById(R.id.addActionButton);
        final AddArticleListDialog aald = new AddArticleListDialog(this);
        addActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                generateTextInputDialog();
                aald.show();

            }
        });
        ListAcitivityCustomListViewAdaptator adapter = new ListAcitivityCustomListViewAdaptator((ArrayList<Article>) items, this);

        //handle listview and assign adapter
        ListView lView = findViewById(R.id.list_item_todo);
        lView.setAdapter(adapter);
        mPrefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        if (mPrefs.contains(PREFS_LIST)) {
            Gson gson = new Gson();
            String json = mPrefs.getString("PREFS_LIST", null);
            Type type = new TypeToken<List<Article>>() {
            }.getType();
            List<Article> arrayTmp = gson.fromJson(json, type);
            items.addAll(arrayTmp);
        }


        BottomNavigationItemView btnHist = findViewById(R.id.history_button);
        btnHist.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(i);
            }
        });
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
                if (m_element.trim().equals("")) {
                    generateAlert("Vous avez mis un champs vide");
                }
                items.add(new Article(m_element, new Date().toString(), false, 1));
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
        SharedPreferences.Editor ed = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        ed.remove("PREF_LIST");
        ed.putString("PREFS_LIST", json);
        ed.apply();
    }

    public HistoryManager getHm() {
        return hm;
    }

    private void generateAlert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(text)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
