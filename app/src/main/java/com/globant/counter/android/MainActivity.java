package com.globant.counter.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.presenter.CalculatorPresenter;
import com.globant.counter.android.mvp.view.CalculatorView;

public class MainActivity extends AppCompatActivity {

    private CalculatorPresenter calculatorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        calculatorPresenter = new CalculatorPresenter(new CalculatorModel(), new CalculatorView(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculatorPresenter.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        calculatorPresenter.unregister();
    }
}
