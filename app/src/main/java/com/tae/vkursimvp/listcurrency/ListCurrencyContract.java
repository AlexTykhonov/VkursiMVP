package com.tae.vkursimvp.listcurrency;

import com.tae.vkursimvp.PojoVal;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Response;

public interface ListCurrencyContract {

    interface Model {

        void getCurrencyList (APIListener apiListener);

    }

    interface View {
        void displayCurrency (ArrayList<PojoVal> pojoNbu);
    }

    interface Presenter {

    }

    interface APIListener {

        void onSuccess(ArrayList<PojoVal> pojoNbu);
        void onError(ArrayList<PojoVal> pojoNbu);
        void onFailure(Throwable t);
    }
}
