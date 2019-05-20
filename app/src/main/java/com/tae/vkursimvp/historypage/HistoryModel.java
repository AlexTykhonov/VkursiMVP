package com.tae.vkursimvp.historypage;

import com.tae.vkursimvp.NbuInterface;
import com.tae.vkursimvp.PojoVal;
import com.tae.vkursimvp.RetrofitClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Retrofit;

public class HistoryModel implements HistoryMainContract.Model {

    Date today = new Date();
    Date yesterday;
    Date datebeforeyesterday;

    HistoryMainContract.APIListener apiListener;

    NbuInterface nbuInterface = RetrofitClient.callRetrofit().create(NbuInterface.class);

    // 3 dates
    public void calendar() {
        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");//
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, -1);
        yesterday = calendar.getTime();
        System.out.println(dateformat.format(yesterday) + "THIS IS YESTERDAY ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        calendar.add(Calendar.DATE, -1);
        datebeforeyesterday = calendar.getTime();
        System.out.println(dateformat.format(datebeforeyesterday) + "THIS IS DATE BEFORE YESTERDAY ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

    @Override
    public ArrayList<PojoVal> getHistory(String s1, String s2) {
        return null;
    }

//    @Override
//    public Observable<ArrayList<PojoVal>> getHistory (String valcode, String date) {
//        return (Observable<ArrayList<PojoVal>>) nbuInterface.getHistory(valcode,date,"json")
//                .subscribeOn(Schedulers.computation())
//               .observeOn(AndroidSchedulers.mainThread())
//                .doOnError(throwable -> System.out.println("Throwable " + throwable.getMessage()))
//                .subscribe(data-> System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+data)); //del later
//               .subscribe(apiListener::onSuccess, apiListener::onFailure);
  //  }



}

