package app.weatheapp.config;

/**
 * Created by devkkda on 7/22/2017.
 */

public class Utils {

    public Utils(){

    }
    String tdtwo="";
    String desCri="";
    double op,opx;

    public double convertTemp(String temp){

        tdtwo = temp.substring(temp.lastIndexOf(":") + 1);

        op = Double.parseDouble(tdtwo);
        opx = op - 273.15;

        return opx;
    }

    public String descriWeather(String in){

        desCri = in.substring(in.lastIndexOf(":") + 2);
        desCri = (desCri.substring(0, desCri.length() - 1));

        return desCri;
    }


}
