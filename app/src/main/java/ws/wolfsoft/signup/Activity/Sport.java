package ws.wolfsoft.signup.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ws.wolfsoft.signup.R;

public class Sport extends AppCompatActivity {
    TextView monday;
    Button y,n,d;
    TextView tv,a;
    RelativeLayout cv;
    EditText mshh1,msm,meh,mem,tsh,tsm,teh,tem,wsh,wsm,weh,wem,fsh,fsm,feh,fem,tush,tusm,tueh,tuem,ssh,ssm,seh,sem,sush,susm,sueh,suem;
    String ms,msmq,mehq,memq,tshq,tsmq,tehq,temq,wshq,wsmq,wehq,wemq,fshq,fsmq,fehq,femq,tushq,tusmq,tuehq,tuemq,sshq,ssmq,sehq,semq,sushq,susmq,suehq,suemq;
    int j=0;
    String J;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);


        mshh1 = findViewById(R.id.mtshh1);
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

        sush = findViewById(R.id.sutsh);
        susm = findViewById(R.id.sutsm);
        sueh = findViewById(R.id.suteh);
        suem = findViewById(R.id.sutem);


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

        sushq = sush.getText().toString();
        susmq = susm.getText().toString();
        suehq = sueh.getText().toString();
        suemq = suem.getText().toString();

        Bundle extras = getIntent().getExtras();
        final String msg1 = extras.getString("k1");
        final String msg2 = extras.getString("k2");
        final String msg3 = extras.getString("k3");
        final String msg4 = extras.getString("k4");
        final String msg5 = extras.getString("k5");
        final String msg6 = extras.getString("k6");
       final  String msg7 = extras.getString("k7");
        final String msg8 = extras.getString("k8");
        final String msg9 = extras.getString("k9");
        final String ms1 = extras.getString("kk1");
        final String ms2 = extras.getString("kk2");
        final String ms3 = extras.getString("kk3");
        final String ms4 = extras.getString("kk4");
        final String ms5 = extras.getString("kk5");
        final String ms6 = extras.getString("kk6");
        final String ms7 = extras.getString("kk7");
        final String ms8 = extras.getString("kk8");
        final String ms9 = extras.getString("kk9");
        final String m1 = extras.getString("1k");
        final String m2 = extras.getString("2k");
        final String m3 = extras.getString("3k");
        final String m4 = extras.getString("4k");
        final String m5 = extras.getString("5k");
        final String m6 = extras.getString("6k");
        y=findViewById(R.id.yes);
        tv = findViewById(R.id.train);
        a =findViewById(R.id.a);
        a.setText(msg1);
        cv = findViewById(R.id.calndrier);
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv.setVisibility(View.VISIBLE);
                cv.setVisibility(View.VISIBLE);
                j=1;

            }
        });
        d = findViewById(R.id.right);
        String test;
        test = mshh1.getText().toString();
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Sport.this, Asso.class);

                i.putExtra("k10", mshh1.getText().toString());
                i.putExtra("k20", msm.getText().toString());
                i.putExtra("k30", meh.getText().toString());
                i.putExtra("k40", mem.getText().toString());

                i.putExtra("k50", tsh.getText().toString());
                i.putExtra("k60", tsm.getText().toString());
                i.putExtra("k70", teh.getText().toString());
                i.putExtra("k80", tem.getText().toString());

                i.putExtra("k90", wsh.getText().toString());
                i.putExtra("kk10", wsm.getText().toString());
                i.putExtra("kk20", weh.getText().toString());
                i.putExtra("kk30", wem.getText().toString());

                i.putExtra("kk40", fsh.getText().toString());
                i.putExtra("kk50", fsm.getText().toString());
                i.putExtra("kk60", feh.getText().toString());
                i.putExtra("kk70", fem.getText().toString());

                i.putExtra("kk80", tush.getText().toString());
                i.putExtra("kk90", tusm.getText().toString());
                i.putExtra("1k0", tueh.getText().toString());
                i.putExtra("2k0", tuem.getText().toString());

                i.putExtra("3k0", ssh.getText().toString());
                i.putExtra("4k0", ssm.getText().toString());
                i.putExtra("5k0", seh.getText().toString());
                i.putExtra("6k0", sem.getText().toString());

                i.putExtra("7k0", sush.getText().toString());
                i.putExtra("8k0", susm.getText().toString());
                i.putExtra("9k0", sueh.getText().toString());
                i.putExtra("10k0", suem.getText().toString());



                i.putExtra("k100", msg1);
                i.putExtra("k200", msg2);
                i.putExtra("k300", msg3);
                i.putExtra("k400", msg4);

                i.putExtra("k500", msg5);
                i.putExtra("k600", msg6);
                i.putExtra("k700", msg7);
                i.putExtra("k800", msg8);

                i.putExtra("k900", msg9);
                i.putExtra("kk100", ms1);
                i.putExtra("kk200", ms2);
                i.putExtra("kk300", ms3);

                i.putExtra("kk400", ms4);
                i.putExtra("kk500", ms5);
                i.putExtra("kk600", ms6);
                i.putExtra("kk700", ms7);

                i.putExtra("kk800", ms8);
                i.putExtra("kk900", ms9);
                i.putExtra("1k00", m1);
                i.putExtra("2k00", m2);

                i.putExtra("3k00", m3);
                i.putExtra("4k00", m4);
                i.putExtra("5k00", m5);
                i.putExtra("6k00", m6);
                J= Integer.toString(j);
                i.putExtra("7k00", J);
                startActivity(i);
            }
        });

    }
}
