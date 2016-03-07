package com.example.cuelogic.interview_test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cuelogic.interview_test.ApplicationController.AppController;
import com.example.cuelogic.interview_test.Model.Animal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainActivity extends Activity {
    private static String TAG = MainActivity.class.getSimpleName();
    // json response url
    private String urlJson = "http://192.168.10.104/imageData.php";
    private String jsonResponse = "";
    private TextView txtResponse;
    private List<Animal> animalsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResponse = (TextView) findViewById(R.id.txtResponse);
        makeJsonObjectRequest();
    }

    /**
     * Method to make json object request where json response starts wtih {
     */
    private void makeJsonObjectRequest() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
                urlJson, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    JSONArray animals = response.optJSONArray("animals");

                    for (int i = 0; i < animals.length(); i++) {
                        JSONObject animalsArr = (JSONObject) animals.get(i);
                        String name = animalsArr.getString("name");
                        String imgURL = animalsArr.getString("imgURL");

                        jsonResponse += "Name: " + name + "\n\n";
                        jsonResponse += "imgURL: " + imgURL + "\n\n";
                    }

                    txtResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
