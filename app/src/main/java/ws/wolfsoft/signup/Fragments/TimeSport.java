package ws.wolfsoft.signup.Fragments;


import android.os.Bundle;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ws.wolfsoft.signup.R;

public class TimeSport extends Fragment {
    View vi;
    Button y;
    TextView tv;
    RelativeLayout cv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi =inflater.inflate(R.layout.fragment_time_sport,container,false);
        y = vi.findViewById(R.id.yes);
        tv = vi.findViewById(R.id.train);
        cv = vi.findViewById(R.id.calndrier);
        // cv.setVisibility(View.INVISIBLE);
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
