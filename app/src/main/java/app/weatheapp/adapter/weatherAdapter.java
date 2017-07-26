package app.weatheapp.adapter;

import android.content.Context;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import app.weatheapp.R;
import app.weatheapp.config.Utils;
import app.weatheapp.model.weatherModel;

import static android.content.ContentValues.TAG;

/**
 * Created by devkkda on 7/23/2017.
 */

public class weatherAdapter extends RecyclerView.Adapter<weatherAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> weatherList;
    private List<String> weatherDes;
    private List<String> dateList;
    private CardView cdView;
    private RelativeLayout rlyt_Item, topDescLyt;
    private TextView id_tdy, id_tmpTdy, id_decTdy,id_tdyDate,weekTdyDec;
    private TextView id_tdyTwo,id_tmpTdyTwo,id_decTdyTwo,txt_twoWea;
    String td = "";
    String tdtwo = "";
    String tdthree = "";
    Utils ut = new Utils();;
    int tempN, tempT;
    weatherModel wc = new weatherModel();
    String todayVmax = "";
    String todayVmin = "";

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(View view) {
            super(view);
            cdView = (CardView) view.findViewById(R.id.card_viewTdy);
            rlyt_Item = (RelativeLayout) view.findViewById(R.id.lyt_noCard);
            topDescLyt = (RelativeLayout) view.findViewById(R.id.topDescLyt);
            id_tdy = (TextView) view.findViewById(R.id.id_tdy);
            id_tmpTdy = (TextView) view.findViewById(R.id.id_tmpTdy);
            id_decTdy = (TextView) view.findViewById(R.id.id_decTdy);
            id_tdyTwo = (TextView) view.findViewById(R.id.id_tdyTwo);
            id_tmpTdyTwo = (TextView) view.findViewById(R.id.id_tmpTdyTwo);
            id_decTdyTwo = (TextView) view.findViewById(R.id.id_decTdyTwo);
            id_tdyDate = (TextView) view.findViewById(R.id.id_tdyDate);
            txt_twoWea = (TextView) view.findViewById(R.id.txt_twoWea);
            weekTdyDec = (TextView) view.findViewById(R.id.weekTdyDec);
        }
    }

    public weatherAdapter(Context mContext, List<String> weathList, List<String> desc, List<String> date) {
        this.mContext = mContext;
        this.weatherList = weathList;
        this.weatherDes = desc;
        this.dateList = date;
    }


    @Override
    public weatherAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(weatherAdapter.MyViewHolder holder, int position) {

        int n = weatherList.size() / 2;

        if (position == 0) {

            rlyt_Item.setVisibility(View.GONE);
            cdView.setVisibility(View.VISIBLE);

            td = weatherList.get(position + 1);
            tempN = (int) ut.convertTemp(td);
            todayVmax = tempN + "";
            td = weatherList.get(position);
            tempN = (int) ut.convertTemp(td);
            todayVmin = tempN + "";
            tdtwo = weatherDes.get(position+1);
            tdthree = ut.descriWeather(tdtwo);
            weekTdyDec.setText(tdthree);
            id_decTdy.setText(tdthree);
            txt_twoWea.setText(todayVmax);
            id_tdy.setText("Maximum Temp : " + todayVmax + "°");
            id_tmpTdy.setText("Minimum Temp : " + todayVmin + "°");

        } else {

            rlyt_Item.setVisibility(View.VISIBLE);
            cdView.setVisibility(View.GONE);
            topDescLyt.setVisibility(View.GONE);

            td = weatherList.get(position + 1);
            tempN = (int) ut.convertTemp(td);
            todayVmax = tempN + "";
            td = weatherList.get(position);
            tempN = (int) ut.convertTemp(td);
            todayVmin = tempN + "";

            tdtwo = weatherDes.get(position+1);
            tdthree = ut.descriWeather(tdtwo);

            id_tdyTwo.setText("Maximum Temp : " + todayVmax + "°");
            id_tmpTdyTwo.setText("Minimum Temp : " + todayVmin + "°");

            tdtwo = weatherDes.get(position+1);
            tdthree = ut.descriWeather(tdtwo);
            id_decTdyTwo.setText(tdthree);

            //update date

            id_tdyDate.setText(dateList.get(position));

        }

    }

    @Override
    public int getItemCount() {
        int n = weatherList.size() / 2;

        return n;
    }
}
