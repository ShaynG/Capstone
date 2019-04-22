package com.example.shayng.capstone;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Context;
import android.widget.TimePicker;

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



            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.create_dialog,null);
            editStartTime = view.findViewById(R.id.startTimePicker);
            editStartTime.setIs24HourView(false);
            editDate = view.findViewById(R.id.datePicker);
            editTitle = view.findViewById(R.id.editName);
            spinner = view.findViewById(R.id.spinner);
            tellMeMore = view.findViewById(R.id.descriptionBox);

            //editDate.setMaxDate();

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
                    String spin="ok";
                    String description="ok";

                    //Date daten = new Date();
                   // Time
                    //String date = editDate.getMonth() + " " + editDate.getDayOfMonth();


                    if(title != null && title.length() > 0) {
                        title = editTitle.getText().toString();
                        hour = editStartTime.getHour();
                        minute = editStartTime.getMinute();
                        spin = spinner.getSelectedItem().toString();
                        description = tellMeMore.getText().toString();
                    }
                        listener.applyMarker(title,hour + ":" + minute, hour + ":" + minute,spin, true, description);


                }
            });



            return builder.create();
    }

    public interface ExampleDialogListener{
        void applyMarker(String title, String date, String endDate, String place, boolean addData, String description);

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
