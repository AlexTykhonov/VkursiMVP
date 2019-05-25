package com.tae.vkursimvp.historypage;

import android.widget.Toast;

import com.jjoe64.graphview.series.DataPoint;
import com.tae.vkursimvp.PojoVal;

import java.util.ArrayList;

public class HistoryPresenter implements HistoryMainContract.Presenter, HistoryMainContract.APIListener {

HistoryActivity historyActivity;
HistoryMainContract.View mView;
HistoryMainContract.Model historyModel;

    public HistoryPresenter(HistoryMainContract.View view) {
        mView = view;
        historyModel = new HistoryModel(this);
    }

    void getHistory (String valcode, String date) {
        historyModel.getHistory(valcode, date);
    }

    @Override
    public void onSuccess(ArrayList<PojoVal> pojoNbu) {
        mView.handleResults(pojoNbu);
        System.out.println("ON SUCCESS");
    }

    @Override
    public void onError(ArrayList<PojoVal> pojoNbu) {
    }

    @Override
    public void onFailure(Throwable t) {
    }

    @Override
    public void calendar(int period, String val) {
        historyModel.calendar(period, val);
    }
}
