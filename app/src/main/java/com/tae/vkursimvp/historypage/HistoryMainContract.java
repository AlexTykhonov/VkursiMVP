package com.tae.vkursimvp.historypage;

import android.widget.Toast;

import com.jjoe64.graphview.series.DataPoint;
import com.tae.vkursimvp.PojoVal;
import com.tae.vkursimvp.listcurrency.ListCurrencyContract;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;

public interface HistoryMainContract {

    interface Model {
       // Observable<ArrayList<PojoVal>> getHistory (String s1, String s2);
        ArrayList<PojoVal> getHistory (String s1, String s2);
    }

    interface View {
//        void createGraphView (DataPoint dataPoint);
     void handleResults(ArrayList<PojoVal> pojoNbu);
//        void handleError(Throwable t);
    }

    interface Presenter {

    }

    interface APIListener {

        void onSuccess(ArrayList<PojoVal> pojoNbu);
        void onError(ArrayList<PojoVal> pojoNbu);
        void onFailure(Throwable t);
    }
}
