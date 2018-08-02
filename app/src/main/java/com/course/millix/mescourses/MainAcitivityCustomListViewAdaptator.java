package com.course.millix.mescourses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;

public class MainAcitivityCustomListViewAdaptator extends BaseAdapter implements ListAdapter {

    private ArrayList<ItemCourse> listeCourse = new ArrayList<>();
    private Context context;

    public MainAcitivityCustomListViewAdaptator(ArrayList<ItemCourse> list, Context context) {
        this.listeCourse = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listeCourse.size();
    }

    @Override
    public Object getItem(int position) {
        return listeCourse.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
        //TODO
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_text_area, null);
        }
        //Handle TextView and display string from your list
        TextView listItemText = view.findViewById(R.id.nameArea);
        listItemText.setText(listeCourse.get(position).getDenomination());

        TextView listItemQt = view.findViewById(R.id.qtArea);
        listItemQt.setText(String.valueOf(listeCourse.get(position).getQuantite()));

        RelativeLayout rl = view.findViewById(R.id.text_rl);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateModifyItemDialog(listeCourse.get(position), position);
            }
        });
        return view;
    }

    private void generateModifyItemDialog(final ItemCourse itemCourse, final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Que faites vous de " + itemCourse.getDenomination() + " ?");
        builder.setCancelable(true);
        // Set up the input
        final EditText input = new EditText(this.context);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        builder.setView(input);

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText nameBox = new EditText(context);
        nameBox.setHint("Article");
        nameBox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        nameBox.setText(itemCourse.getDenomination());
        layout.addView(nameBox);

        final EditText quantityBox = new EditText(context);
        quantityBox.setHint("Quantite");
        quantityBox.setText(String.valueOf(itemCourse.getQuantite()));
        quantityBox.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(quantityBox);

        builder.setView(layout);

        // Set up the buttons
        builder.setPositiveButton("C'est bon pour lui !", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity mA = (MainActivity) context;
                itemCourse.setDoneDate(new Date().toString());
                try {
                    mA.getHm().save(itemCourse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listeCourse.remove(position);
                notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!nameBox.getText().toString().trim().equals("")) {
                    if (!quantityBox.getText().toString().trim().equals("0")) {
                        itemCourse.setDenomination(nameBox.getText().toString().trim());
                        itemCourse.setQuantite(Integer.parseInt(quantityBox.getText().toString().trim()));
                        notifyDataSetChanged();
                    } else {
                        //si Il met 0 en quantité
                        generateAlert("Vous ne pouvez pas mettre 0 en quantité car ça sert a rien, si vous voulez supprimer un article veuillez utiliser le bouton 'supprimer'");
                    }
                } else {
                    generateAlert("Vous ne pouvez pas mettre un champs vide en 'Article' car ça sert a rien, si vous voulez supprimer un article veuillez utiliser le bouton 'supprimer'");
                }

            }
        });
        builder.setNeutralButton("Supprimer cet article", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listeCourse.remove(position);
                notifyDataSetChanged();
            }
        });
        builder.show();
    }

    private void generateAlert(String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
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
