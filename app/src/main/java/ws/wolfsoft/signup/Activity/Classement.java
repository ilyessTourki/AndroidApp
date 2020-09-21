package ws.wolfsoft.signup.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.Challenge;
import ws.wolfsoft.signup.PrefConfig;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.RangViewAdapter;
import ws.wolfsoft.signup.Retrofit.ApiClient;
import ws.wolfsoft.signup.Retrofit.ApiInterface;
import ws.wolfsoft.signup.ScoreViewAdapter;
import ws.wolfsoft.signup.User;
import ws.wolfsoft.signup.models.topchallenger;

public class Classement extends AppCompatActivity  implements ScoreViewAdapter.OnClickListener,RangViewAdapter.OnClickListener{
    public static ApiInterface apiInterface;
    public static PrefConfig prefConfig;
    private RecyclerView mRecyclerView,mRecyclerViewR;
    private RecyclerView.LayoutManager mLayoutManager,mLayoutManagerR;
    private RecyclerView.ViewHolder holderr;
    private ScoreViewAdapter RecycleAdap;
    private RangViewAdapter RecycleAdapR;
    static final   ArrayList<topchallenger> mtabaclist = new ArrayList<>();
    static final   ArrayList<Integer> mtabaclistR = new ArrayList<>();

    static String tit,dat;
    static int j=0,kk=0;
    Toolbar toolbar;

    int y =0;
    static int u =0,w=0,b=0;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConfig = new PrefConfig(this);
        mtabaclist.clear();
        mtabaclistR.clear();




        updateuser();


        mLayoutManager = new LinearLayoutManager(this);
       mLayoutManagerR = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerViewR = findViewById(R.id.recyclerR);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerViewR.setHasFixedSize(true);

        RecycleAdap = new ScoreViewAdapter(mtabaclist,this);
        RecycleAdapR = new RangViewAdapter(mtabaclistR,this);

        mRecyclerView.setLayoutManager(mLayoutManager);
       mRecyclerViewR.setLayoutManager(mLayoutManagerR);


//////////////////////////////////////////////////////////////////

    }
    public void updateuser(){


        Call<List<topchallenger>> call= apiInterface.topscore();
        call.enqueue(new Callback<List<topchallenger>>() {

            @Override
            public void onResponse(Call<List<topchallenger>> call, Response<List<topchallenger>> response) {
                 List<topchallenger> list = new ArrayList<>();
                list= response.body();
                b=response.body().size();
                for (y=0;y<response.body().size();y++) {
                    final topchallenger tc = list.get(y);
                    u = Integer.parseInt(tc.getId());
                    w= Integer.parseInt(tc.getResponse());
                    Log.e("mes",tc.getResponse());
                    Call<User> cal= apiInterface.username(u);
                    cal.enqueue(new Callback<User>() {

                        @Override
                        public void onResponse(Call<User> cal, Response<User> response) {

                            Log.e("mes",response.body().getResponse());
                            Log.e("position",String.valueOf( position));
                            position =position+1;
                            tit = tc.getResponse();
                            dat = response.body().getResponse();
                            topchallenger Item = new topchallenger();

                                Item.setId(tit);
                                Item.setResponse(dat);


                           // Item.setClassement(position);
                            mtabaclist.add(Item);
                            mtabaclistR.add(position);
                            Collections.sort(mtabaclist, new Comparator<topchallenger>() {
                                @Override
                                public int compare(topchallenger o1, topchallenger o2) {
                                    return o2.getId().compareTo(o1.getId());
                                }
                            });
                            RecycleAdap.notifyDataSetChanged();
                            mRecyclerView.findViewHolderForAdapterPosition(y);
                            RecycleAdapR.notifyDataSetChanged();
                            mRecyclerView.setAdapter(RecycleAdap);
                            mRecyclerViewR.setAdapter(RecycleAdapR);

                        }
                        @Override
                        public void onFailure(Call<User> cal, Throwable t) {

                            prefConfig.displayToast("failed here");
                            Log.e("ERRO", t.getMessage());


                        }

                    });
                }

            }
            @Override
            public void onFailure(Call<List<topchallenger>> call, Throwable t) {

                prefConfig.displayToast("failed here");
                Log.e("ERRO", t.getMessage());


            }

        });


    }



    @Override
    public void OnItemClick(int position) {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.retour, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.RetourMenu:
                startActivity(new Intent(Classement.this, HomeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
