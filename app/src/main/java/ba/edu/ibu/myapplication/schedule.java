package ba.edu.ibu.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class schedule extends AppCompatActivity {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm");

    SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyyhh : mm");
    CustomAdapter adapter;
    ListView lv;
    String date,time;
    int id;
    int[] images = { R.drawable.plane,R.drawable.images,R.drawable.bus,R.drawable.train,R.drawable.hotel};
    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
    Boolean pressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);
        Bundle getSchedule = getIntent().getExtras();
            takingData(getSchedule);

    }

    public void takingData(Bundle getSchedule){
        spacecrafts = addActivity.s;
        lv= (ListView) findViewById(R.id.sch);

        date = getSchedule.getString("date");
        time = getSchedule.getString("time");
        id = getSchedule.getInt("id");

        adapter=new CustomAdapter(this,getData());
        lv.setAdapter(adapter);
    }
    private ArrayList getData()
    {
        Spacecraft s=new Spacecraft();
        s.setName(date);
        s.setPropellant(time);
        s.setImage(images[id-1]);
        spacecrafts.add(s);

        return spacecrafts;
    }

    public void addSchedule(View view) {
        addActivity.s = spacecrafts;
        Intent adding = new Intent(schedule.this,addActivity.class);
        startActivity(adding);

    }

    public void reminder(View view) {
            pressed = true;
            alarm();

    }
    public void alarm(){
        Long max = Long.MAX_VALUE;
        int i = 0;
        int id = 0;
        for(Spacecraft s : addActivity.s) {
            String ym = s.getName();
            String hs = s.getPropellant();
            long time =0;
            try {
                Date date = sdf.parse(ym+" "+hs);
                time = date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(max>time) {
                max=time;
                id = i;
            } i++;
        }
        long alertTime = max;
        Intent Alert = new Intent(this,AlertReceiver.class);
        addActivity.min = id;
        PendingIntent pi = PendingIntent.getBroadcast(this.getApplicationContext(),1,Alert,0);
        AlarmManager alarmManager  = (AlarmManager)
                getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP,alertTime,
                pi);
        msg("Alarm Set");
    }
     public void msg(String s){
         Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
     }

}
