package app.weatheapp.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import app.weatheapp.R;
import app.weatheapp.config.volleyJSONObjectRequest;
import app.weatheapp.config.volleyRequestQueue;
import app.weatheapp.config.weatherCofig;
import app.weatheapp.model.weatherModel;

/**
 * Created by devkkda on 7/22/2017.
 */

public class SplashScreen extends AppCompatActivity implements Response.Listener,
        Response.ErrorListener {

    public static final String REQUEST_TAG = "weatherInfo";
    public static final String MY_PREFS_NAME = "weatherLocation";
    private RequestQueue mQueue;
    private AVLoadingIndicatorView indi;
    private weatherCofig wc;
    private weatherModel wModel;
    private Button buttonSeach;
    private EditText loc, coucode;
    private TextView txt_head;
    private String city = "";
    private String country = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        buttonSeach = (Button) findViewById(R.id.buttonSeach);
        loc = (EditText) findViewById(R.id.input_city);
        txt_head = (TextView) findViewById(R.id.txt_head);
        indi = (AVLoadingIndicatorView) findViewById(R.id.loadingView);
        coucode = (EditText) findViewById(R.id.input_country);
        buttonSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                city = loc.getText().toString();
                country = coucode.getText().toString();
                if(!city.equalsIgnoreCase("") && !country.equalsIgnoreCase("")) {
                    searchLocation(city, country);
                }else {

                    Snackbar snackbar = Snackbar
                            .make(buttonSeach, "Check Input", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                    snackbar.show();


                }

            }
        });
        updateUi();

    }

    private void updateUi() {

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String storedCity = prefs.getString("cityName", null);
        String storedCountry = prefs.getString("countryName", null);

        if (storedCity != null && storedCountry != null) {
            if(!storedCity.equalsIgnoreCase("") && !storedCountry.equalsIgnoreCase("")) {
                loc.setText(storedCity);
                coucode.setText(storedCountry);
                txt_head.setVisibility(View.INVISIBLE);
                loc.setEnabled(false);
                coucode.setEnabled(false);
                buttonSeach.setVisibility(View.GONE);
                searchLocation(storedCity, storedCountry);
            }

        } else {

        }


    }

    private void searchLocation(String one, String two) {

        indi.setVisibility(View.VISIBLE);
        indi.show();


        mQueue = volleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();
        String url = wc.SERVER_PATH + one + "," + two + wc.API_KEY;
        Log.d("requestUrl", url);
        final volleyJSONObjectRequest jsonRequest = new volleyJSONObjectRequest(Request.Method
                .GET, url,
                new JSONObject(), this, this);
        jsonRequest.setTag(REQUEST_TAG);

        mQueue.add(jsonRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Instantiate the RequestQueue.

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        indi.hide();

        Snackbar snackbar = Snackbar
                .make(buttonSeach, "something went wrong", Snackbar.LENGTH_LONG)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
        snackbar.show();

    }

    @Override
    public void onResponse(Object response) {
        Log.d("Response is: ", response + "");
        wModel = new weatherModel(response);
        Log.d("vaa: ", wModel.getListTotTemp() + "");
        Log.d("vaaxx: ", wModel.getErrorCode() + "");
        Log.d("vaaxx: ", wModel.getCityName() + "");
        Log.d("vaaxx: ", wModel.getCountryName() + "");

        if (wModel.getErrorCode().equalsIgnoreCase("200")) {

            updateCache();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    indi.hide();
                    Intent Intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(Intent);
                    finish();
                }
            }, 5000);


        } else {

            Snackbar snackbar = Snackbar
                    .make(buttonSeach, "something went wrong", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.show();
        }
    }

    private void updateCache() {

        if(!city.equalsIgnoreCase("") && !country.equalsIgnoreCase("")) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("cityName", city);
            editor.putString("countryName", country);
            editor.apply();
        }
    }
}
