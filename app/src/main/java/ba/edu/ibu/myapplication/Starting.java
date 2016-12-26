package ba.edu.ibu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;

public class Starting extends AppCompatActivity {
    static AutoCompleteTextView destin;
    static EditText daysET;
    static EditText budgetET;
    static String[] cities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getting_info);
        cities = getResources().getStringArray(R.array.City);

        destin = (AutoCompleteTextView) findViewById(R.id.destination_city);
        daysET = (EditText) findViewById(R.id.editText2);
        budgetET = (EditText) findViewById(R.id.budget);
        if (destin != null) {
            destin.setAdapter(new ArrayAdapter<>(this, R.layout.list_detail, cities));
        }
    }

    public void calculateMe(View view) {
        int days = Integer.parseInt(daysET.getText().toString());
        int budget = Integer.parseInt(budgetET.getText().toString());
        String dest = destin.getText().toString();

        Intent Budget = new Intent(Starting.this, ba.edu.ibu.myapplication.Budget.class);
        Budget.putExtra("city",dest);
        Budget.putExtra("budget",budget);
        Budget.putExtra("days",days);
        startActivity(Budget);
    }

}
