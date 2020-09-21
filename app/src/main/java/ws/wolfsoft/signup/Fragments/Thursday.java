package ws.wolfsoft.signup.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ws.wolfsoft.signup.R;

public class Thursday extends Fragment {
    int r,m,noum,noum1,free,free1;
    View vi;
    TextView wake,sleep,fre,from;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int v = this.getArguments().getInt("7");
        int v1 = this.getArguments().getInt("8");
        int v2 = this.getArguments().getInt("9");
        int v3 = this.getArguments().getInt("10");
        int v4 = this.getArguments().getInt("11");
        int v5 = this.getArguments().getInt("12");
        vi = inflater.inflate(R.layout.fragment_thursday,container,false);
        r=v2-2;
        m=(r*60+v3);
        if(m>420)
        {
            noum=(m-420)/60;
            noum1=(m-420)%60;
        }
        else if(m<=420){
            noum=(1440-(420-m))/60;
            noum1=(1440-(420-m))%60;
        }
        wake=vi.findViewById(R.id.wake);
        wake.setText(String.valueOf(r)+"h"+String.valueOf(v3));
        sleep=vi.findViewById(R.id.sleep);
        sleep.setText(String.valueOf(noum)+"h"+String.valueOf(noum1));
        free=((1440-(v*60+v1))/60)-9;
        free1=((1440-(v*60+v1))%60);
        fre=vi.findViewById(R.id.free);
        fre.setText(String.valueOf(free)+"h"+String.valueOf(free1));
        from=vi.findViewById(R.id.from);
        from.setText("From  "+String.valueOf(v4)+"H"+String.valueOf(v5)+" to "+String.valueOf(noum)+"H"+String.valueOf(noum1));


        return vi;
    }

}
