package ws.wolfsoft.signup.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.PrefConfig;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Retrofit.ApiClient;
import ws.wolfsoft.signup.Retrofit.ApiInterface;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.User;

public class Asso extends AppCompatActivity {
    String a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,t,u,v,w,x,y,z;
    TextView aa,bb;
    Button next;
    CalendarView calendar;
    public static ApiInterface apiInterface;
    public static PrefConfig prefConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asso);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        prefConfig = new PrefConfig(this);
        next= findViewById(R.id.next);
        calendar = findViewById(R.id.calendarView2);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
                Intent intent = new Intent(getApplicationContext(),
                        HomeActivity.class);
                startActivity(intent);

            }
        });





    }

    public void performRegistration(){
        Bundle extras = getIntent().getExtras();
        String msg1 = extras.getString("k10");
        String msg2 = extras.getString("k20");
        String msg3 = extras.getString("k30");
        String msg4 = extras.getString("k40");
        String msg5 = extras.getString("k50");
        String msg6 = extras.getString("k60");
        String msg7 = extras.getString("k70");
        String msg8 = extras.getString("k80");
        String msg9 = extras.getString("k90");
        String ms1 = extras.getString("kk10");
        String ms2 = extras.getString("kk20");
        String ms3 = extras.getString("kk30");
        String ms4 = extras.getString("kk40");
        String ms5 = extras.getString("kk50");
        String ms6 = extras.getString("kk60");
        String ms7 = extras.getString("kk70");
        String ms8 = extras.getString("kk80");
        String ms9 = extras.getString("kk90");
        String m1 = extras.getString("1k0");
        String m2 = extras.getString("2k0");
        String m3 = extras.getString("3k0");
        String m4 = extras.getString("4k0");
        String m5 = extras.getString("5k0");
        String m6 = extras.getString("6k0");
        String m7 = extras.getString("7k0");
        String m8 = extras.getString("8k0");
        String m9 = extras.getString("9k0");
        String m10 = extras.getString("10k0");


        String msg10 = extras.getString("k100");
        String msg20 = extras.getString("k200");
        String msg30 = extras.getString("k300");
        String msg40 = extras.getString("k400");
        String msg50 = extras.getString("k500");
        String msg60 = extras.getString("k600");
        String msg70 = extras.getString("k700");
        String msg80 = extras.getString("k800");
        String msg90 = extras.getString("k900");
        String ms10 = extras.getString("kk100");
        String ms20 = extras.getString("kk200");
        String ms30 = extras.getString("kk300");
        String ms40 = extras.getString("kk400");
        String ms50 = extras.getString("kk500");
        String ms60 = extras.getString("kk600");
        String ms70 = extras.getString("kk700");
        String ms80 = extras.getString("kk800");
        String ms90 = extras.getString("kk900");
        String m100 = extras.getString("1k00");
        String m20 = extras.getString("2k00");
        String m30 = extras.getString("3k00");
        String m40 = extras.getString("4k00");
        String m50 = extras.getString("5k00");
        String m60 = extras.getString("6k00");
        String j = extras.getString("7k00");
        a=msg1+msg2;
        b=msg10+msg20;
        aa= findViewById(R.id.B);
        bb= findViewById(R.id.A);
        aa.setText(a);
        bb.setText(j);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String selectedDate = sdf.format(new Date(calendar.getDate()));
        Log.e("ERRO", selectedDate+"dd");
        String Mts= msg10+msg20;
        String Mte = msg30+msg40;
        String Tts = msg50+msg60;
        String Tte= msg70+msg80;
        String Wte = msg90+ms10;
        String Wts =ms20+ms30;
        String Fts=ms40+ms50;
        String Fte=ms60+ms70;
        String Thts=ms80+ms90;
        String Thte=m100+m20;
        String Sts=m30+m40;
        String Ste=m50+m60;
        String Sport=j;
        String Mss= msg1+msg2;
        String Mse= msg3+msg4;
        String Tss= msg5+msg6;
        String Tse= msg7+msg8;
        String Wss= msg9+ms1;
        String Wse= ms2+ms3;
        String Tuss =ms4+ms5;
        String Tuse =ms6+ms7;
        String Fss =ms8+ms9;
        String Fse=m1+m2;
        String Sss=m3+m4;
        String Sse=m5+m6;
        String Suss=m7+m8;
        String Suse=m9+m10;
        String Asso ="1";
        String Dateasso = selectedDate;
        ws.wolfsoft.signup.models.User user = ShardPrefManager.getInstance(this).getUser();
        int Iduser =user.getId();
        Log.e("id",String.valueOf(Iduser));

        Call<User> call= apiInterface.performRegister(Mts,Mte,Tts,Tte,Wte,Wts,Fts,Fte,Thts,Thte,Sts,Ste,Sport,Mss,Mse,Tss,Tse,Wss,Wse,Tuss,Tuse,Fss,Fse,Sss,Sse,Suss,Suse,Asso,Dateasso,Iduser);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getResponse().equals("Ok")){
                    prefConfig.displayToast("Registration sucess ... ");
                }
                else if (response.body().getResponse().equals("exist")){
                    prefConfig.displayToast("user already exist ... ");
                }
                else if (response.body().getResponse().equals("error")){
                    prefConfig.displayToast("check what wrong... ");
                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                prefConfig.displayToast("brabi le");
                //Toast.makeText(ErrorHandlingActivity.this, "network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                Log.e("ERRO", t.getMessage());

            }

        });


    }

}

