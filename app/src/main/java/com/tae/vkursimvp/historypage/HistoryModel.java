package com.tae.vkursimvp.historypage;

import com.tae.vkursimvp.NbuInterface;
import com.tae.vkursimvp.PojoVal;
import com.tae.vkursimvp.RetrofitClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HistoryModel implements HistoryMainContract.Model {

    Date today = new Date();
    Date yesterday;
    Date datebeforeyesterday;
    ArrayList<PojoVal> mArrayList;
    ArrayList<String> labelString = new ArrayList();
    HistoryMainContract.APIListener apiListener;
    NbuInterface nbuInterface = RetrofitClient.callRetrofit().create(NbuInterface.class);

    public HistoryModel(HistoryPresenter historyPresenter) {
        this.apiListener = historyPresenter;
        calendar(10, "840");
    }
    public void calendar(int period, String valcode) {
        DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");//
        DateFormat labelFormat1 = new SimpleDateFormat("dd.MM");//
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        // first element of x-axis label
        labelString.clear();
        labelString.add(labelFormat1.format(today));

        for (int i=0;i<=period;i++) {
            calendar.add(Calendar.DATE, -1);
            labelString.add(labelFormat1.format(calendar.getTime()));
            System.out.println(calendar.getTime()+"+DATA++++++++++++++++++++++++");
            getHistory(valcode,dateformat.format(calendar.getTime()));
        }
        Collections.reverse(labelString);
        System.out.println("????"+ labelString+ "======================== LABEL STRING =======================");
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

