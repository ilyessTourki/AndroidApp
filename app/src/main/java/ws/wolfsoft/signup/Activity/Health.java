package ws.wolfsoft.signup.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

public class Health extends AppCompatActivity {
    public TextView Message,imc,are,must,at,yimc,heights,cm,weights,kg,your,musts,least;
    int count=0;
    int h,w,gg,pp;
    double s,c,g,p;
    String we,he,G,P;
    EditText height,weight;
    RelativeLayout health;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        Message = findViewById(R.id.create);
        Message.setText("What about your Health?");

        imc=findViewById(R.id.imc);
        yimc=findViewById(R.id.yimc);
        your=findViewById(R.id.your);
        are=findViewById(R.id.are);
        must=findViewById(R.id.must);
        musts=findViewById(R.id.musts);
        at=findViewById(R.id.at);
        least= findViewById(R.id.least);
        height=findViewById(R.id.height);
        heights=findViewById(R.id.heights);
        weight=findViewById(R.id.weight);
        weights=findViewById(R.id.weights);
        next=findViewById(R.id.next);
        cm = findViewById(R.id.cm);
        kg = findViewById(R.id.kg);




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

                                    imc.setVisibility(View.VISIBLE);
                                    yimc.setVisibility(View.VISIBLE);
                                    your.setVisibility(View.VISIBLE);
                                    are.setVisibility(View.VISIBLE);
                                    must.setVisibility(View.VISIBLE);
                                    musts.setVisibility(View.VISIBLE);
                                    heights.setVisibility(View.VISIBLE);
                                    at.setVisibility(View.VISIBLE);
                                    least.setVisibility(View.VISIBLE);
                                    kg.setVisibility(View.VISIBLE);
                                    cm.setVisibility(View.VISIBLE);
                                    height.setVisibility(View.VISIBLE);
                                    weight.setVisibility(View.VISIBLE);
                                    weights.setVisibility(View.VISIBLE);
                                    next.setVisibility(View.VISIBLE);
                                }
                                if(count == 20 ){we = weight.getText().toString();
                                    he = height.getText().toString();
                                    if(he!=" "){h = Integer.parseInt(he);}
                                    if(we!=" "){w = Integer.parseInt(we);}
                                    s= (w)/(h*h/10000);
                                    c = 22*h*h/10000;
                                    g=c-w;
                                    p=w-c;
                                    gg= (int) g;
                                    pp= (int) p;
                                    imc.setText(String.valueOf(s));
                                    G= String.valueOf(gg);
                                    P= String.valueOf(pp);

                                    if(s<18.5){
                                        are.setText("Insufficient weight (thinness)");
                                        must.setText("gain weight by ");
                                        at.setText(G+"Kg");
                                    }
                                    else if(s>=18.5 && s<25 ){
                                        are.setText("Normal body");
                                        must.setText("Stay like you are That is good");
                                    }
                                    else if(s>=25 && s<30 ){
                                        are.setText("overweight");
                                        must.setText("slim by");
                                        at.setText(P+"Kg");
                                    }
                                    else if(s>=30 && s<35 ){
                                        are.setText("Moderate obesity");
                                        must.setText("slim by");
                                        at.setText(P+"Kg");
                                    }
                                    else if(s>=35 && s<40 ){
                                        are.setText("Severe obesity");
                                        must.setText("slim by ");
                                        at.setText(P+"Kg");
                                    }
                                    else{
                                        are.setText("Morbid or massive obesity");
                                        must.setText("slim by");
                                        at.setText(P+"Kg");
                                    }}
                                if(count == 25 ){we = weight.getText().toString();
                                    he = height.getText().toString();
                                    if(he!=" "){h = Integer.parseInt(he);}
                                    if(we!=" "){w = Integer.parseInt(we);}
                                    s= (w)/(h*h/10000);
                                    c = 22*h*h/10000;
                                    g=c-w;
                                    p=w-c;
                                    gg= (int) g;
                                    pp= (int) p;
                                    imc.setText(String.valueOf(s));
                                    G= String.valueOf(gg);
                                    P= String.valueOf(pp);

                                    if(s<18.5){
                                        are.setText("Insufficient weight (thinness)");
                                        must.setText("gain weight by ");
                                        at.setText(G+"Kg");
                                    }
                                    else if(s>=18.5 && s<25 ){
                                        are.setText("Normal body");
                                        must.setText("Stay like you are That is good");
                                    }
                                    else if(s>=25 && s<30 ){
                                        are.setText("overweight");
                                        must.setText("slim by");
                                        at.setText(P+"Kg");
                                    }
                                    else if(s>=30 && s<35 ){
                                        are.setText("Moderate obesity");
                                        must.setText("slim by");
                                        at.setText(P+"Kg");
                                    }
                                    else if(s>=35 && s<40 ){
                                        are.setText("Severe obesity");
                                        must.setText("slim by ");
                                        at.setText(P+"Kg");
                                    }
                                    else{
                                        are.setText("Morbid or massive obesity");
                                        must.setText("slim by");
                                        at.setText(P+"Kg");
                                    }}
                                if(count == 30 ){we = weight.getText().toString();
                                    he = height.getText().toString();
                                    if(he!=" "){h = Integer.parseInt(he);}
                                    if(we!=" "){w = Integer.parseInt(we);}
                                    s= (w)/(h*h/10000);
                                    c = 22*h*h/10000;
                                    g=c-w;
                                    p=w-c;
                                    gg= (int) g;
                                    pp= (int) p;
                                    imc.setText(String.valueOf(s));
                                    G= String.valueOf(gg);
                                    P= String.valueOf(pp);

                                    if(s<18.5){
                                        are.setText("Insufficient weight (thinness)");
                                        must.setText("gain weight by ");
                                        at.setText(G+"Kg");
                                    }
                                    else if(s>=18.5 && s<25 ){
                                        are.setText("Normal body");
                                        must.setText("Stay like you are That is good");
                                    }
                                    else if(s>=25 && s<30 ){
                                        are.setText("overweight");
                                        must.setText("slim by");
                                        at.setText(P+"Kg");
                                    }
                                    else if(s>=30 && s<35 ){
                                        are.setText("Moderate obesity");
                                        must.setText("slim by");
                                        at.setText(P+"Kg");
                                    }
                                    else if(s>=35 && s<40 ){
                                        are.setText("Severe obesity");
                                        must.setText("slim by ");
                                        at.setText(P+"Kg");
                                    }
                                    else{
                                        are.setText("Morbid or massive obesity");
                                        must.setText("slim by");
                                        at.setText(P+"Kg");
                                    }}
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };

        t.start();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddHealthFormulair();
                startActivity(new Intent(Health.this, HomeActivity.class));
            }
        });

    }

    private void AddHealthFormulair(){

        String hheight = height.getText().toString().trim();
        String wweight = weight.getText().toString().trim();
        String iimc = imc.getText().toString().trim();
        String aare = are.getText().toString().trim();
        String mmust = must.getText().toString().trim();


        if (hheight.isEmpty()) {
            height.setError("height is required");
            height.requestFocus();
            return;
        }
        if (wweight.isEmpty()) {
            weight.setError("weight is required");
            weight.requestFocus();
            return;
        }

        User user = ShardPrefManager.getInstance(this).getUser();

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createHealth(user.getId(),hheight, wweight, iimc, aare, mmust);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if(response.code() == 201){

                    DefaultResponse dr = response.body();
                    Toast.makeText(Health.this, dr.getMsg(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Health.this, HomeActivity.class));

                }else if(response.code() == 422){
                    startActivity(new Intent(Health.this, HomeActivity.class));
                    Toast.makeText(Health.this, "Health formulaire already exist", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(Health.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}
