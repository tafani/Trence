package ba.edu.ibu.myapplication;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class addActivity extends AppCompatActivity {

    private int currentApiVersion;
    static ArrayList<Spacecraft> s=new ArrayList<>();
    static int min;
    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if(currentApiVersion >= android.os.Build.VERSION_CODES.KITKAT)
        {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
    }
    public void fragment1(View view) {


        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        plane p = new plane();
        FT.replace(R.id.fragment,p);
        FT.commit();
    }

    public void fragment2(View view) {
        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        ship p = new ship();
        FT.replace(R.id.fragment,p);
        FT.commit();
    }

    public void fragment3(View view) {
        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        bus p = new bus();
        FT.replace(R.id.fragment,p);
        FT.commit();
    }

    public void fragment4(View view) {
        FragmentManager FM = getFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();
        train p = new train();
        FT.replace(R.id.fragment,p);
        FT.commit();
    }

    public void trainAdd(View view) {
        EditText txtDate = (EditText)findViewById(R.id.date);
        EditText txtTime = (EditText)findViewById(R.id.Timer);
        if(!txtDate.getText().toString().equals("") || !txtTime.getText().toString().equals("")){
        Intent trainAdding = new Intent(addActivity.this,schedule.class);
        trainAdding.putExtra("date",txtDate.getText().toString());
        trainAdding.putExtra("time",txtTime.getText().toString());
            trainAdding.putExtra("id",4);
        startActivity(trainAdding);
        } else{
            Toast.makeText(getApplicationContext(),"You cannot leave any data empty",Toast.LENGTH_LONG).show();
        }
    }

    public void busAdd(View view) {
        EditText txtDate = (EditText)findViewById(R.id.date);
        EditText txtTime = (EditText)findViewById(R.id.Timer);
        if(!txtDate.getText().toString().equals("") || !txtTime.getText().toString().equals("")){
        Intent BussAdding = new Intent(addActivity.this,schedule.class);
        BussAdding.putExtra("date",txtDate.getText().toString());
        BussAdding.putExtra("time",txtTime.getText().toString());
            BussAdding.putExtra("id",3);
        startActivity(BussAdding);
        } else{
            Toast.makeText(getApplicationContext(),"You cannot leave any data empty",Toast.LENGTH_LONG).show();
        }
    }

    public void shipAdd(View view) {
        EditText txtDate = (EditText)findViewById(R.id.date);
        EditText txtTime = (EditText)findViewById(R.id.Timer);
        if(!txtDate.getText().toString().equals("") || !txtTime.getText().toString().equals("")){
        Intent ShipAdding = new Intent(addActivity.this,schedule.class);
        ShipAdding.putExtra("date",txtDate.getText().toString());
        ShipAdding.putExtra("time",txtTime.getText().toString());
            ShipAdding.putExtra("id",2);
            startActivity(ShipAdding);
        } else{
            Toast.makeText(getApplicationContext(),"You cannot leave any data empty",Toast.LENGTH_LONG).show();
        }
    }

    public void planeAdd(View view) {
        EditText txtDate = (EditText)findViewById(R.id.date);
        EditText txtTime = (EditText)findViewById(R.id.Timer);
        Intent planeAdding = new Intent(addActivity.this,schedule.class);
        if(!txtDate.getText().toString().equals("")|| !txtTime.getText().toString().equals("")){
            planeAdding.putExtra("date",txtDate.getText().toString());
            planeAdding.putExtra("time",txtTime.getText().toString());
            planeAdding.putExtra("id",1);
            startActivity(planeAdding);
        } else{
            Toast.makeText(getApplicationContext(),"You cannot leave any data empty",Toast.LENGTH_LONG).show();
        }

    }
}
