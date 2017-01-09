package ba.edu.ibu.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tafani on 08/01/2017.
 */
public class MoreInforNotification extends Activity {
    ListView lv;
    CustomAdapter adapter;
    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        spacecrafts=addActivity.s;
        setContentView(R.layout.more_info_notific);
        lv = (ListView)findViewById(R.id.checksch);
        adapter = new CustomAdapter(this,spacecrafts);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyyhh : mm");

    @Override
    protected void onPause() {
        super.onPause();
        alarm();
    }

    public void alarm(){
        addActivity.s.remove(addActivity.min);
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
    }

}
