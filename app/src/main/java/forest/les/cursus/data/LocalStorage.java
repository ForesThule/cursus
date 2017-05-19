package forest.les.cursus.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import forest.les.cursus.Cv;
import forest.les.cursus.ThisApp;
import forest.les.cursus.model.ValCurs;

import static android.R.attr.data;

/**
 * Created by root on 18.05.17.
 */

public class LocalStorage {

    static Type type = new TypeToken<Map<String, ValCurs>>() {
    }.getType();

    public synchronized static void saveValcurs(Context ctx, ValCurs valCurs) {

        HashMap<String, ValCurs> res = getValCurs(ctx);

        res.put(valCurs.date,valCurs);

        String jsonString = new Gson().toJson(res);
        getSpecificStorage(ctx, Cv.PREFS_VALCURS_MAP)
                .edit()
                .putString(Cv.REPO_VALCURS, jsonString)
                .apply();

    }

    public synchronized static HashMap<String,ValCurs> getValCurs(Context ctx) {

        String jsonString = getSpecificStorage(ctx, Cv.PREFS_VALCURS_MAP)
                .getString(Cv.REPO_VALCURS, "");

        HashMap <String, ValCurs> valcurses = null;

        try {
            valcurses = new Gson().fromJson(jsonString,type);

            if (valcurses==null){
                valcurses = new HashMap<>();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return valcurses;
    }

        private static SharedPreferences getSpecificStorage(Context ctx, String prefsName) {

            ThisApp thisApp = ThisApp.get(ctx);
            String key = "CURSUS_PREFERENCES";

            return ctx.getSharedPreferences(key, Context.MODE_PRIVATE);
        }


    }




