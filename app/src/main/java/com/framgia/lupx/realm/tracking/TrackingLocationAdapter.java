package com.framgia.lupx.realm.tracking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmResults;

/**
 * Created by LUPX on 4/24/2016.
 */
public class TrackingLocationAdapter extends RecyclerView.Adapter<TrackingLocationAdapter.TrackingLocationViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private RealmResults<TrackLocation> results;

    public TrackingLocationAdapter(Context context, RealmResults<TrackLocation> results) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.results = results;
    }

    @Override
    public TrackingLocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_track_location, parent, false);
        return new TrackingLocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TrackingLocationViewHolder holder, int position) {
        TrackLocation location = results.get(position);
        holder.setLocation(location);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class TrackingLocationViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView latitude;
        private TextView longitude;
        private TextView speed;

        public void setLocation(TrackLocation location) {
            this.time.setText(String.valueOf(location.getTime()));
            this.latitude.setText(String.valueOf(location.getLatitude()));
            this.longitude.setText(String.valueOf(location.getLongitude()));
            this.speed.setText(String.valueOf(location.getSpeed()));
        }

        public TrackingLocationViewHolder(View v) {
            super(v);
            time = (TextView) v.findViewById(R.id.time);
            latitude = (TextView) v.findViewById(R.id.lat);
            longitude = (TextView) v.findViewById(R.id.lon);
            speed = (TextView) v.findViewById(R.id.speed);
        }
    }
}