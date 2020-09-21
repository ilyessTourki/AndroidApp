package ws.wolfsoft.signup.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import ws.wolfsoft.signup.Storage.ShardHealthManager;
import ws.wolfsoft.signup.Storage.ShardMoneyManager;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.User;

public class Subject extends AppCompatActivity {
    public static ApiInterface apiInterface;
    public static PrefConfig prefConfig;
    Toolbar toolbar;
    CheckBox moneey,health;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConfig = new PrefConfig(this);
        final Button next = findViewById(R.id.next);

       moneey = findViewById(R.id.money);
       health = findViewById(R.id.health);
        final CheckBox time = findViewById(R.id.time);

        ws.wolfsoft.signup.models.User user = ShardPrefManager.getInstance(this).getUser();
        int Userid =user.getId();



        Call<User> cal= apiInterface.moneytest(Userid);
        cal.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> cal, Response<User> response) {
                Log.e("mo",response.body().getResponse());
                if(!response.body().getResponse().equals("failed"))
                {
                    moneey.setEnabled(false);
                }

            }

            @Override
            public void onFailure(Call<User> cal, Throwable t) {

                prefConfig.displayToast("failed here");
                Log.e("ERRO", t.getMessage());


            }

        });



        Call<User> cal1= apiInterface.healthtest(Userid);
        cal1.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> cal1, Response<User> response) {
                Log.e("mo",response.body().getResponse());
                if(!response.body().getResponse().equals("failed"))
                {
                    health.setEnabled(false);
                }

            }

            @Override
            public void onFailure(Call<User> cal1, Throwable t) {

                prefConfig.displayToast("failed here");
                Log.e("ERRO", t.getMessage());


            }

        });




        Call<User> call= apiInterface.information(Userid);
        call.enqueue(new Callback<User>() {
            Response<User> mts;
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
               // Log.e("idd",response.body().getMts());
                if(response.body().getMts()!=null )
                {
                    time.setEnabled(false);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                prefConfig.displayToast("Succe le");
                Log.e("ERRO", t.getMessage());


            }

        });

                time.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    CheckBox money = findViewById(R.id.money);
                    CheckBox health = findViewById(R.id.health);
                    CheckBox time = findViewById(R.id.time);



                    if (isChecked && !health.isChecked() && !money.isChecked()) {
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Time2.class);
                                int choix=0;
                                i.putExtra("choix",choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });


                    }else if (isChecked && !health.isChecked() && money.isChecked()){
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Time2.class);
                                int choix=1;
                                i.putExtra("choix",choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });
                    }else if (isChecked && health.isChecked() && !money.isChecked()){
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Time2.class);
                                int choix=2;
                                i.putExtra("choix",choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });
                    }else if (isChecked && health.isChecked() && money.isChecked()){
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Time2.class);
                                int choix=3;
                                i.putExtra("choix",choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });
                    }
                }
            });

            moneey.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    CheckBox money = findViewById(R.id.money);
                    CheckBox health = findViewById(R.id.health);
                    CheckBox time = findViewById(R.id.time);


                    if (isChecked && !health.isChecked() && !time.isChecked()) {
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Money.class);
                                int choix = 4;
                                i.putExtra("choix", choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });

                    } else if (isChecked && !health.isChecked() && time.isChecked()){
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Time2.class);
                                int choix=1;
                                i.putExtra("choix",choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });
                    }else if (isChecked && health.isChecked() && !time.isChecked()) {
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Money.class);
                                int choix = 5;
                                i.putExtra("choix", choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });
                    }else if (isChecked && health.isChecked() && time.isChecked()){
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Time2.class);
                                int choix=3;
                                i.putExtra("choix",choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });
                    }
                }

            });


            health.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    CheckBox money = findViewById(R.id.money);
                    CheckBox health = findViewById(R.id.health);
                    CheckBox time = findViewById(R.id.time);


                    if (isChecked && !money.isChecked() && !time.isChecked()) {
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Health.class);
                                int choix = 6;
                                i.putExtra("choix", choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });

                    }else if (isChecked && money.isChecked() && !time.isChecked()) {
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Money.class);
                                int choix = 5;
                                i.putExtra("choix", choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });
                    }else if (isChecked && !money.isChecked() && time.isChecked()){
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Time2.class);
                                int choix=2;
                                i.putExtra("choix",choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });
                    }else if (isChecked && money.isChecked() && time.isChecked()){
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(Subject.this, Time2.class);
                                int choix=3;
                                i.putExtra("choix",choix);
                                startActivity(i);
                                Subject.this.finish();
                            }
                        });
                    }
                }
            });


        if (!health.isSelected() && !moneey.isSelected() && !time.isSelected()){
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Subject.this, "error ", Toast.LENGTH_SHORT).show();
                }
            });

        }


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
                startActivity(new Intent(Subject.this, HomeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
