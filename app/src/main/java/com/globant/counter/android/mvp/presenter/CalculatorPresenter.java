package com.globant.counter.android.mvp.presenter;

import android.app.Activity;

import com.globant.counter.android.R;
import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.view.CalculatorView;
import com.globant.counter.android.util.bus.RxBus;
import com.globant.counter.android.util.bus.observers.CountButtonUpBusObserver;

public class CalculatorPresenter {

    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void onCountButtonPressed() {
        String firstInput = view.getFirstInput();
        String secondInput = view.getSecondInput();
        String operatorInput = view.getOperatorInput();

        if(firstInput.isEmpty() || secondInput.isEmpty() || operatorInput.isEmpty()){
            view.setError(R.string.error_empty_field);
        } else if(!operatorInput.matches("[-+*/!]")){
            view.setError(R.string.error_invalid_operator);
        } else {
            model.calculateOperation(Double.parseDouble(view.getFirstInput()),
                    Double.parseDouble(view.getSecondInput()), view.getOperatorInput());
            view.setResult(String.valueOf(model.getResult()));
        }

    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }

        RxBus.subscribe(activity, new CountButtonUpBusObserver() {
            @Override
            public void onEvent(CountButtonUp value) {
                onCountButtonPressed();
            }
        });

    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }
        RxBus.clear(activity);
    }
}
