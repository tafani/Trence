package ba.edu.ibu.myapplication;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class plane extends Fragment {


    private int id = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_plane, container, false);
        return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        EditText txtDate = (EditText)getView().findViewById(R.id.date);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    dateDialog dialog = new dateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft,"datePicker");
                }
            }
        });
        EditText txtTime = (EditText)getView().findViewById(R.id.Timer);
        txtTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    timeDialog dialog2 = new timeDialog(v);
                    FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                    dialog2.show(ft2,"timePicker");
                }
            }
        });
    }
}
