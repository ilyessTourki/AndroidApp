package ws.wolfsoft.signup.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import ws.wolfsoft.signup.R;

public class ReponseActivity extends AppCompatActivity {

    EditText description;
    CalendarView calendarView;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reponse);

        description = (EditText) findViewById(R.id.description);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        save = (Button) findViewById(R.id.btnsave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReponseActivity.this, HomeActivity.class));
            }
        });
    }
}
