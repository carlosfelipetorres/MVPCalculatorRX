package com.globant.counter.android.mvp.view;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import com.globant.counter.android.R;
import com.globant.counter.android.util.bus.RxBus;
import com.globant.counter.android.util.bus.observers.CountButtonUpBusObserver;
import com.globant.counter.android.util.bus.observers.ResetButtonObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorView extends ActivityView {

    @BindView(R.id.result_label)
    TextView resultLabel;
    @BindView(R.id.first_number_input)
    EditText firstNumberInput;
    @BindView(R.id.second_number_input)
    EditText secondNumberInput;
    @BindView(R.id.operator_input)
    EditText operatorInput;

    public CalculatorView(Activity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void setResult(String result) {
        resultLabel.setText(result);
    }

    public void setError(int id) {
        resultLabel.setText(getActivity().getString(id));
    }

    public String getFirstInput() {
        return firstNumberInput.getText().toString();
    }

    public String getSecondInput() {
        return secondNumberInput.getText().toString();
    }

    public String getOperatorInput() {
        return operatorInput.getText().toString();
    }

    @OnClick(R.id.result_button)
    public void resultButtonPressed() {
        RxBus.post(new CountButtonUpBusObserver.CountButtonUp());
    }

}
