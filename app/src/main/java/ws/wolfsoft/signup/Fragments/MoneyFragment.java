package ws.wolfsoft.signup.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Retrofit.RetrofitClient;
import ws.wolfsoft.signup.Storage.ShardMoneyManager;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.models.DefaultResponse;
import ws.wolfsoft.signup.models.Money;
import ws.wolfsoft.signup.models.MoneyResponse;
import ws.wolfsoft.signup.models.User;

public class MoneyFragment extends Fragment {

    EditText Earn, Rent, Habit;
    TextView updat, delete,Message,somme;
    LinearLayout mon;
    int ea,re,ha,s;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aff_money, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Earn = view.findViewById(R.id.earn2) ;
        Rent= view.findViewById(R.id.rent2);
        Habit= view.findViewById(R.id.habit2);
        updat= view.findViewById(R.id.updatInfo1);
        delete= view.findViewById(R.id.delete1);
        Message = view.findViewById(R.id.create);
        mon=view.findViewById(R.id.money);
        somme= view.findViewById(R.id.somme);



        MoneyLogin();


        updat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMoney();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMoney();
            }
        });
    }

    private void deleteMoney() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure?");
        builder.setMessage("This action is irreversible...");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Money money = ShardMoneyManager.getInstance(getActivity()).getMoney();
                Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().deleteMoney(money.getId());

                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                        if (!response.body().isErr()) {
                            ShardMoneyManager.getInstance(getActivity()).getMoney();
                            //ShardMoneyManager.getInstance(getActivity()).clear();
                            MoneyLogin();
                        }

                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
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

    private void updateMoney() {

        String saler = Earn.getText().toString().trim();
        String rent = Rent.getText().toString().trim();
        String habit = Habit.getText().toString().trim();


        if (saler.isEmpty()) {
            Earn.setError("saler is required");
            Earn.requestFocus();
            return;
        }
        if (rent.isEmpty()) {
            Rent.setError("Rent is required");
            Rent.requestFocus();
            return;
        }
        if (habit.isEmpty()) {
            Habit.setError("Habit is required");
            Habit.requestFocus();
            return;
        }

        User user = ShardPrefManager.getInstance(getActivity()).getUser();
        Money money = ShardMoneyManager.getInstance(getActivity()).getMoney();

        Call<MoneyResponse> call = RetrofitClient.getInstance().getApi()
                .updateMoney(money.getId(),saler,rent,habit,user.getId());

        call.enqueue(new Callback<MoneyResponse>() {
            @Override
            public void onResponse(Call<MoneyResponse> call, Response<MoneyResponse> response) {
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if (!response.body().isError()) {
                    ShardMoneyManager.getInstance(getActivity()).saveMoney(response.body().getMoney());
                }
            }

            @Override
            public void onFailure(Call<MoneyResponse> call, Throwable t) {

            }
        });

    }

    private void MoneyLogin(){

        User user = ShardPrefManager.getInstance(getActivity()).getUser();

        Call<MoneyResponse> call = RetrofitClient
                .getInstance().getApi().MoneyLogin(user.getId());

        call.enqueue(new Callback<MoneyResponse>() {
            @Override
            public void onResponse(Call<MoneyResponse> call, Response<MoneyResponse> response) {
               // Log.e("one",String.valueOf(response.body().getMoney().getId()));
                MoneyResponse moneyResponse = response.body();
               // Log.e("one",String.valueOf(moneyResponse.getMoney().getId()));
                if (!moneyResponse.isError()) {

                    ShardMoneyManager.getInstance(getActivity())
                            .saveMoney(moneyResponse.getMoney());

                    Message.setVisibility(View.INVISIBLE);
                    mon.setVisibility(View.VISIBLE);
                    Earn.setText(ShardMoneyManager.getInstance(getActivity()).getMoney().getSaler());
                    Rent.setText(ShardMoneyManager.getInstance(getActivity()).getMoney().getRent());
                    Habit.setText(ShardMoneyManager.getInstance(getActivity()).getMoney().getHabit());
                    ea= Integer.parseInt(ShardMoneyManager.getInstance(getActivity()).getMoney().getSaler());
                    re=Integer.parseInt(ShardMoneyManager.getInstance(getActivity()).getMoney().getRent());
                    ha= Integer.parseInt(ShardMoneyManager.getInstance(getActivity()).getMoney().getHabit());
                    s=ea-(re+ha);

                    somme.setText(String.valueOf(s));



                } else {
                    mon.setVisibility(View.INVISIBLE);
                    Message.setText("Money formulaire not exist");

                }
            }

            @Override
            public void onFailure(Call<MoneyResponse> call, Throwable t) {

            }
        });

    }
}
