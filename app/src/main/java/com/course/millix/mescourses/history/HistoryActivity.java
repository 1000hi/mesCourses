package com.course.millix.mescourses.history;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ListView;

import com.course.millix.mescourses.ItemCourse;
import com.course.millix.mescourses.R;
import com.course.millix.mescourses.history.persistence.HistoryManager;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
public class HistoryActivity extends AppCompatActivity {

    private HistoryManager hm = new HistoryManager();
    private List<ItemCourse> itemHistory = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.history_activity);
        itemHistory.addAll(hm.getHistory());
        HistoryActivityCustomListViewAdaptator historyActivityCustomListViewAdaptator = new HistoryActivityCustomListViewAdaptator(itemHistory,this);
        ListView lView = findViewById(R.id.history_list);
        lView.setAdapter(historyActivityCustomListViewAdaptator);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

}
