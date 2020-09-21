package ws.wolfsoft.signup.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import ws.wolfsoft.signup.models.User;

public class ShardPrefManager {

    private static final String SHARED_PREF_NAME = "my_shared_preff";

    private static ShardPrefManager mInstance;
    private Context mCtx;

    private ShardPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }


    public static synchronized ShardPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new ShardPrefManager(mCtx);
        }
        return mInstance;
    }


    public void saveUser(User user) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("email", user.getEmail());
        editor.putInt("point", user.getPoint());

        editor.apply();

    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getInt("point", -1)

        );
    }


    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
