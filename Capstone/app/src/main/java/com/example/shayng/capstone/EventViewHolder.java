package com.example.shayng.capstone;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;
import java.util.List;

public class EventViewHolder extends RecyclerView.Adapter <EventViewHolder.ContactViewHolder>{
    private List<Event> eventList;

    public EventViewHolder(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        Event ci = eventList.get(i);
        //contactViewHolder.favButton.setImageIcon();
        contactViewHolder.vLocation.setText(ci.getLocation());
        contactViewHolder.vTime.setText(ci.getStartTime());
        contactViewHolder.vLink.setText("www.csuci.edu");
        contactViewHolder.vTitle.setText(ci.getTitle());
        contactViewHolder.vDescription.setText(ci.getDescription());


       // if(ci.getUrlLink().equals("")) contactViewHolder.vLink.setVisibility(View.GONE);

    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vLocation;
        protected TextView vTime;
        protected TextView vLink;
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
}
