package com.ichirotech.module1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements MyAsyncCallback {
    public String TAG = "Demo";
    @BindView(R.id.rvCatagory)
    RecyclerView rvCatagory;
    AnimeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new AnimeAdapter(this);
        String url1 = "https://animeyou.net/api/home.php";
        DemoAsync demoAsync = new DemoAsync(this);
        demoAsync.execute(url1);





    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(ArrayList<Anime> animes) {
        rvCatagory.setLayoutManager(new LinearLayoutManager(this));
        adapter.setListAnime(animes);
        rvCatagory.setAdapter(adapter);
    }

    private static class DemoAsync extends AsyncTask<String, Void, ArrayList<Anime>> {

        WeakReference<MyAsyncCallback> listen;

        public DemoAsync(MyAsyncCallback listen) {
            this.listen = new WeakReference<>(listen);
        }

        @Override
        protected ArrayList<Anime> doInBackground(String... strings) {
            String url1 = "https://animeyou.net/api/home.php";
            final ArrayList<Anime> animes = new ArrayList<>();
            SyncHttpClient client = new SyncHttpClient();

            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            client.get(url1, new AsyncHttpResponseHandler() {


                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String hasil = new String(responseBody);
                        JSONObject jsonData = new JSONObject(hasil);
                        JSONArray jsonArray = jsonData.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject animeObj = jsonArray.getJSONObject(i);
                            Anime anime = new Anime(animeObj);
                            animes.add(anime);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Log.d("Tag", "onFailure: " + statusCode);
                }
            });
            return animes;
        }

        @Override
        protected void onPostExecute(ArrayList<Anime> anime) {
            super.onPostExecute(anime);
            MyAsyncCallback listener = this.listen.get();
            if (listener != null) {
                listener.onPostExecute(anime);


            }

        }
    }
}

interface MyAsyncCallback {
    void onPreExecute();

    void onPostExecute(ArrayList<Anime> animes);
}