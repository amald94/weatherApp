package app.weatheapp.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by devkkda on 7/22/2017.
 */

public class weatherModel {

    private static List<String> listTotTemp = new ArrayList<String>();
    private static List<String> listTotCondi = new ArrayList<String>();
    private static String[] tempValues;
    private static String[] descValues;
    private static String errorCode;
    private static String cityName;
    private static String countryName;

    public weatherModel(){

    }

    public weatherModel(Object response) {

        try {
            JSONObject obj = new JSONObject(String.valueOf(response));

            List<String> listTemp = new ArrayList<String>();
            List<String> listCondi = new ArrayList<String>();

            this.errorCode = obj.getString("cod");
            listTotTemp.clear();
            listTotCondi.clear();

            JSONObject jtemp = obj.getJSONObject("city");
            this.cityName = jtemp.getString("name");
            this.countryName = jtemp.getString("country");
            JSONArray arrayTemp = obj.getJSONArray("list");
            for (int i = 0; i < arrayTemp.length(); i++) {
                listTemp.add(arrayTemp.getJSONObject(i).getString("temp"));
                listCondi.add(arrayTemp.getJSONObject(i).getString("weather"));
            }

            for (int x = 0; x < listTemp.size(); x++) {

                this.tempValues = listTemp.get(x).split(",");
                this.listTotTemp.add(tempValues[1]);
                this.listTotTemp.add(tempValues[2]);
                this.descValues = listCondi.get(0).split(",");
                this.listTotCondi.add(descValues[1]);
                this.listTotCondi.add(descValues[2]);

            }

            Log.d("max temp", String.valueOf(listTotTemp));
            Log.d("max temp", String.valueOf(listTotCondi));


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static String getCityName() {
        return cityName;
    }

    public static String getCountryName() {
        return countryName;
    }

    public static String getErrorCode() {
        return errorCode;
    }

    public List<String> getListTotTemp() {
        return listTotTemp;
    }

    public List<String> getListTotCondi() {
        return listTotCondi;
    }
}
