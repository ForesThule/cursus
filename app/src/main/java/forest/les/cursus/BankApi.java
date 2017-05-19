package forest.les.cursus;

import java.util.List;

import forest.les.cursus.model.ValCurs;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by root on 16.05.17.
 */

public interface BankApi {
    @GET("scripts/XML_daily.asp")
    Observable<ValCurs> getData(@Query("date_req") String date_req);
////    http://www.cbr.ru/scripts/XML_daily.asp?date_req=02/03/2002

}
