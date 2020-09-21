package ws.wolfsoft.signup.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.PendingIntent;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.Challenge;
import ws.wolfsoft.signup.ChallengeViewAdapter;
import ws.wolfsoft.signup.NewsApi;
import ws.wolfsoft.signup.PrefConfig;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Retrofit.ApiClient;
import ws.wolfsoft.signup.Retrofit.ApiInterface;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.models.User;
import ws.wolfsoft.signup.models.topchallenger;

import static ws.wolfsoft.signup.App.CHANNEL_1_ID;

public class ListeChallanges extends AppCompatActivity  implements ChallengeViewAdapter.OnClickListener{
    Button participate,ajout;
    TextView type;
    int Type,count=1,Gype;

    public static ApiInterface apiInterface;
    public static PrefConfig prefConfig;

    private ListView mListView;
    private ChallengeViewAdapter mListAdapter;
    private ArrayList<Challenge> mListData;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ChallengeViewAdapter RecycleAdap;
    static final   ArrayList<Challenge> mtabaclist = new ArrayList<>();

    static String tit,dat;
    static int j=0,p=0;
    int y =0,k=0;
    Toolbar toolbar;
    int x=0;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_challanges);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        notificationManager = NotificationManagerCompat.from(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConfig = new PrefConfig(this);

        mtabaclist.clear();

        ajout= findViewById(R.id.add);


        type = findViewById(R.id.TYPE);
        Bundle extras = getIntent().getExtras();
        Type = extras.getInt("1");

        if(Type==1){
            type.setText("Time");
        }
        else if(Type==2){
            type.setText("Money");
        }
        else if(Type==3){
            type.setText("Health");
        }
        ///////////////////// get from base////////

        User user = ShardPrefManager.getInstance(this).getUser();
        p=user.getId();

        Gype = extras.getInt("9");
        if(Gype==4) {
            listechallenges();
        k=1;}


        else challenges();

/////////////////////////////////////////////////

////////////////list//////////////////////////////////////////

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);


        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        RecycleAdap = new ChallengeViewAdapter(mtabaclist,this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//////////////////////////////////////////////////////////////////
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListeChallanges.this, AddChallange.class);

                i.putExtra("1", Type);
                startActivity(i);
            }
        });
    }
    public void challenges(){
        Call<Challenge> cal= apiInterface.count();
        cal.enqueue(new Callback<Challenge>() {
            @Override
            public void onResponse(Call<Challenge> cal, Response<Challenge> response) {

                for (int i = 0; i < Integer.parseInt(response.body().getResponse()); i++) {
                    Call<Challenge> call= apiInterface.challenges(i+1,Type);
                    call.enqueue(new Callback<Challenge>() {

                        @Override
                        public void onResponse(Call<Challenge> call, Response<Challenge> response) {
                            if(response.body().getDatefin()!=null) {
                                String h = response.body().getDatefin();
                                Log.e("wey", h);
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String today = sdf.format(new Date(calendar.getTime().toString()));
                                Date sal = null;
                                try {
                                    sal = sdf.parse(today);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Date sal2 = null;
                                try {
                                    sal2 = sdf.parse(h);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (sal2.after(sal) || sal2.equals(sal)) {
                                    x=1;


                            if(response.body().getType()!=0) {
                                tit = response.body().getTitre();
                                dat = response.body().getDatefin();
                                j=response.body().getID();

                                Challenge Item = new Challenge();
                                Item.setTitre(tit);
                                Item.setDatefin(dat);
                                Item.setID(j);

                                mtabaclist.add(Item);
                                mRecyclerView.setAdapter(RecycleAdap);

                            } } }
                        }

                        @Override
                        public void onFailure(Call<Challenge> call, Throwable t) {

                            prefConfig.displayToast("failed here");
                            Log.e("ERRO", t.getMessage());


                        }

                    });
                    count++;
                } }

            @Override
            public void onFailure(Call<Challenge> cal, Throwable t) {

                prefConfig.displayToast("failed here");
                Log.e("ERRO", t.getMessage());


            }

        });
    }

    public void listechallenges(){
        Call<Challenge> cal= apiInterface.count();
        cal.enqueue(new Callback<Challenge>() {
            @Override
            public void onResponse(Call<Challenge> cal, Response<Challenge> response) {
                Log.e("challid",String.valueOf(p));
                    Call<List<topchallenger>> call= apiInterface.challiduser(p);
                    call.enqueue(new Callback<List<topchallenger>>() {

                        @Override
                        public void onResponse(Call<List<topchallenger>> call, Response<List<topchallenger>> response) {
                            List<topchallenger> lista = new ArrayList<>();
                            lista= response.body();
                            for (y=0;y<response.body().size();y++) {
                                final topchallenger tc = lista.get(y);


                                Call<Challenge> calll= apiInterface.challengeuser(Integer.parseInt(tc.getResponse()));
                                calll.enqueue(new Callback<Challenge>() {

                                    @Override
                                    public void onResponse(Call<Challenge> calll, Response<Challenge> response) {

                                        if(response.body().getDatefin()!=null) {
                                            String h = response.body().getDatefin();
                                            Log.e("wey", h);
                                            Calendar calendar = Calendar.getInstance();
                                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                            String today = sdf.format(new Date(calendar.getTime().toString()));
                                            Date sal = null;
                                            try {
                                                sal = sdf.parse(today);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            Date sal2 = null;
                                            try {
                                                sal2 = sdf.parse(h);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                            if(sal2.equals(sal))
                                            {

                                               String title = response.body().getTitre();
                                                String message = "There is a challenge that ends today check it";



                Notification notification = new NotificationCompat.Builder(ListeChallanges.this, CHANNEL_1_ID)
                        .setSmallIcon(R.drawable.ic_announcement)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();

                            notificationManager.notify(1, notification);


                                            }
                                            if (sal2.after(sal)) {
                                                x=1;


                                                if(response.body().getType()!=0) {
                                                    tit = response.body().getTitre();
                                                    dat = response.body().getDatefin();
                                                    j=response.body().getID();
                                                    Challenge Item = new Challenge();
                                                    Item.setTitre(tit);
                                                    Item.setDatefin(dat);
                                                    Item.setID(j);
                                                    mtabaclist.add(Item);
                                                    mRecyclerView.setAdapter(RecycleAdap);

                                                } }
                                        else {
                                                if(response.body().getType()!=0) {
                                                    tit = response.body().getTitre();
                                                    dat = "----------";
                                                    j=response.body().getID();
                                                    Challenge Item = new Challenge();
                                                    Item.setTitre(tit);
                                                    Item.setDatefin(dat);
                                                    Item.setID(j);
                                                    mtabaclist.add(Item);
                                                    mRecyclerView.setAdapter(RecycleAdap);}

                                            }

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Challenge> calll, Throwable t) {

                                        prefConfig.displayToast("failed here");
                                        Log.e("ERRO", t.getMessage());


                                    }

                                });


                                Log.e("challid",tc.getResponse());
                            }


                        }

                        @Override
                        public void onFailure(Call<List<topchallenger>> call, Throwable t) {

                            prefConfig.displayToast("failed here");
                            Log.e("ERoRO", t.getMessage());


                        }

                     });
            }

            @Override
            public void onFailure(Call<Challenge> cal, Throwable t) {

                prefConfig.displayToast("failed here");
                Log.e("ERRO", t.getMessage());


            }

        });
    }

    @Override
    public void OnItemClick(int position) {

        int f = mtabaclist.get(position).getID();
        Log.e("click", String.valueOf(mtabaclist.get(position).getID()));
        Intent i = new Intent(ListeChallanges.this, DetailChallenge.class);
        i.putExtra("1", f);
        i.putExtra("2", Type);
        i.putExtra("3",k);
        startActivity(i);

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
                startActivity(new Intent(ListeChallanges.this, HomeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
