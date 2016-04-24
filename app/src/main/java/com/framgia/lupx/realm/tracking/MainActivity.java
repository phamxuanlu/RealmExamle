package com.framgia.lupx.realm.tracking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private final int LOCATION_PERMISSION_REQUEST = 1;
    private Realm realm;
    private RecyclerView rcvLocations;
    private TrackingLocationAdapter adapter;
    private RealmResults<TrackLocation> results;
    private String[] PERMISSIONS = new String[]{
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(this, TrackingService.class);
                    startService(intent);
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean check = PermissionUtil.verify(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST,
            "This app need " +
                "access your location");
        if (check) {
            Intent intent = new Intent(this, TrackingService.class);
            startService(intent);
        }
        realm = Realm.getInstance(App.getRealmConfig());
        results = realm.where(TrackLocation.class).findAll();
        results.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
        });
        initViews();
    }

    private void initViews() {
        rcvLocations = (RecyclerView) findViewById(R.id.listLocation);
        rcvLocations.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrackingLocationAdapter(this, results);
        rcvLocations.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
