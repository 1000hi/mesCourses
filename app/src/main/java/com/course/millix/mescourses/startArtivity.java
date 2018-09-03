package com.course.millix.mescourses;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.course.millix.mescourses.history.persistence.HistoryManager;

public class startArtivity extends AppCompatActivity {

    HistoryManager hm = new HistoryManager();


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_display);
        hm.getHistory();
    }

    //Récupération des listes d'items depuis le fichier JSON

}
