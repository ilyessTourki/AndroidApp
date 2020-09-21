package ws.wolfsoft.signup.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import ws.wolfsoft.signup.models.Health;

public class ShardHealthManager {

    private static final String SHARED_PREF_NAME_HEALTH = "my_shared_preff";

    private static ShardHealthManager mInstance;
    private Context hCtx;

    private ShardHealthManager(Context hCtx) {
        this.hCtx = hCtx;
    }


    public static synchronized ShardHealthManager getInstance(Context hCtx) {
        if (mInstance == null) {
            mInstance = new ShardHealthManager(hCtx);
        }
        return mInstance;
    }

        public void saveHealth(Health health) {

        SharedPreferences sharedPreferences = hCtx.getSharedPreferences(SHARED_PREF_NAME_HEALTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("healthid", health.getId());
        editor.putInt("userid", health.getUserid());
        editor.putString("height", health.getHeight());
        editor.putString("weight", health.getWeight());
        editor.putString("imc", health.getImc());
        editor.putString("are", health.getAre());
        editor.putString("must", health.getMust());

        editor.apply();

    }

        public Health getHealth() {
        SharedPreferences sharedPreferences = hCtx.getSharedPreferences(SHARED_PREF_NAME_HEALTH, Context.MODE_PRIVATE);
        return new Health(
                sharedPreferences.getInt("healthid", -1),
                sharedPreferences.getInt("userid", -1),
                sharedPreferences.getString("height", null),
                sharedPreferences.getString("weight", null),
                sharedPreferences.getString("imc", null),
                sharedPreferences.getString("are", null),
                sharedPreferences.getString("must", null)

        );
    }

    public void clear() {
        SharedPreferences sharedPreferences = hCtx.getSharedPreferences(SHARED_PREF_NAME_HEALTH, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
