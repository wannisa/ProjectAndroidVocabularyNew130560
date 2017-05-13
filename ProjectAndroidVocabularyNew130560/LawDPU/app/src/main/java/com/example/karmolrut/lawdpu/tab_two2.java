package com.example.karmolrut.lawdpu;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class tab_two2 extends Fragment implements SearchView.OnQueryTextListener {

    private static final String TAG = "tab_two";
    private static final String URL_GET_DATA = "http://anakpon.net/web/android/summarystudenttest.php";
    private RecyclerView recyclerview;
    private List<CountryModel> mCountryModel;
    private RVAdapter2 adapter;
    OkHttpClient okHttpClient = new OkHttpClient();
    String StatusCode, SQL,ID, StudentName, ActivityName;
    String UserID = "test"; //รับมาจาก Shared Preference

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.lis_law2, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);

        GetData getPatientData = new GetData();
        getPatientData.execute(UserID);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tabone, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        adapter.setFilter(mCountryModel);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when exv
                        return true; // Return true to expand action view
                    }
                });
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<CountryModel> filteredModelList = filter(mCountryModel, newText);
        adapter.setFilter(filteredModelList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    private List<CountryModel> filter(List<CountryModel> models, String query) {
        query = query.toLowerCase();

        final List<CountryModel> filteredModelList = new ArrayList<>();
        for (CountryModel model : models) {
            final String text = model.getThai().toLowerCase();
            final String text1 = model.getEnglish().toLowerCase(); //search
            if (text.contains(query)) {
                filteredModelList.add(model);
            }else if(text1.contains(query)){
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    public class GetData extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d(TAG, "onPostExecute() method running");
            Log.d(TAG, SQL);
            Log.d(TAG, StatusCode);
//            Log.d(TAG, ID);
            setHasOptionsMenu(true);
            adapter = new RVAdapter2(mCountryModel);
            recyclerview.setAdapter(adapter);



        }

        @Override
        protected Void doInBackground(String... params) {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("UserID", params[0]);
            RequestBody body = builder.build();
            Request request = new Request.Builder().url(URL_GET_DATA).post(body).build();
            Response response = null;
            try {
                try {

                    response = okHttpClient.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String result = response.body().string();
                JSONObject jsonObject;

                try {
                    jsonObject = new JSONObject(result);
                    StatusCode = jsonObject.getString("StatusCode");
                    SQL = jsonObject.getString("SQL");
                    //ID = jsonObject.getString("id");

                    JSONArray jsonArray = jsonObject.getJSONArray("Data");
                    mCountryModel = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        CountryModel item = new CountryModel();
                        item.setId(object.getString("id"));
                        item.setThai(object.getString("thai"));
                        item.setEnglish(object.getString("english"));
                        item.setFavo(object.getString("favo"));
                        mCountryModel.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
