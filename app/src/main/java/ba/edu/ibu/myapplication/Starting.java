package ba.edu.ibu.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Starting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getting_info);
        String[] city = getResources().getStringArray(R.array.City);
        AutoCompleteTextView destin = (AutoCompleteTextView) findViewById(R.id.destination_city);
        assert destin != null;
        destin.setAdapter(new ArrayAdapter<String>(this, R.layout.list_detail, city));
    }

}
