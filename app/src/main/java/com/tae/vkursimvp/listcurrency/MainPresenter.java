package com.tae.vkursimvp.listcurrency;

import com.tae.vkursimvp.PojoVal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        if (pojoNbu != null ) {
            System.out.println("&&*^&*^&*^*^^*&^*^*&^*&^^&*^**&^^&* THIS IS POJO NBU!!!!!    ---> " + pojoNbu);
            for (int i = 0; i < pojoNbu.size(); i++) {
                if (pojoNbu.get(i).getR030() == 840) {
                    pojoNbu.get(i).setPriority(1);
                } else if (pojoNbu.get(i).getR030() == 978) {
                    pojoNbu.get(i).setPriority(2);
                } else if (pojoNbu.get(i).getR030() == 826) {
                    pojoNbu.get(i).setPriority(3);
                } else if (pojoNbu.get(i).getR030() == 985) {
                    pojoNbu.get(i).setPriority(4);
                } else {
                    pojoNbu.get(i).setPriority(5);
                }
            }
        }

        // добавляем сортировку
        class PriorityComparator implements Comparator<PojoVal> {
            @Override
            public int compare(PojoVal o1, PojoVal o2) {
                return o1.compareTo(o2);
            }
        }
        Collections.sort(pojoNbu,new PriorityComparator());
        //

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
