package com.course.millix.mescourses.history;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.course.millix.mescourses.Article;
import com.course.millix.mescourses.R;
import com.course.millix.mescourses.history.persistence.HistoryManager;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
public class HistoryActivity extends AppCompatActivity {

    private HistoryManager hm = new HistoryManager();
    private List<Article> itemHistory = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.history_activity);
        itemHistory.addAll(hm.getHistory());
        final HistoryActivityCustomListViewAdaptator historyActivityCustomListViewAdaptator = new HistoryActivityCustomListViewAdaptator(itemHistory,this);
        ListView lView = findViewById(R.id.history_list);
        lView.setAdapter(historyActivityCustomListViewAdaptator);

        FloatingActionButton hist_flush = findViewById(R.id.flush_history_button);
        hist_flush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyDialog();
            }
        });

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



    @Override
    protected void onPause() {
        super.onPause();
    }

    private void historyDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Voulez-vous vraiment supprimer votre historique des achats ?");
        builder.setCancelable(false);
        // Set up the buttons
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!hm.flushHistory()){
                    generateAlert("Impossible de supprimer la liste");
                }
                else{
                    //clean the listview
                    //reload the acticity for now while i'm looking for a better way to do it
                    finish();
                    startActivity(getIntent());
                }
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        builder.show();
    }

}
