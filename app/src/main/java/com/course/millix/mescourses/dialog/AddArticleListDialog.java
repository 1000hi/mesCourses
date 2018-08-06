package com.course.millix.mescourses.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.course.millix.mescourses.Article;
import com.course.millix.mescourses.R;

import java.util.Date;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by mvaillant on 06/08/2018.
 */

public class AddArticleListDialog extends Dialog implements android.view.View.OnClickListener {

    public Activity activity;
    public Dialog dialog;
    public Button oui, annuler;
    private Article currentArticle = null;

    public AddArticleListDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_add_article);
        oui = findViewById(R.id.btn_ok);
        annuler = findViewById(R.id.btn_cancel);
        oui.setOnClickListener(this);
        annuler.setOnClickListener(this);
        EditText name = findViewById(R.id.nameArea);
        EditText quantity = findViewById(R.id.quantityArea);
        quantity.setInputType(InputType.TYPE_CLASS_NUMBER);
        EditText price = findViewById(R.id.priceArea);
        price.setInputType(InputType.TYPE_CLASS_NUMBER);

        name.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 66) {
                    hideKeyboard(v);
                    return true; //this is required to stop sending key event to parent
                }
                return false;
            }


        });
    }




        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.btn_ok:
                    createArticle();
                    activity.finish();
                    break;
                case R.id.btn_cancel:
                    dismiss();
                    break;
                default:
                    break;
            }
            dismiss();
        }

    private void createArticle() {
        EditText name = findViewById(R.id.nameArea);
        EditText quantity = findViewById(R.id.quantityArea);
        EditText price = findViewById(R.id.priceArea);
        if (!name.getText().toString().trim().equals("")) {
            if (!quantity.getText().toString().trim().equals("")) {
                currentArticle = new Article(name.getText().toString().trim(), new Date().toString(), false, Integer.valueOf(quantity.getText().toString().trim()));
                if (!price.getText().toString().trim().equals("")) {
                    currentArticle.setPrix(Float.valueOf(price.getText().toString().trim()));
                }
            }
        }
    }

    private void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(INPUT_METHOD_SERVICE);
        if (manager != null)
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
