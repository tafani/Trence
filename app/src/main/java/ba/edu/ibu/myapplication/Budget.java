package ba.edu.ibu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Budget extends AppCompatActivity {

    static String[] cities;
    static String[] resto;
    static String[] groce;
    static String[] coli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
        Bundle getdata = getIntent().getExtras();
        String dest =  getdata.getString("city");
        int budget = getdata.getInt("budget");
        int days = getdata.getInt("days");

        cities = getResources().getStringArray(R.array.City);
        coli = getResources().getStringArray(R.array.coli);
        resto = getResources().getStringArray(R.array.Restaurant);
        groce = getResources().getStringArray(R.array.groceries);
        int idx = indexOf(dest);

        double p = calculate(days, idx);
        TextView price = (TextView)findViewById(R.id.Budget);
        NumberFormat numberFormat = new DecimalFormat("#,###.00");
        price.setText("you will need " + numberFormat.format(p) + "$ during the trip" );
    }

    int indexOf(String city) {
        for (int idx = 0; idx<cities.length; idx++) {
            if (cities[idx].equals(city)) {
                return idx;
            }
        }
        return -1;
    }

    double calculate(int days, int idx){
        double restoPrice = Double.parseDouble(resto[idx]);
        double grocePrice = Double.parseDouble(groce[idx]);
        double coliNum = Double.parseDouble(coli[idx]);

        double meal = days * 3 * (18 * restoPrice / 100);
        double stay = days * ( 150 * coliNum / 100 );
        double snack = days * ( (2.23*5 * grocePrice/100)+(4.25*2*grocePrice/100)+(12*grocePrice/100) );
        return (meal+stay+snack) ;
    }
}
