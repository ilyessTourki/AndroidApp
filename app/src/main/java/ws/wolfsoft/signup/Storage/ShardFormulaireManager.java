package ws.wolfsoft.signup.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import ws.wolfsoft.signup.models.formulaire;

public class ShardFormulaireManager {

    private static final String SHARED_PREF_NAME_FORMULAIRE = "my_shared_preff";

    private static ShardFormulaireManager mInstance;
    private Context fCtx;

    public ShardFormulaireManager(Context fCtx) {
        this.fCtx = fCtx;
    }

    public static synchronized ShardFormulaireManager getInstance(Context fCtx) {
        if (mInstance == null) {
            mInstance = new ShardFormulaireManager(fCtx);
        }
        return mInstance;
    }

    public void saveFormulair(formulaire formulaire) {

        SharedPreferences sharedPreferences = fCtx.getSharedPreferences(SHARED_PREF_NAME_FORMULAIRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("formulaireid", formulaire.getId());
        editor.putInt("userid", formulaire.getUserid());
        editor.putString("age", formulaire.getAge());
        editor.putString("activite", formulaire.getActivite());
        editor.putString("situation", formulaire.getSituation());

        editor.apply();

    }

        public formulaire getFormulaire() {
        SharedPreferences sharedPreferences = fCtx.getSharedPreferences(SHARED_PREF_NAME_FORMULAIRE, Context.MODE_PRIVATE);
        return new formulaire(
                sharedPreferences.getInt("formulaireid", -1),
                sharedPreferences.getInt("userid", -1),
                sharedPreferences.getString("age", null),
                sharedPreferences.getString("activite", null),
                sharedPreferences.getString("situation", null)

        );
    }

    public void clear() {
        SharedPreferences sharedPreferences = fCtx.getSharedPreferences(SHARED_PREF_NAME_FORMULAIRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
