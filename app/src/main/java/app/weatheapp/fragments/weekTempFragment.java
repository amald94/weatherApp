package app.weatheapp.fragments;


import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import app.weatheapp.R;
import app.weatheapp.adapter.weatherAdapter;
import app.weatheapp.model.weatherModel;

/**
 * Created by devkkda on 7/20/2017.
 */

public class weekTempFragment extends Fragment {

    private RecyclerView recyclerView;
    private weatherAdapter adapter;
    private List<String> wList;
    private List<String> wListDesc;
    private List<String> dateList;
    private weatherModel wModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_week, container, false);

        updateUi(view);

        return view;
    }

    private void updateUi(View view) {


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        wList = new ArrayList<>();
        wListDesc = new ArrayList<>();
        dateList = new ArrayList<>();
        adapter = new weatherAdapter(getActivity(), wList, wListDesc,dateList);
        wModel = new weatherModel();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareData();

    }

    private void prepareData() {


        for(int i =0; i<wModel.getListTotTemp().size(); i++){

            wList.add(wModel.getListTotTemp().get(i));
            wListDesc.add(wModel.getListTotCondi().get(i));

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd-MMM-yyyy");
            // for (int i = 1; i < n; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.add(Calendar.DATE, i);
            String day = sdf.format(calendar.getTime());
            Log.i("dateee", day);
            //  }
            dateList.add(day);

        }





        //   wList.add(wModel.getListTotTemp());
        adapter.notifyDataSetChanged();
    }
}
