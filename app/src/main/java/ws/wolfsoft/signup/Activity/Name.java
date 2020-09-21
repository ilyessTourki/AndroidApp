package ws.wolfsoft.signup.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ws.wolfsoft.signup.Activity.Formulaire;
import ws.wolfsoft.signup.R;

public class Name extends AppCompatActivity {
    public TextView Message;
    int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        Message = findViewById(R.id.create);
        Message.setText("Welcome Ilyess");

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
                                    if (count >= 5) {
                                        Message.setText("We are here to change your lifestyle in a better way");


                                    }
                                    if (count >= 10) {
                                        Message.setText("We will teach you how manage your life");


                                    }
                                    if (count >= 15) {
                                        Message.setText("Time");


                                    }
                                    if (count >= 18) {
                                        Message.setText("Money");


                                    }
                                    if (count >= 21) {
                                        Message.setText("and Health .. ");

                                    }
                                    if (count >= 24) {
                                        Message.setText("Now we must know you more by this form");

                                    }
                                    if (count == 29){
                                        Intent intent = new Intent(getApplicationContext(),
                                                Formulaire.class);
                                        startActivity(intent);

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


       /* I*/

    }
}
