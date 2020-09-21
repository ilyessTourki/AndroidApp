package ws.wolfsoft.signup.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.Activity.Classement;
import ws.wolfsoft.signup.Activity.ForTest;
import ws.wolfsoft.signup.Activity.Gift;
import ws.wolfsoft.signup.Activity.ListeChallanges;
import ws.wolfsoft.signup.Activity.Money;
import ws.wolfsoft.signup.Activity.ReponseActivity;
import ws.wolfsoft.signup.NewsApi;
import ws.wolfsoft.signup.PrefConfig;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Retrofit.ApiClient;
import ws.wolfsoft.signup.Retrofit.ApiInterface;
import ws.wolfsoft.signup.Storage.ShardMoneyManager;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.User;
import ws.wolfsoft.signup.models.topchallenger;

public class HomeFragment extends Fragment {
    public static ApiInterface apiInterface;
    public static PrefConfig prefConfig;

    Button gift,news,chal,chall,challe,top;
    int j=0;
    int y =0;
    static int u =0;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vi =inflater.inflate(R.layout.fragment_aff_home,container,false);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConfig = new PrefConfig(getActivity());
       gift = (Button) vi.findViewById(R.id.gift);
       news = (Button) vi.findViewById(R.id.news);
       chal = (Button) vi.findViewById(R.id.chall1);
       chall = (Button) vi.findViewById(R.id.chall2);
       challe = (Button) vi.findViewById(R.id.chall3);


        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ShardMoneyManager.getInstance(getActivity()).getMoney().getId()!= 0)
                {
                    startActivity(new Intent(getActivity(), Gift.class));
                }
                else {
                    prefConfig.displayToast("You need to fill the money form first");
                }

            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), NewsApi.class));
            }
        });
        chal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j=1;
                int T=0;
                Log.e("err","de");
                Intent i = new Intent(getActivity(), ListeChallanges.class);

                i.putExtra("1", j);
                i.putExtra("9", T);
                startActivity(i);
            }
        });
        chall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j=2;
                int T=0;
                Intent i = new Intent(getActivity(), ListeChallanges.class);

                i.putExtra("1", j);
                i.putExtra("9", T);
                startActivity(i);
            }
        });
        challe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j=3;
                int T=0;
                Intent i = new Intent(getActivity(), ListeChallanges.class);

                i.putExtra("1", j);
                i.putExtra("9", T);
                startActivity(i);
            }
        });




        return vi;
    }

}
