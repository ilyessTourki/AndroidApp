package ws.wolfsoft.signup.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Retrofit.RetrofitClient;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.models.DefaultResponse;
import ws.wolfsoft.signup.models.User;

public class Money extends AppCompatActivity {
    public TextView Message;
        TextView r,h;
        EditText earn;
        Button y,yy,next;
        int count=0;
        int choix;
        LinearLayout mon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);

        r =  findViewById(R.id.rent);
        h = findViewById(R.id.habit);
        y = findViewById(R.id.yes);
        yy = findViewById(R.id.yess);
        next=findViewById(R.id.next);
        earn = findViewById(R.id.earn);




        Message = findViewById(R.id.create);
        Message.setText("How do you manage your money ?");

        Thread t = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(500);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                count++;
                                if (count == 7) {


                                    Message.setVisibility(View.INVISIBLE);

                                    mon=findViewById(R.id.money);
                                    mon.setVisibility(View.VISIBLE);
                                    y.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            r.setVisibility(View.VISIBLE);
                                        }
                                    });
                                    yy.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            h.setVisibility(View.VISIBLE);
                                        }
                                    });
                                    next.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            AddMoneyFormulair();

                                        }
                                    });

                                }
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        t.start();

    }

    private void AddMoneyFormulair(){

        String saler = earn.getText().toString().trim();
        String rent = r.getText().toString().trim();
        String habit = h.getText().toString().trim();


        if (saler.isEmpty()) {
            earn.setError("saler is required");
            earn.requestFocus();
            return;
        }

        User user = ShardPrefManager.getInstance(this).getUser();

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createMoney(user.getId(),saler, rent, habit);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if(response.code() == 201){

                    DefaultResponse dr = response.body();
                    Toast.makeText(Money.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                    Intent i=getIntent();
                    choix=i.getIntExtra("choix",0);

                    if (choix==4){
                        Intent intent = new Intent(getApplicationContext(),
                                HomeActivity.class);
                        startActivity(intent);
                    }
                    else if (choix==1){Intent intent = new Intent(getApplicationContext(),
                            HomeActivity.class);
                        startActivity(intent);

                    }else if (choix==5){Intent intent = new Intent(getApplicationContext(),
                            Health.class);
                        startActivity(intent);
                    }else if (choix==3){Intent intent = new Intent(getApplicationContext(),
                            Health.class);
                        startActivity(intent);
                    }

                }else if(response.code() == 422){

                    Toast.makeText(Money.this, "Money Formulaire already exist", Toast.LENGTH_LONG).show();

                    Intent i=getIntent();
                    choix=i.getIntExtra("choix",0);

                    if (choix==4){
                        Intent intent = new Intent(getApplicationContext(),
                                HomeActivity.class);
                        startActivity(intent);
                    }
                    else if (choix==1){Intent intent = new Intent(getApplicationContext(),
                            HomeActivity.class);
                        startActivity(intent);

                    }else if (choix==5){Intent intent = new Intent(getApplicationContext(),
                            Health.class);
                        startActivity(intent);
                    }else if (choix==3){Intent intent = new Intent(getApplicationContext(),
                            Health.class);
                        startActivity(intent);
                    }
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(Money.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
