package com.globant.counter.android.mvp.presenter;

import android.app.Activity;

import com.globant.counter.android.R;
import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.view.CalculatorView;
import com.globant.counter.android.util.bus.RxBus;
import com.globant.counter.android.util.bus.observers.CountButtonUpBusObserver;

public class CalculatorPresenter {

    private CalculatorModel calculatorModel;
    private CalculatorView calculatorView;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.calculatorModel = model;
        this.calculatorView = view;
    }

    public void onCountButtonPressed() {
        String firstInput = calculatorView.getFirstInput();
        String secondInput = calculatorView.getSecondInput();
        String operatorInput = calculatorView.getOperatorInput();

        if(firstInput.isEmpty() || secondInput.isEmpty() || operatorInput.isEmpty()){
            calculatorView.setError(R.string.error_empty_field);
        } else if(!operatorInput.matches("[-+*/!]")){
            calculatorView.setError(R.string.error_invalid_operator);
        } else {
            calculatorModel.calculateOperation(Double.parseDouble(calculatorView.getFirstInput()),
                    Double.parseDouble(calculatorView.getSecondInput()), calculatorView.getOperatorInput());
            calculatorView.setResult(String.valueOf(calculatorModel.getResult()));
        }

    }

    public void register() {
        Activity activity = calculatorView.getActivity();

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
        Activity activity = calculatorView.getActivity();

        if (activity == null) {
            return;
        }
        RxBus.clear(activity);
    }
}
