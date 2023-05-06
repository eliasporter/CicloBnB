package com.example.ciclobnb;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class CustomCalendarAdapter extends ArrayAdapter<Date> {
    private final LayoutInflater inflater;
    private final HashSet<Date> eventDays;
    private final int colorToday;

    public CustomCalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays, int colorToday) {
        super(context, R.layout.calendar_day, days);
        this.eventDays = eventDays;
        this.inflater = LayoutInflater.from(context);
        this.colorToday = colorToday;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // inflate calendar day layout if necessary
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.calendar_day, parent, false);
        }

        // get date object for this position
        Date date = getItem(position);

        // set day text
        TextView dayText = convertView.findViewById(R.id.calendar_day_text);
        dayText.setText(String.valueOf(date.getDate()));

        // set font color
        if (date.equals(Calendar.getInstance().getTime())) {
            dayText.setTextColor(colorToday);
        } else {
            dayText.setTextColor(Color.BLACK);
        }

        // set background color
        if (eventDays.contains(date)) {
            convertView.setBackgroundColor(Color.YELLOW);
        } else {
            convertView.setBackgroundColor(Color.WHITE);
        }

        return convertView;
    }
}
