package com.example.shayng.capstone;

import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;

    public CustomInfoWindowAdapter(Context context) {
        this.mContext = mContext;

        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    private void renderWindowText(Marker marker, View view){

        String title = marker.getTitle();
        String snippet = marker.getSnippet();
        TextView tvText1 = (TextView) view.findViewById(R.id.firstView);
        TextView tvText2 = (TextView) view.findViewById(R.id.secondView);

        if(!title.equals("")) tvText1.setText(title);
        if(!snippet.equals("")) tvText2.setText(snippet);

    }


    public CustomInfoWindowAdapter(View mWindow) {
        this.mWindow = mWindow;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        //rendowWindowText(marker,mWindow);
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        renderWindowText(marker, mWindow);
        return mWindow;
    }
}
