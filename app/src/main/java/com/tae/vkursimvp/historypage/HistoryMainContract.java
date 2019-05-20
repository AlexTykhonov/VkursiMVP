package com.tae.vkursimvp.historypage;

import android.widget.Toast;

import com.jjoe64.graphview.series.DataPoint;
import com.tae.vkursimvp.PojoVal;
import com.tae.vkursimvp.listcurrency.ListCurrencyContract;

import java.util.ArrayList;

public interface HistoryMainContract {

    interface Model {
        void getHistory (String s1, String s2);
        void calendar(int period, String valcode);
    }

    interface View {
//        void createGraphView (DataPoint dataPoint);
     void handleResults(ArrayList<PojoVal> pojoNbu);
//        void handleError(Throwable t);
    }

    interface Presenter {
        void calendar (int period, String val);
    }

    interface APIListener {

        void onSuccess(ArrayList<PojoVal> pojoNbu);
        void onError(ArrayList<PojoVal> pojoNbu);
        void onFailure(Throwable t);
    }
}
