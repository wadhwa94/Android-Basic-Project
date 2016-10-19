package com.example.jawadh.helloworld;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private RestAPI clientRestAPI;
    private final String API_KEY = "YWRtaW46cGFzc3dvcmQxMjM=";
    private final String API_BASE_URL = "http://10.0.2.2:8000/";
    public AsyncHttpClient client = new AsyncHttpClient();
    public ListView listView;
    public ArrayAdapter adapter;
    public ArrayList<String> stringArray;
    private Toolbar toolBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolBar);
        setTitle("Main Activity");
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String[] items = {};
        stringArray = new ArrayList<>(Arrays.asList(items));
        Button AddUser = (Button) findViewById(R.id.Add_User);
        listView = (ListView) findViewById(R.id.listView);

//        stringArray = {};//new String[12];
        for (int i = 0; i < 10; i++) {
            // JSONObject j = response.getJSONObject(i);

            stringArray.add(i + "List ITeM");
        }
        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_listview, stringArray);
        listView.setAdapter(adapter);

        AddUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callAddUserOnClick();
            }
        });
        Button GetTop10 = (Button) findViewById(R.id.GetTop10);
        GetTop10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callGetTop10UserOnClick();
            }
        });

        Button GetAllUsers = (Button) findViewById(R.id.GetUsers);
        GetAllUsers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callGetAllUsersOnClick();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if(id == R.id.action_settings){
            Toast.makeText(this,"Hey you just hit " + item.getTitle(),Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.navigate){
            startActivity(new Intent(this,SubActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    private void callAddUserOnClick() {


        // clientRestAPI = new RestAPI();
        String url = getApiUrl("users/");
        //RequestParams params = new RequestParams("apikey", API_KEY);
        client.get("http://djangoservice.azurewebsites.net/snippets/", new JsonHttpResponseHandler() {

            @Override
            public void onStart() {
                Toast.makeText(getApplicationContext(), " ------ Started ---- ", Toast.LENGTH_SHORT).show();
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Toast.makeText(getApplicationContext(), "Success with JSOn Object ", Toast.LENGTH_SHORT).show();
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Toast.makeText(getApplicationContext(), " sucess with JSOn ARRay ", Toast.LENGTH_SHORT).show();
                // Pull out the first event on the public timeline
                try {

                    //     JSONObject firstEvent = response.get(0);


                    for (int i = 10; i < 12; i++) {
                        JSONObject j = response.getJSONObject(i - 10);
                        stringArray.add(j.getString("url"));
                    }
//                    for(int i=12;i<15;i++){
//                        stringArray[i] = "j.getString";
//
//                    }
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(), stringArray.get(0) + stringArray.get(1), Toast.LENGTH_SHORT).show();

                } catch (org.json.JSONException e) {
                    Toast.makeText(getApplicationContext(), " exception ", Toast.LENGTH_SHORT).show();

                }
            }
//           @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                Toast.makeText(getApplicationContext(),"Add User Clicked" + statusCode + " response =  " + response + " headers = " + headers ,Toast.LENGTH_SHORT).show();
//
//                }


            public void onFailure(int statusCode, Header[] headers, JSONObject errorResponse, Throwable e) {
                Toast.makeText(getApplicationContext(), "Add User Clicked" + statusCode + " response =  " + errorResponse.toString() + " headers = " + headers, Toast.LENGTH_SHORT).show();
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                Toast.makeText(getApplicationContext(), "Add User Clicked- Retry Count:" + retryNo, Toast.LENGTH_SHORT).show();
                // called when request is retried
            }


        });

//        clientRestAPI.getAllUsers(new JsonHttpResponseHandler() {
//
//        });

        Toast.makeText(getApplicationContext(), "Add User Clicked - LAst", Toast.LENGTH_SHORT).show();
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    private void callGetTop10UserOnClick() {
        Toast.makeText(getApplicationContext(), "Get Top 10 Users Clicked", Toast.LENGTH_SHORT).show();
    }

    private void callGetAllUsersOnClick() {
        Toast.makeText(getApplicationContext(), "Get all Users Clicked", Toast.LENGTH_SHORT).show();
    }

}
