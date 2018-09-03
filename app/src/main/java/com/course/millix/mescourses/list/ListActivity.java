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

}
