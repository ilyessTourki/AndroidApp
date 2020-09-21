package ws.wolfsoft.signup.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import ws.wolfsoft.signup.R;

public class TimeAsso extends Fragment {
    View vi;
    Button y;
    TextView tv;
    CalendarView cv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi =inflater.inflate(R.layout.fragment_time_asso,container,false);
        y = vi.findViewById(R.id.yes);
        tv = vi.findViewById(R.id.meet);
        cv = vi.findViewById(R.id.calendarView2);
        cv.setVisibility(View.INVISIBLE);
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            tv.setVisibility(View.VISIBLE);
                cv.setVisibility(View.VISIBLE);
            }
        });
        return vi;


    }

}
