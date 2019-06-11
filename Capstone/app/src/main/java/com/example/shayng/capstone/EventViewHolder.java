package com.example.shayng.capstone;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventViewHolder extends RecyclerView.Adapter<EventViewHolder.ContactViewHolder> implements Filterable {
    private List<Event> eventList;
    private List<Event> eventsListFilter;

    public EventViewHolder(List<Event> eventList) {
        this.eventList = eventList;
        eventsListFilter = new ArrayList<>(eventList);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {

        Event ci = eventList.get(i);

        Calendar editedStart = Calendar.getInstance();
        editedStart.setTimeInMillis(Long.parseLong(ci.getStartTime()));

        Calendar editedEnd = Calendar.getInstance();
        editedEnd.setTimeInMillis(Long.parseLong(ci.getEndTime()));

        int hour = editedStart.getTime().getHours() % 12 == 0? 12: editedStart.getTime().getHours() % 12;
        int endHour = editedEnd.getTime().getHours() % 12 == 0? 12: editedEnd.getTime().getHours() % 12;
        String minute = editedStart.getTime().getMinutes()==0? "00": editedStart.getTime().getMinutes()+"";
        if(minute.length()==1) minute = "0" + minute;
        String endMinute = editedEnd.getTime().getMinutes()==0? "00": editedEnd.getTime().getMinutes()+"";
        if(endMinute.length()==1) endMinute = "0" + endMinute;
        String AMPM1 = editedStart.getTime().getHours() - 12 < 0? "AM":"PM";
        String AMPM2 = editedEnd.getTime().getHours() - 12 < 0? "AM":"PM";

        String toDate;
        //= (editedStart.getTime().getMonth()+1) +" "+ editedStart.getTime().getDate()+" ";
        switch(editedStart.getTime().getMonth()){
            case Calendar.JANUARY: toDate="January ";
                break;
            case Calendar.FEBRUARY: toDate="February ";
                break;
            case Calendar.MARCH: toDate="March ";
                break;
            case Calendar.APRIL: toDate="April ";
                break;
            case Calendar.MAY: toDate="May ";
                break;
            case Calendar.JUNE: toDate="June ";
                break;
            case Calendar.JULY: toDate="July ";
                break;
            case Calendar.AUGUST: toDate="August ";
                break;
            case Calendar.SEPTEMBER: toDate="September ";
                break;
            case Calendar.OCTOBER: toDate="October ";
                break;
            case Calendar.NOVEMBER: toDate="November ";
                break;
            case Calendar.DECEMBER: toDate="December ";
                break;
            default: toDate = "May ";
        }

        toDate+=editedStart.getTime().getDate()+", ";



        String toTime = toDate + hour+":"+minute+AMPM1 + " to " + endHour+":"+endMinute+AMPM2;

        //contactViewHolder.favButton.setImageIcon();
        contactViewHolder.vLocation.setText(ci.getLocation());
        contactViewHolder.vTime.setText(toTime);
        //contactViewHolder.vLink.setText("www.csuci.edu");
        contactViewHolder.vTitle.setText(ci.getTitle());
        contactViewHolder.vDescription.setText(ci.getDescription());


        //if(ci.getUrlLink().equals("")) contactViewHolder.vLink.setVisibility(View.GONE);

    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vLocation;
        protected TextView vTime;
        protected TextView vLink;
        protected TextView vLinkHeader;
        protected TextView vTitle;
        protected TextView vDescription;
        private ImageButton favButton;

        public ContactViewHolder(View v) {
            super(v);
            favButton = v.findViewById(R.id.favButton);
            favButton.bringToFront();
            vLocation = v.findViewById(R.id.cardLocationValue);
            vTime = v.findViewById(R.id.cardTimeValue);
            vLink = v.findViewById(R.id.cardLinkValue);
            vLinkHeader = v.findViewById(R.id.edEmail);
            vTitle = v.findViewById(R.id.title);
            vDescription = v.findViewById(R.id.cardDescValue);
        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.single_card, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    @Override
    public Filter getFilter() {
        return eventsFilter;
    }

    private Filter eventsFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<Event> filteredEvents = new ArrayList<>();

            if(constraint == null || constraint.length()==0){
                filteredEvents.addAll(eventsListFilter);
            }
            else{
                String filter = constraint.toString().toLowerCase().trim();

                for(Event e : eventsListFilter){

                    if(e.getTitle().toLowerCase().contains(filter) || e.getDescription().toLowerCase().contains(filter) || e.getLocation().toLowerCase().contains(filter)){
                        filteredEvents.add(e);
                    }
                }

            }

            FilterResults results = new FilterResults();

            results.values = filteredEvents;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            eventList.clear();
            eventList.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };
}
