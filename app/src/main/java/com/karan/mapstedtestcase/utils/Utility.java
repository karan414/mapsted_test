package com.karan.mapstedtestcase.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.karan.mapstedtestcase.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class Utility {

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.equalsIgnoreCase("")
                || string.equalsIgnoreCase("null");
    }

    @SuppressWarnings("deprecation")
    public static boolean isInternetAvailable(@NonNull Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (NetworkInfo value : info)
                    if (value.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public static ArrayList<String> sortByKey(@NonNull ArrayList<String> keys) {
        Collections.sort(keys);
        return keys;
    }

    public static SpinnerAdapter setSpinnerAdapter(@NonNull Context mContext, @NonNull ArrayList<?> list) {
         return new ArrayAdapter<>(mContext, R.layout.row_spinner, R.id.tv_spinner, list);
    }

    public static int sortMapByValues(@NonNull HashMap<Integer, Double> map) {
        double maxValueInMap=(Collections.max(map.values()));
        for (HashMap.Entry<Integer, Double> entry : map.entrySet()) {
            if (entry.getValue()==maxValueInMap) {
                return entry.getKey();
            }
        }
        return 1;
    }
}
