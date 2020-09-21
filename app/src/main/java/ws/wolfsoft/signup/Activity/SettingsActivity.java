package ws.wolfsoft.signup.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Retrofit.RetrofitClient;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.models.DefaultResponse;
import ws.wolfsoft.signup.models.LoginResponse;
import ws.wolfsoft.signup.models.User;

public class SettingsActivity extends AppCompatActivity {

    EditText name,email,CurrentPassword,NewPassword;
    TextView Updat,updatpass,delete;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        name = findViewById(R.id.name);
        email = findViewById(R.id.age);
        Updat = findViewById(R.id.updat);
        CurrentPassword =findViewById(R.id.pass);
        NewPassword = findViewById(R.id.newpass);
        updatpass = findViewById(R.id.updatpass);
        delete = findViewById(R.id.delete);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email.setText(ShardPrefManager.getInstance(this).getUser().getEmail());
        name.setText(ShardPrefManager.getInstance(this).getUser().getName());

        Updat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        updatpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });

    }


    private void updateProfile() {
        String eemail = email.getText().toString().trim();
        String nname = name.getText().toString().trim();

        if (eemail.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(eemail).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        if (nname.isEmpty()) {
            name.setError("Name required");
            name.requestFocus();
            return;
        }


        User user = ShardPrefManager.getInstance(this).getUser();

        Call<LoginResponse> call = RetrofitClient.getInstance()
                .getApi().updateUser(
                        user.getId(),
                        nname,
                        eemail
                );

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Toast.makeText(SettingsActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                if (!response.body().isError()) {
                    ShardPrefManager.getInstance(SettingsActivity.this).saveUser(response.body().getUser());
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }


    private void updatePassword() {
        String currentpassword = CurrentPassword.getText().toString().trim();
        String newpassword = NewPassword.getText().toString().trim();

        if (currentpassword.isEmpty()) {
            CurrentPassword.setError("Password required");
            CurrentPassword.requestFocus();
            return;
        }

        if (newpassword.isEmpty()) {
            NewPassword.setError("Enter new password");
            NewPassword.requestFocus();
            return;
        }


        User user = ShardPrefManager.getInstance(this).getUser();

        Call<DefaultResponse> call = RetrofitClient.getInstance().getApi()
                .updatePassword(currentpassword, newpassword, user.getEmail());

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                Toast.makeText(SettingsActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }


    private void deleteUser() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?");
        builder.setMessage("This action is irreversible...");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                User user = ShardPrefManager.getInstance(SettingsActivity.this).getUser();
                Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().deleteUser(user.getId());

                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                        if (!response.body().isErr()) {
                            ShardPrefManager.getInstance(SettingsActivity.this).clear();
                            ShardPrefManager.getInstance(SettingsActivity.this).clear();
                            Intent intent = new Intent(SettingsActivity.this, SigninActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }

                        Toast.makeText(SettingsActivity.this, response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog ad = builder.create();
        ad.show();
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
                startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
