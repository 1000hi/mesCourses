package com.course.millix.mescourses.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.course.millix.mescourses.Article;
import com.course.millix.mescourses.R;

import java.util.ArrayList;
import java.util.List;


public class HistoryActivityCustomListViewAdaptator extends BaseAdapter implements ListAdapter {

    private List<Article> historiqueListeCourse = new ArrayList<>();
    private Context context;

    public HistoryActivityCustomListViewAdaptator(List<Article> historiqueListeCourse, Context context) {
        this.historiqueListeCourse = historiqueListeCourse;
        this.context = context;
    }

    @Override
    public int getCount() {
        return historiqueListeCourse.size();
    }

    @Override
    public Object getItem(int position) {
        return historiqueListeCourse.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_text_area_history, null);
        }

        TextView listItemText = view.findViewById(R.id.name);
        listItemText.setText(historiqueListeCourse.get(position).getDenomination());

        TextView listItemQt = view.findViewById(R.id.qtArea);
        listItemQt.setText(String.valueOf(historiqueListeCourse.get(position).getQuantite()));

        TextView listItemDate = view.findViewById(R.id.date_text);
        listItemDate.setText(String.valueOf(historiqueListeCourse.get(position).getDoneDate()));

        return view;
    }
}
