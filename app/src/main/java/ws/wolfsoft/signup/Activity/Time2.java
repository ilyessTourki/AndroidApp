package ws.wolfsoft.signup.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ws.wolfsoft.signup.R;

public class Time2 extends AppCompatActivity {
    public TextView Message;
    EditText msh,msm,meh,mem,tsh,tsm,teh,tem,wsh,wsm,weh,wem,fsh,fsm,feh,fem,tush,tusm,tueh,tuem,ssh,ssm,seh,sem;
    String mshq,msmq,mehq,memq,tshq,tsmq,tehq,temq,wshq,wsmq,wehq,wemq,fshq,fsmq,fehq,femq,tushq,tusmq,tuehq,tuemq,sshq,ssmq,sehq,semq;
    int count =0;
    int cote=0;
    Fragment fragment;
    androidx.fragment.app.Fragment fb;
    Button left,right,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time2);


        msh = findViewById(R.id.mtsh);
        msm = findViewById(R.id.mtsm);
        meh = findViewById(R.id.mteh);
        mem = findViewById(R.id.mtem);

        tsh = findViewById(R.id.ttsh);
        tsm = findViewById(R.id.ttsm);
        teh = findViewById(R.id.tteh);
        tem = findViewById(R.id.ttem);

        wsh = findViewById(R.id.wtsh);
        wsm = findViewById(R.id.wtsm);
        weh = findViewById(R.id.wteh);
        wem = findViewById(R.id.wtem);

        fsh = findViewById(R.id.ftsh);
        fsm = findViewById(R.id.ftsm);
        feh = findViewById(R.id.fteh);
        fem = findViewById(R.id.ftem);

        tush = findViewById(R.id.thtsh);
        tusm = findViewById(R.id.thtsm);
        tueh = findViewById(R.id.thteh);
        tuem = findViewById(R.id.thtem);

        ssh = findViewById(R.id.stsh);
        ssm = findViewById(R.id.stsm);
        seh = findViewById(R.id.steh);
        sem = findViewById(R.id.stem);


                                    final View d = findViewById(R.id.right);
                                    d.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            mshq = msh.getText().toString();
                                            msmq = msm.getText().toString();
                                            mehq = meh.getText().toString();
                                            memq = mem.getText().toString();

                                            tshq = tsh.getText().toString();
                                            tsmq = tsm.getText().toString();
                                            tehq = teh.getText().toString();
                                            temq = tem.getText().toString();

                                            wshq = wsh.getText().toString();
                                            wsmq = wsm.getText().toString();
                                            wehq = weh.getText().toString();
                                            wemq = wem.getText().toString();

                                            fshq = fsh.getText().toString();
                                            fsmq = fsm.getText().toString();
                                            fehq = feh.getText().toString();
                                            femq = fem.getText().toString();

                                            tushq = tush.getText().toString();
                                            tusmq = tusm.getText().toString();
                                            tuehq = tueh.getText().toString();
                                            tuemq = tuem.getText().toString();

                                            sshq = ssh.getText().toString();
                                            ssmq = ssm.getText().toString();
                                            sehq = seh.getText().toString();
                                            semq = sem.getText().toString();
                                            Intent i = new Intent(Time2.this, Sport.class);
                                            i.putExtra("k1", mshq);
                                            i.putExtra("k2", msmq);
                                            i.putExtra("k3", mehq);
                                            i.putExtra("k4", memq);

                                            i.putExtra("k5", tshq);
                                            i.putExtra("k6", tsmq);
                                            i.putExtra("k7", tehq);
                                            i.putExtra("k8", temq);

                                            i.putExtra("k9", wshq);
                                            i.putExtra("kk1", wsmq);
                                            i.putExtra("kk2", wehq);
                                            i.putExtra("kk3", wemq);

                                            i.putExtra("kk4", fshq);
                                            i.putExtra("kk5", fsmq);
                                            i.putExtra("kk6", fehq);
                                            i.putExtra("kk7", femq);

                                            i.putExtra("kk8", tushq);
                                            i.putExtra("kk9", tusmq);
                                            i.putExtra("1k", tuehq);
                                            i.putExtra("2k", tuemq);

                                            i.putExtra("3k", sshq);
                                            i.putExtra("4k", ssmq);
                                            i.putExtra("5k", sehq);
                                            i.putExtra("6k", semq);

                                            startActivity(i);
                                        }
                                    });

                                }
                            }

