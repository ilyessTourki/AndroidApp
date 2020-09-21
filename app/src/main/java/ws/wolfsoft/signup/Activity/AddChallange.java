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
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.Challenge;
import ws.wolfsoft.signup.NewsApi;
import ws.wolfsoft.signup.PrefConfig;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Retrofit.ApiClient;
import ws.wolfsoft.signup.Retrofit.ApiInterface;

public class AddChallange extends AppCompatActivity {
    TextView titre,description;
    CalendarView calendar;
    Button next,retour;
    String selectedDate;
    int Type;
    public static ApiInterface apiInterface;
    public static PrefConfig prefConfig;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challange);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConfig = new PrefConfig(this);
        //////////////////////////type////////////
        Bundle extras = getIntent().getExtras();
        Type = extras.getInt("1");
        ///////////////////////////////////////
        ///////////button retour//////////////





        /////////////////////////
        next= findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chellengeinser();

            }
        });
        calendar = findViewById(R.id.calendarView2);
        calendar.setMinDate(calendar.getDate());
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
               /* ;
                Log.e("ERRO", selectedDate);*/
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//


                //selectedDate= dayOfMonth + "/" + mon + "/" + year;
                Calendar calender = Calendar. getInstance();
                Date salle = new Date(view.getDate());
                calender.setTime(salle);
                calender.set(year,month,dayOfMonth);
                selectedDate=sdf.format(new Date(calender.getTime().toString()));
                Log.e("ERRO", String.valueOf(calender.get(Calendar.MONTH)));
            }
        });

    }





    public void chellengeinser(){
        titre = findViewById(R.id.tit);
        String Titre = titre.getText().toString();
        Log.e("test",Titre);
        description = findViewById(R.id.desc);
        String Description=description.getText().toString();

        Call<Challenge> call= apiInterface.chellengeinser(Titre,Description,Type,selectedDate);

        call.enqueue(new Callback<Challenge>() {

            @Override
            public void onResponse(Call<Challenge> call, Response<Challenge> response) {

                if (response.body().getResponse().equals("Ok")){
                    prefConfig.displayToast("Registration sucess ... ");
                }
                else if (response.body().getResponse().equals("exist")){
                    prefConfig.displayToast("user already exist ... ");
                }
                else if (response.body().getResponse().equals("error")){
                    prefConfig.displayToast("check what wrong... ");
                }
                Intent i = new Intent(AddChallange.this, HomeActivity.class);
                startActivity(i);


            }

            @Override
            public void onFailure(Call<Challenge> call, Throwable t) {
                prefConfig.displayToast("Remplis tous les champs");
                //Toast.makeText(ErrorHandlingActivity.this, "network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(AddChallange.this, HomeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
