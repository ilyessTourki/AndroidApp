package ws.wolfsoft.signup.Storage;

import android.content.Context;
import android.content.SharedPreferences;


import ws.wolfsoft.signup.models.Money;

public class ShardMoneyManager {

    private static final String SHARED_PREF_NAME_MONEY = "my_shared_preff";

    private static ShardMoneyManager mInstance;
    private Context Ctx;

    private ShardMoneyManager(Context Ctx) {
        this.Ctx = Ctx;
    }


    public static synchronized ShardMoneyManager getInstance(Context Ctx) {
        if (mInstance == null) {
            mInstance = new ShardMoneyManager(Ctx);
        }
        return mInstance;
    }

    public void saveMoney(Money money) {

        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(SHARED_PREF_NAME_MONEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("moneyid", money.getId());
        editor.putInt("userid", money.getUserid());
        editor.putString("saler", money.getSaler());
        editor.putString("rent", money.getRent());
        editor.putString("habit", money.getHabit());

        editor.apply();

    }

    public Money getMoney() {
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(SHARED_PREF_NAME_MONEY, Context.MODE_PRIVATE);
        return new Money(
                sharedPreferences.getInt("moneyid", -1),
                sharedPreferences.getInt("userid", -1),
                sharedPreferences.getString("saler", null),
                sharedPreferences.getString("rent", null),
                sharedPreferences.getString("habit", null)


        );
    }

    public void clear() {
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(SHARED_PREF_NAME_MONEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
