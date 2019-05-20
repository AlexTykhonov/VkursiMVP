package com.tae.vkursimvp.listcurrency;

import com.tae.vkursimvp.PojoVal;

import java.util.ArrayList;

public class MainPresenter implements ListCurrencyContract.Presenter, ListCurrencyContract.APIListener {

    ListCurrencyContract.View mView;
    ListCurrencyContract.Model mModel;

    public MainPresenter(ListCurrencyContract.View view) {
        this.mView = view;
        mModel = new ListCurrencyModel();
        mModel.getCurrencyList(this);
    }


    @Override
    public void onSuccess(ArrayList<PojoVal> pojoNbu) {
       mView.displayCurrency(pojoNbu);
        System.out.println(pojoNbu+"_________________presenter_____________________---------------");
    }

    @Override
    public void onError(ArrayList<PojoVal> pojoNbu) {

    }

    @Override
    public void onFailure(Throwable t) {
        System.out.println("FAILURE !!!!!!!!!!!!!!!!"+t);
    }


}
