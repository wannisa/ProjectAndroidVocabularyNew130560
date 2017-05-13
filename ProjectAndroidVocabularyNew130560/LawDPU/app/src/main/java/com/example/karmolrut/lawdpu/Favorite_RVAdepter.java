package com.example.karmolrut.lawdpu;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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

public class Favorite_RVAdepter extends RecyclerView.Adapter<ItemViewHolder> {
    private static final String TAG = "RVAdapter";
    private List<CountryModel> mCountryModel;
    private List<CountryModel> mOriginalCountryModel;
    OkHttpClient okHttpClient = new OkHttpClient();
    private Context mContext;



    public Favorite_RVAdepter(List<CountryModel> mCountryModel) {
        this.mCountryModel = mCountryModel;
        this.mOriginalCountryModel = mCountryModel;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, int i) {
        final CountryModel model = mCountryModel.get(i);
        itemViewHolder.bind(model);

        itemViewHolder.starOOF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doProcess2(model.getId());


                Bundle bundle = new Bundle();
                v.getContext().startActivity(new Intent(v.getContext(),  Favorite.class).putExtras(bundle));
//                model.setFavo("true");
////                    itemViewHolder.starOn = (ImageButton) itemViewHolder.starOn.findViewById(R.id.staron);
////                    itemViewHolder.starOOF = (ImageButton) itemViewHolder.starOOF.findViewById(R.id.staroof);
////
//                itemViewHolder.starOn.setVisibility(View.VISIBLE);
//                itemViewHolder.starOOF.setVisibility(View.INVISIBLE);

            }
        });

        itemViewHolder.starOn.setOnClickListener(new View.OnClickListener() {  //ปุ่มแจ้งเตือน
            @Override
            public void onClick(View v) {

                doProcess2(model.getId());
//                model.setFavo("false");
////                    itemViewHolder.starOn = (ImageButton) itemViewHolder.starOn.findViewById(R.id.staron);
////                    itemViewHolder.starOOF = (ImageButton) itemViewHolder.starOOF.findViewById(R.id.staroof);
////
//                itemViewHolder.starOn.setVisibility(View.INVISIBLE);
//                itemViewHolder.starOOF.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_row, null);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
//        int x;
//        try{
//            x = mCountryModel.size();
//        } catch (NullPointerException e) {
//            x = 0;
//        }
//        return x;
        return (null != mCountryModel ? mCountryModel.size() : 0);
    }
    public void doProcess(String id,String thai, String english, String favo){


        AsyncTaskCheckNotification asyncTaskCheckNotification = new AsyncTaskCheckNotification();
        asyncTaskCheckNotification.execute(id, thai, english, favo);
    }
    public class AsyncTaskCheckNotification extends AsyncTask<String, Void, Void> {  //การแจ้งเตือน
        @Override
        protected void onPostExecute(Void aVoid) {

        }
        @Override
        protected Void doInBackground(String... params) {
            FormBody.Builder builder = new FormBody.Builder();
            // Add Params to Builder
            builder.add("ID", params[0]);
            builder.add("THAI", params[1]);
            builder.add("ENG", params[2]);
            builder.add("FAVO", params[3]);
            RequestBody body = builder.build();
            Request request = new Request.Builder().url("http://anakpon.net/web/android/favorite.php").post(body).build();
            Response response;
            try {
                response = okHttpClient.newCall(request).execute();
                String result = response.body().string();
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(result);
                    // Query JSON tag: State
                    String SQL = jsonObject.getString("SQL");
                    Log.d(TAG, SQL);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void doProcess2(String id){


        AsyncTaskCheckNotification2 asyncTaskCheckNotification2 = new AsyncTaskCheckNotification2();
        asyncTaskCheckNotification2.execute(id);
    }
    public class AsyncTaskCheckNotification2 extends AsyncTask<String, Void, Void> {  //การแจ้งเตือน
        @Override
        protected void onPostExecute(Void aVoid) {

        }
        @Override
        protected Void doInBackground(String... params) {
            FormBody.Builder builder = new FormBody.Builder();
            // Add Params to Builder
            builder.add("ID", params[0]);
            RequestBody body = builder.build();
            Request request = new Request.Builder().url("http://anakpon.net/web/android/favoritedelete.php").post(body).build();
            Response response;
            try {
                response = okHttpClient.newCall(request).execute();
                String result = response.body().string();
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(result);
                    // Query JSON tag: State
                    String SQL = jsonObject.getString("SQL");
                    Log.d(TAG, SQL);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void setFilter(List<CountryModel> countryModels){
        mCountryModel = new ArrayList<>();
        mCountryModel.addAll(countryModels);
        notifyDataSetChanged();
    }

}
