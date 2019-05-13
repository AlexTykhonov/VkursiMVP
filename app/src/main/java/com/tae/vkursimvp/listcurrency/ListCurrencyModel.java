package com.tae.vkursimvp.listcurrency;

import com.tae.vkursimvp.NbuInterface;
import com.tae.vkursimvp.RetrofitClient;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Schedulers;


public class ListCurrencyModel implements ListCurrencyContract.Model {
    NbuInterface nbuInterface = RetrofitClient.callRetrofit().create(NbuInterface.class);

    @Override
    public void getCurrencyList(ListCurrencyContract.APIListener apiListener) {
        nbuInterface.getNbuData()
               // .subscribeOn(Schedulers.computation())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiListener.onSuccess());

    }
}
