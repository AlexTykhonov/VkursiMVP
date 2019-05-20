package com.tae.vkursimvp.historypage;

import com.tae.vkursimvp.NbuInterface;
import com.tae.vkursimvp.PojoVal;
import com.tae.vkursimvp.RetrofitClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HistoryModel implements HistoryMainContract.Model {

    Date today = new Date();
    Date yesterday;
    Date datebeforeyesterday;
    ArrayList<PojoVal> mArrayList;

    HistoryMainContract.APIListener apiListener;
    NbuInterface nbuInterface = RetrofitClient.callRetrofit().create(NbuInterface.class);

    public HistoryModel(HistoryPresenter historyPresenter) {
        this.apiListener = historyPresenter;
        calendar(10, "840");
    }

    // 3 dates
    public void calendar(int period, String valcode) {
        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");//
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        for (int i=0;i<=period;i++) {
            calendar.add(Calendar.DATE, -1);
            System.out.println(calendar.getTime()+"+DATA++++++++++++++++++++++++");
            getHistory(valcode,dateformat.format(calendar.getTime()));
        }
        calendar.add(Calendar.DATE, -1);
        yesterday = calendar.getTime();
        System.out.println(dateformat.format(yesterday) + "THIS IS YESTERDAY ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        calendar.add(Calendar.DATE, -1);
        datebeforeyesterday = calendar.getTime();
        System.out.println(dateformat.format(datebeforeyesterday) + "THIS IS DATE BEFORE YESTERDAY ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

    @Override
    public void getHistory (String valcode, String date) {
         nbuInterface.getHistory(valcode,date,"json")
                .subscribeOn(Schedulers.computation())
               .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> System.out.println("Throwable " + throwable.getMessage()))
               .subscribe(apiListener::onSuccess, apiListener::onFailure);

    }



}

