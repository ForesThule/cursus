package forest.les.cursus;

import android.content.Context;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by root on 18.05.17.
 */
public class Helpers {

    public static BankApi createBankApi(Context ctx) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.cbr.ru/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        BankApi bankApi = retrofit.create(BankApi.class);

        return bankApi;
    }

    public static BankApi createBankApi_(Context ctx) {

        String savedServerAdress = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getString(Cv.PREFS_SERVER_ADDRESS, "");

        String mainUrl = "".equals(savedServerAdress)
                ? Cv.PREFS_SERVER_ADDRESS
                : Cv.HTTP + savedServerAdress;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        CookieJar jar = new CookieJar() {

            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {

                List<Cookie> cookies = cookieStore.get(url.host());

                return null != cookies ? cookies : new ArrayList<>();
            }
        };

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(300, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .cookieJar(jar);

        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(interceptor);
        }

        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(mainUrl)
                .client(client.build())
                .build()
                .create(BankApi.class);
    }
}
