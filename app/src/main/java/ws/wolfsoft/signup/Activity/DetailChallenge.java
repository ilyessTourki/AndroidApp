package ws.wolfsoft.signup.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.Challenge;
import ws.wolfsoft.signup.PrefConfig;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Retrofit.ApiClient;
import ws.wolfsoft.signup.Retrofit.ApiInterface;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.models.User;
import ws.wolfsoft.signup.models.topchallenger;

public class DetailChallenge extends AppCompatActivity {
    int Type,id,k;
    public static ApiInterface apiInterface;
    public static PrefConfig prefConfig;
    TextView titre,decription,date;
    Button participate;
    Toolbar toolbar;
    int point=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_challenge);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConfig = new PrefConfig(this);

        challenges();



        participate = findViewById(R.id.partic);
        Bundle extras = getIntent().getExtras();
        k = extras.getInt("3");
        if(k==1){
            participate.setVisibility(View.INVISIBLE);
        }
        else {
        participate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                id = extras.getInt("1");
                updateuser();

            }
        });}

    }

    public void challenges(){
        Bundle extras = getIntent().getExtras();
        Type = extras.getInt("2");
        id = extras.getInt("1");
        Log.e("id",String.valueOf(id));
        Log.e("id",String.valueOf(Type));

        Call<Challenge> call= apiInterface.challengeuser(id);
        call.enqueue(new Callback<Challenge>() {

            @Override
            public void onResponse(Call<Challenge> call, Response<Challenge> response) {

                titre=findViewById(R.id.titt);
                decription=findViewById(R.id.description);
                date=findViewById(R.id.date);
                titre.setText(response.body().getTitre());
                decription.setText(response.body().getDescription());
                Log.e("ERRO", response.body().toString());
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String today = sdf.format(new Date(calendar.getTime().toString()));
                Calendar cal = Calendar. getInstance();
                Calendar cal1 = Calendar. getInstance();
                Date sal = null;
                try {
                    sal = sdf.parse(today);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date sal2 = null;
                try {
                    sal2 = sdf.parse(response.body().getDatefin());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                cal. setTime(sal);
                cal1. setTime(sal2);
                long msDiff = cal1.getTimeInMillis()-cal.getTimeInMillis()  ;
                long daysDiff =  TimeUnit.MILLISECONDS.toDays(msDiff);
                if(daysDiff<0){ date.setText("Over");}
                else
                date.setText(String.valueOf(daysDiff));

            }

            @Override
            public void onFailure(Call<Challenge> call, Throwable t) {

                prefConfig.displayToast("failed here");
                Log.e("ERRO", t.getMessage());


            }

        });

    }

    public void updateuser(){
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("1");
        User user = ShardPrefManager.getInstance(this).getUser();


        Call<topchallenger> call= apiInterface.updateuser(user.getId(),id,point);
        call.enqueue(new Callback<topchallenger>() {

            @Override
            public void onResponse(Call<topchallenger> call, Response<topchallenger> response) {

                prefConfig.displayToast("ok");

            }

            @Override
            public void onFailure(Call<topchallenger> call, Throwable t) {

                prefConfig.displayToast("failed here");
                Log.e("ERRO", t.getMessage());


            }

        });

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
                startActivity(new Intent(DetailChallenge.this, HomeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
