package ba.edu.ibu.myapplication;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

public class Budget extends AppCompatActivity {

    static String[] cities;
    static String[] resto;
    static String[] groce;
    static String[] coli;
    ProgressBar prg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        Bundle getdata = getIntent().getExtras();
        String dest =  getdata.getString("city");
        int budget = getdata.getInt("budget");
        int days = getdata.getInt("days");
        prg = (ProgressBar)findViewById(R.id.progressBar2);

        cities = getResources().getStringArray(R.array.City);
        coli = getResources().getStringArray(R.array.coli);
        resto = getResources().getStringArray(R.array.Restaurant);
        groce = getResources().getStringArray(R.array.groceries);
        int idx = indexOf(dest);

        double p = calculate(idx);
        int pr = (int) (budget/p/days * 100);
        toProgress(pr);
        TextView pri = (TextView)findViewById(R.id.persent);
        pri.setText("you have "+pr +"% enough budget");
        TextView price = (TextView)findViewById(R.id.Budget);
        NumberFormat numberFormat = new DecimalFormat("#,###.00");
        if (price != null) {
            price.setText("It is recommended for you to have " + numberFormat.format(p) + "$/day during your trip" );
        }

    }

    int indexOf(String city) {
        for (int idx = 0; idx<cities.length; idx++) {
            if (cities[idx].equals(city)) {
                return idx;
            }
        }
        return -1;
    }

    void toProgress(int pr){
        prg.setProgress(pr);
        if(pr<40){ prg.getProgressDrawable().setColorFilter(RED, PorterDuff.Mode.SRC_IN);}
        else
        if(pr<80){  prg.getProgressDrawable().setColorFilter(YELLOW, PorterDuff.Mode.SRC_IN);}
        else
        { prg.getProgressDrawable().setColorFilter(GREEN, PorterDuff.Mode.SRC_IN);}
    }

    double calculate( int idx){
        double restoPrice = Double.parseDouble(resto[idx]);
        double grocePrice = Double.parseDouble(groce[idx]);
        double coliNum = Double.parseDouble(coli[idx]);

        double meal =  3 * (18 * restoPrice / 100);
        double stay =  ( 150 * coliNum / 100 );
        double snack =  ( (2.23*5* grocePrice/100)+(4.25*2*grocePrice/100)+(12*grocePrice/100) );
        return (meal+stay+snack) ;
    }
}
