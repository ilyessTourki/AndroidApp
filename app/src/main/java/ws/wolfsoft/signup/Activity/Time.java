package ws.wolfsoft.signup.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ws.wolfsoft.signup.Activity.Health;
import ws.wolfsoft.signup.Activity.HomeActivity;
import ws.wolfsoft.signup.Activity.Money;
import ws.wolfsoft.signup.Fragments.TimeCalander;
import ws.wolfsoft.signup.Fragments.TimeSport;
import ws.wolfsoft.signup.R;

public class Time extends AppCompatActivity {
    public TextView Message;
    int count =0;
    int cote=0;
    int choix;
    Fragment fragment;
    Button left,right,next;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);


        Message = findViewById(R.id.create);
        Message.setText("Now We must know some Information about your time managment");

        Thread t = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                count++;
                                if (count == 7) {
                                    Message.setText("");
                                    fragment = new TimeCalander();
                                    FragmentManager fm = getFragmentManager();
                                    FragmentTransaction ft = fm.beginTransaction();
                                    ft.replace(R.id.fragment, fragment).commit();
                                    cote=1;
                                    final View b = findViewById(R.id.left);
                                    b.setVisibility(View.VISIBLE);
                                    final View d = findViewById(R.id.right);
                                    d.setVisibility(View.VISIBLE);
                                    b.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            if(cote==2) {

                                                fragment = new TimeCalander();
                                                FragmentManager fm = getFragmentManager();
                                                FragmentTransaction ft = fm.beginTransaction();
                                                ft.replace(R.id.fragment, fragment).commit();
                                                cote=1;
                                                d.setVisibility(View.VISIBLE);
                                                b.setVisibility(View.INVISIBLE);
                                            }
                                            else if (cote==3){

                                                fragment = new TimeSport();
                                                FragmentManager fm = getFragmentManager();
                                                FragmentTransaction ft = fm.beginTransaction();
                                                ft.replace(R.id.fragment, fragment).commit();
                                                cote=2;
                                                d.setVisibility(View.VISIBLE);
                                                b.setVisibility(View.VISIBLE);

                                            }
                                        }

                                    });
                                    d.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            if(cote==1) {
                                                fragment = new TimeSport();
                                                FragmentManager fm = getFragmentManager();
                                                FragmentTransaction ft = fm.beginTransaction();
                                                ft.replace(R.id.fragment, fragment).commit();
                                                cote=2;
                                                d.setVisibility(View.INVISIBLE);
                                                next = findViewById(R.id.next);
                                                next.setVisibility(View.VISIBLE);
                                                b.setVisibility(View.VISIBLE);
                                                next.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                        Intent i=getIntent();
                                                        choix=i.getIntExtra("choix",0);

                                                        if (choix==0){

                                                        Intent intent = new Intent(getApplicationContext(),
                                                                HomeActivity.class);

                                                        startActivity(intent);}
                                                        else if(choix==1){
                                                            Intent intent = new Intent(getApplicationContext(),
                                                                    Money.class);
                                                            int choix=1;
                                                            intent.putExtra("choix",choix);

                                                            startActivity(intent);

                                                        }else if(choix==2){
                                                            Intent intent = new Intent(getApplicationContext(),
                                                                    Health.class);

                                                            startActivity(intent);

                                                        }else if(choix==3){
                                                            Intent intent = new Intent(getApplicationContext(),
                                                                    Money.class);
                                                            int choix=3;
                                                            intent.putExtra("choix",choix);

                                                            startActivity(intent);

                                                        }
                                                    }
                                                });
                                            }

                                        }
                                    });

                                    if(cote==3){
                                        next = findViewById(R.id.next);
                                        next.setVisibility(View.VISIBLE);

                                    }
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
}
