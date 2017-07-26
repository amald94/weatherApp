package app.weatheapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.weatheapp.R;
import app.weatheapp.activities.MainActivity;
import app.weatheapp.activities.UpdateLocation;
import app.weatheapp.config.Utils;
import app.weatheapp.model.weatherModel;

/**
 * Created by devkkda on 7/20/2017.
 */

public class currentTempFragments extends Fragment {

    private weatherModel wc;
    private String td = "";
    private String tdtwo = "";
    private Utils ut;
    private int tempN, tempT;
    private ImageView sett;
    private TextView temp,cityName,ccname,tempSymb,weathertdyMain,weathertmrwMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_current, container, false);
        wc = new weatherModel();
        updateUi(view);


        return view;
    }

    private void updateUi(View view) {

        String todayVmax="";
        String todayVmin="";
        ut = new Utils();
        temp = (TextView) view.findViewById(R.id.tempNowMain);
        cityName = (TextView) view.findViewById(R.id.cityName);
        ccname = (TextView) view.findViewById(R.id.countryName);
        tempSymb = (TextView) view.findViewById(R.id.tempSymb);
        sett = (ImageView) view.findViewById(R.id.gearImg);

        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent Intent = new Intent(getActivity(), UpdateLocation.class);
                startActivity(Intent);

            }
        });

        weathertdyMain = (TextView) view.findViewById(R.id.weathertdyMain);
        weathertmrwMain = (TextView) view.findViewById(R.id.weathertmrwMain);
        try {
            td = wc.getListTotTemp().get(1);
            //tdtwo = td.substring(td.lastIndexOf(":") + 1);
            tempN = (int) ut.convertTemp(td);
            temp.setText(tempN + "");
            //tempSymb.setText("\u2103");
            cityName.setText(wc.getCityName());
            ccname.setText(wc.getCountryName());
            todayVmax = tempN+"";
            td = wc.getListTotTemp().get(0);
            tempN = (int) ut.convertTemp(td);
            todayVmin = tempN+"";
            weathertdyMain.setText(todayVmin+"/"+todayVmax);
            //tomorrow
            td = wc.getListTotTemp().get(3);
            tempN = (int) ut.convertTemp(td);
            todayVmax = tempN+"";
            td = wc.getListTotTemp().get(2);
            tempN = (int) ut.convertTemp(td);
            todayVmin = tempN+"";
            weathertmrwMain.setText(todayVmin+"/"+ todayVmax);


        } catch (Exception e) {

            Log.d("exception", e.toString());
        }


    }


}
