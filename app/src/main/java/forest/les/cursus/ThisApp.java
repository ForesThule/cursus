package forest.les.cursus;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;


public class ThisApp extends Application {

    private BankApi api;


    public static ThisApp get(Context ctx) {
        return (ThisApp) ctx.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        api = Helpers.createBankApi(this);

        if (BuildConfig.DEBUG) {

            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) +
                            ":timber: line=" + element.getLineNumber() +
                            " method: " + element.getMethodName();
                }
            });
        }
    }

    public static BankApi getApi(Context context) {
        return ThisApp.get(context).api;
    }

//    public void refreshBankApi() {api = Helpers.createBankApi(this);}

//    public String getCurrentUser() {
//        return currentUser;
//    }

//    public void setCurrentUser(String currentUser) {
//        this.currentUser = currentUser;
//    }

//    public String getCurrentPwd() {
//        return currentPwd;
//    }





//    public void setCurrentPwd(String currentPwd) {
//
//        this.currentPwd = currentPwd;
//
//        // Test mode or not test mode, that is a question!
//        boolean testMode = Cv.TEST_TEST.equals(currentUser + currentPwd);
//
//        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
//        editor.putBoolean(Cv.PREFS_TEST_MODE, testMode);
//
//        if (testMode) {
//
//            String name = getString(R.string.test_user);
//            editor.putString(Cv.PREFS_USER_DISPLAY_NAME, name);
//            currentUserDisplayName = name;
//        }
//        editor.apply();
//    }
 }
