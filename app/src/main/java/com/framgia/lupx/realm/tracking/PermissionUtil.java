package com.framgia.lupx.realm.tracking;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LUPX on 4/24/2016.
 */
public class PermissionUtil {
    public static boolean isUnderMarshmallow() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
    }

    public static boolean verify(Activity activity, String[] permissions, final int
        PERMISSIONS_REQUEST_ID, String msg) {
        if (isUnderMarshmallow()) {
            return true;
        }
        boolean shouldShowRequest = false;
        List<String> pendingPermission = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            int check = ContextCompat.checkSelfPermission(activity, permissions[i]);
            if (check != PackageManager.PERMISSION_GRANTED) {
                pendingPermission.add(permissions[i]);
                if (shouldShowRequest == false) {
                    boolean should = ActivityCompat.shouldShowRequestPermissionRationale
                        (activity, permissions[i]);
                    if (should) {
                        shouldShowRequest = true;
                    }
                }
            }
        }
        int denyPermissionLength = pendingPermission.size();
        String[] denyPermissions = new String[denyPermissionLength];
        for (int i = 0; i < denyPermissionLength; i++) {
            denyPermissions[i] = pendingPermission.get(i);
        }
        if (denyPermissionLength > 0) {
            if (shouldShowRequest) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("PERMISSIONS REQUEST");
                builder.setMessage(msg);
                builder.setPositiveButton("OK", null);
                builder.show();
            } else {
                ActivityCompat.requestPermissions(activity, denyPermissions, PERMISSIONS_REQUEST_ID);
            }
            return false;
        } else {
            return true;
        }
    }
}