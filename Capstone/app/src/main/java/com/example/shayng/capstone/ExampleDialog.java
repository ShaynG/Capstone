package com.example.shayng.capstone;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Context;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


import java.util.Date;


public class ExampleDialog extends AppCompatDialogFragment {

    private EditText editTitle;
    private TimePicker editStartTime;
    private Spinner spinner;
    private DatePicker editDate;
    private TimePicker editEndTime;
    private EditText tellMeMore;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());



            final LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.create_dialog,null);
            editStartTime = view.findViewById(R.id.startTimePicker);
            editStartTime.setIs24HourView(false);
            editEndTime = view.findViewById(R.id.endTimePicker);
            editDate = view.findViewById(R.id.datePicker);
            editTitle = view.findViewById(R.id.editName);
            spinner = view.findViewById(R.id.spinner);
            tellMeMore = view.findViewById(R.id.descriptionBox);


            editDate.setMinDate(System.currentTimeMillis());
            editDate.setMaxDate(System.currentTimeMillis()+604800000);



            builder.setView(view)
            .setTitle("Create New Event")
            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String title="ok";
                    int hour=0;
                    int minute=0;
                    int endHour=0;
                    int endMinute=0;
                    String AMPM="";
                    String spin="";
                    String description="";

                   // DateTimeFormatter dtf = new DateTimeFormatter("MM:dd:YY");
                    Calendar start = Calendar.getInstance();
                    Calendar end = Calendar.getInstance();
                    start.set(editDate.getYear(),editDate.getMonth(),editDate.getDayOfMonth(),editStartTime.getHour(),editStartTime.getMinute());
                    end.set(editDate.getYear(),editDate.getMonth(),editDate.getDayOfMonth(),editEndTime.getHour(),editEndTime.getMinute());
                   // start.setTimeZone((Calendar.ZONE_OFFSET));
                    //Date daten = new Date();
                   // Time
                   // String date = editDate.getMonth() + " " + editDate.getDayOfMonth();
                   // editDate.get
                    start.getTimeInMillis();

                    if(title != null && title.length() > 0) {
                        title = editTitle.getText().toString();
                        hour = editStartTime.getHour() % 12 == 0? 12: editStartTime.getHour() % 12;
                        endHour = editEndTime.getHour() % 12 == 0? 12: editStartTime.getHour() % 12;
                        minute = editStartTime.getMinute();
                        endMinute = editEndTime.getMinute();
                        AMPM = editStartTime.getHour() - 12 < 0? "AM":"PM";
                        spin = spinner.getSelectedItem().toString();
                        description = tellMeMore.getText().toString();
                    }

                    Calendar now = Calendar.getInstance();

                    if(start.after(end)) {
                        Toast.makeText(inflater.getContext(), "Error: Start time must be before end time.", Toast.LENGTH_LONG).show();
                    }
                    else if(title.length()<1){
                        Toast.makeText(inflater.getContext(), "Error: Must put a title.", Toast.LENGTH_LONG).show();
                    }
                    else if(description.length()<1){
                        Toast.makeText(inflater.getContext(), "Error: Must put a description.", Toast.LENGTH_LONG).show();
                    }
                    else if(end.before(now)){
                        Toast.makeText(inflater.getContext(), "Error: Event can't be created in the past.", Toast.LENGTH_LONG).show();
                    }
                    else {
                        listener.applyMarker(title, start.getTimeInMillis()+"", end.getTimeInMillis()+"",spin, true, description);
                    }


                }
            });



            return builder.create();
    }

    public interface ExampleDialogListener{
        void applyMarker(String title, String startDate, String endDate, String place, boolean addData, String description);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
           throw new ClassCastException(context.toString() + "implement ExampleDialogListener");
        }

    }


}
