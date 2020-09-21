package ws.wolfsoft.signup.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Storage.ShardMoneyManager;
import ws.wolfsoft.signup.models.Money;

public class Gift extends AppCompatActivity {

    CalendarView cale;
    Button next;
    String som,selectedDate;
    int mon =0,diff;
    int every=0,nhayer=0;
    long eventOccursOn;
    PopupWindow popUp;
    LinearLayout layout;
    TextView tv;
    LinearLayout.LayoutParams params;
    LinearLayout mainLayout;
    Button but,next2;
    boolean click = true;
    EditText soum;
    int ea,re,ha ;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cale= findViewById(R.id.calen);
        Money moneyy = ShardMoneyManager.getInstance(this).getMoney();
        Log.e("sd",String.valueOf(moneyy.getId()));
        ea= Integer.parseInt(moneyy.getSaler());
        re=Integer.parseInt(moneyy.getRent());
        ha= Integer.parseInt(moneyy.getHabit());
        final int vv=ea-(re+ha);

        soum=findViewById(R.id.soum);
        cale.setMinDate((new Date().getTime()));
        cale.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
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
        ///////////////////////////////////

        popUp = new PopupWindow(this);
        layout = new LinearLayout(this);
        mainLayout = new LinearLayout(this);
        tv = new TextView(this);

        //////////////////////////////////
        next=findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String selected = sdf.format(new Date(cale.getDate()));

                Calendar cal = Calendar. getInstance();
                Calendar cal1 = Calendar. getInstance();
                Date sal = new Date(selectedDate);
                Date sal2 = new Date(selected);
                cal. setTime(sal);
                cal1. setTime(sal2);
                long msDiff = cal.getTimeInMillis()-cal1.getTimeInMillis()  ;
                long daysDiff =  TimeUnit.MILLISECONDS.toDays(msDiff);
                som = soum.getText().toString();
                mon =Integer.parseInt(som);
                every =mon/(int) daysDiff;
                nhayer= vv/31;
                diff=every-nhayer;
                Log.e("ERRO", String.valueOf(every));

                if (click) {
                    if (nhayer>=every) {tv.setText(String.valueOf(
                            "          You have to save               "+" *                   "+every+"dt                         *                  -                every day "));
                    tv.setTextColor(Color.parseColor("#990000"));
                    tv.setTextSize(25);
                    tv.setGravity(75);

                    }
                    else tv.setText(String.valueOf("You can't offored this , it still missing "+diff+"dt every day"));
                    popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
                    layout.setBackgroundColor(Color.parseColor("#90ffcc66"));
                    popUp.update(20, 2200, 1200, 400);

                    click = false;
                } else {
                    popUp.dismiss();
                    click = true;
                }


            }

        });
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);

        layout.addView(tv, params);
        popUp.setContentView(layout);
        // popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);

        // mainLayout.addView(next, params);
        // setContentView(mainLayout);
        next2=findViewById(R.id.next2);
        // next2

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
                startActivity(new Intent(Gift.this, HomeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
