package com.karan.mapstedtestcase.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karan.mapstedtestcase.R;
import com.karan.mapstedtestcase.http.GsonConverter;
import com.karan.mapstedtestcase.model.BuildingModel;
import com.karan.mapstedtestcase.model.PurchasesModel;
import com.karan.mapstedtestcase.model.SessionInfoItemModel;
import com.karan.mapstedtestcase.model.UsageStatisticsModel;
import com.karan.mapstedtestcase.utils.ApiConstant;
import com.karan.mapstedtestcase.utils.AppLogger;
import com.karan.mapstedtestcase.utils.Utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.tv_date)
    protected TextView tv_date;
    @BindView(R.id.tv_manufacture)
    protected TextView tv_manufacture;
    @BindView(R.id.tv_category)
    protected TextView tv_category;
    @BindView(R.id.tv_state)
    protected TextView tv_state;
    @BindView(R.id.tv_country)
    protected TextView tv_country;
    @BindView(R.id.tv_item)
    protected TextView tv_item;
    @BindView(R.id.tv_building)
    protected TextView tv_building;
    @BindView(R.id.sp_category)
    protected Spinner sp_category;
    @BindView(R.id.sp_state)
    protected Spinner sp_state;
    @BindView(R.id.sp_country)
    protected Spinner sp_country;
    @BindView(R.id.sp_item)
    protected Spinner sp_item;
    @BindView(R.id.sp_manufacture)
    protected Spinner sp_manufacture;

    private Context mContext = this;
    private final String TAG = MainActivity.class.getSimpleName();
    private BuildingModel[] building_data;
    private UsageStatisticsModel[] usage_data;
    private HashMap<Integer, Double> building_cost = new HashMap<Integer, Double>();
    private HashMap<Integer, Integer> item_count = new HashMap<Integer, Integer>();
    private HashMap<Integer, Double> category_cost = new HashMap<Integer, Double>();
    private HashMap<String, Double> country_cost = new HashMap<String, Double>();
    private HashMap<String, Double> state_cost = new HashMap<String, Double>();
    private HashMap<String, Double> manufacture_cost = new HashMap<String, Double>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
    }

    private void initview() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        tv_date.setText(dateFormat.format(date));
        getBuildingInfo();
    }

    private void setListener() {
        DecimalFormat df = new DecimalFormat("####0.00");
        sp_manufacture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AppLogger.LogE(TAG, ""+parent.getItemAtPosition(position) + manufacture_cost.get(parent.getItemAtPosition(position)));
                tv_manufacture.setText("$" + df.format(manufacture_cost.get(parent.getItemAtPosition(position))));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                AppLogger.LogE(TAG, ""+parent.getItemAtPosition(0) + manufacture_cost.get(parent.getItemAtPosition(0)));
                tv_manufacture.setText("$" + df.format(manufacture_cost.get(parent.getItemAtPosition(0))));
            }
        });

        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AppLogger.LogE(TAG, ""+parent.getItemAtPosition(position) + category_cost.get(parent.getItemAtPosition(position)));
                tv_category.setText("$" + df.format(category_cost.get(parent.getItemAtPosition(position))));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                AppLogger.LogE(TAG, ""+parent.getItemAtPosition(0) + category_cost.get(parent.getItemAtPosition(0)));
                tv_category.setText("$" + df.format(category_cost.get(parent.getItemAtPosition(0))));
            }
        });

        sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AppLogger.LogE(TAG, ""+parent.getItemAtPosition(position) + state_cost.get(parent.getItemAtPosition(position)));
                tv_state.setText("$" + df.format(state_cost.get(parent.getItemAtPosition(position))));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                AppLogger.LogE(TAG, ""+parent.getItemAtPosition(0) + state_cost.get(parent.getItemAtPosition(0)));
                tv_state.setText("$" + df.format(state_cost.get(parent.getItemAtPosition(0))));
            }
        });

        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AppLogger.LogE(TAG, ""+parent.getItemAtPosition(position) + country_cost.get(parent.getItemAtPosition(position)));
                tv_country.setText("$" + df.format(country_cost.get(parent.getItemAtPosition(position))));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                AppLogger.LogE(TAG, ""+parent.getItemAtPosition(0) + country_cost.get(parent.getItemAtPosition(0)));
                tv_country.setText("$" + df.format(country_cost.get(parent.getItemAtPosition(0))));
            }
        });

        sp_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AppLogger.LogE(TAG, ""+parent.getItemAtPosition(position) + item_count.get(parent.getItemAtPosition(position)));
                tv_item.setText("" + item_count.get(parent.getItemAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                AppLogger.LogE(TAG, ""+parent.getItemAtPosition(0) + item_count.get(parent.getItemAtPosition(0)));
                tv_item.setText("" + item_count.get(parent.getItemAtPosition(0)));
            }
        });

        tv_building.setText(building_data[Utility.sortMapByValues(building_cost)-1].getBuilding_name());
    }

    private void getBuildingInfo() {
        if (Utility.isInternetAvailable(mContext)) {
            String url = ApiConstant.BUILDING_URL;
            RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());

            final StringRequest jsonRequest = new StringRequest(Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        if (!Utility.isNullOrEmpty(response)) {
                            AppLogger.LogE(TAG, response);
                            building_data = GsonConverter.getInstance().decodeFromJsonString(response, BuildingModel[].class);
                            getPurchaseInfo();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    try {
                        AppLogger.LogE(TAG, error.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
            mQueue.add(jsonRequest);
        }
    }

    private void getPurchaseInfo() {
        if (Utility.isInternetAvailable(mContext)) {
            String url = ApiConstant.ANALYTIC_URL;
            RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());

            final StringRequest jsonObjectRequest = new StringRequest
                    (Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (!Utility.isNullOrEmpty(response)) {
                                AppLogger.LogE(TAG, response);
                                usage_data = GsonConverter.getInstance().decodeFromJsonString(response, UsageStatisticsModel[].class);
                                calculatingCost();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            AppLogger.LogE(TAG, error.getMessage());
                        }
                    });
            mQueue.add(jsonObjectRequest);
        }
    }

    private void calculatingCost() {
        if (usage_data != null && usage_data.length > 0) {
            for (int i = 0; i < usage_data.length; i++) {
                String manufacturer = usage_data[i].getManufacturer();
                Double manufacture_total_cost = 0.0;
                SessionInfoItemModel[] data = usage_data[i].getUsage_statistics().getSession_infos();
                for (int j = 0; j < data.length; j++) {
                    Integer building_id = data[j].getBuilding_id();
                    Double total_cost = 0.0;
                    for (int k = 0; k < data[j].getPurchases().length; k++) {
                        PurchasesModel key = data[j].getPurchases()[k];
                        if (item_count.containsKey(key.getItem_id())) {
                            item_count.put(key.getItem_id(), item_count.get(key.getItem_id()) + 1);
                        } else {
                            item_count.put(key.getItem_id(), 1);
                        }

                        if (category_cost.containsKey(key.getItem_category_id())) {
                            category_cost.put(key.getItem_category_id(), category_cost.get(key.getItem_category_id()) + key.getCost());
                        } else {
                            category_cost.put(key.getItem_category_id(), key.getCost());
                        }
                        total_cost += key.getCost();
                    }

                    if (building_cost.containsKey(building_id)) {
                        building_cost.put(building_id, building_cost.get(building_id) + total_cost);
                    } else {
                        building_cost.put(building_id, total_cost);
                    }

                    if (country_cost.containsKey(building_data[building_id - 1].getCountry())) {
                        country_cost.put(building_data[building_id - 1].getCountry(), country_cost.get(building_data[building_id - 1].getCountry()) + total_cost);
                    } else {
                        country_cost.put(building_data[building_id - 1].getCountry(), total_cost);
                    }

                    if (state_cost.containsKey(building_data[building_id - 1].getState())) {
                        state_cost.put(building_data[building_id - 1].getState(), state_cost.get(building_data[building_id - 1].getState()) + total_cost);
                    } else {
                        state_cost.put(building_data[building_id - 1].getState(), total_cost);
                    }

                    manufacture_total_cost += total_cost;
                }

                if (manufacture_cost.containsKey(manufacturer)) {
                    manufacture_cost.put(manufacturer, manufacture_cost.get(manufacturer) + manufacture_total_cost);
                } else {
                    manufacture_cost.put(manufacturer, manufacture_total_cost);
                }
            }

            loadData();
        }
    }

    private void loadData() {
        if (manufacture_cost != null) {
            sp_manufacture.setAdapter(Utility.setSpinnerAdapter(mContext, Utility.sortByKey(new ArrayList<>(manufacture_cost.keySet()))));
        }
        if (country_cost != null) {
            sp_country.setAdapter(Utility.setSpinnerAdapter(mContext, Utility.sortByKey(new ArrayList<>(country_cost.keySet()))));
        }
        if (state_cost != null) {
            sp_state.setAdapter(Utility.setSpinnerAdapter(mContext, Utility.sortByKey(new ArrayList<>(state_cost.keySet()))));
        }
        if (item_count != null) {
            sp_item.setAdapter(Utility.setSpinnerAdapter(mContext, new ArrayList<>(item_count.keySet())));
        }
        if (category_cost != null) {
            sp_category.setAdapter(Utility.setSpinnerAdapter(mContext, new ArrayList<>(category_cost.keySet())));
        }

        setListener();
    }
}
