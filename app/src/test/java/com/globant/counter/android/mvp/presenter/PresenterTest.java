package com.globant.counter.android.mvp.presenter;

import com.globant.counter.android.R;
import com.globant.counter.android.mvp.model.CalculatorModel;
import com.globant.counter.android.mvp.model.CountModel;
import com.globant.counter.android.mvp.view.CalculatorView;
import com.globant.counter.android.mvp.view.CountView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PresenterTest {

    private CalculatorPresenter presenter;
    @Mock CalculatorModel model;
    @Mock CalculatorView view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new CalculatorPresenter(model, view);
    }

    @Test
    public void shouldCalculateResult() {
        when(model.getResult()).thenReturn(12.0);
        when(view.getFirstInput()).thenReturn("6.0");
        when(view.getSecondInput()).thenReturn("6.0");
        when(view.getOperatorInput()).thenReturn("+");
        presenter.onCountButtonPressed();
    }

    @Test
    public void shouldShowOperatorError() {
        when(model.getResult()).thenReturn(0.0);
        when(view.getFirstInput()).thenReturn("6.0");
        when(view.getSecondInput()).thenReturn("6.0");
        when(view.getOperatorInput()).thenReturn("+*");
        presenter.onCountButtonPressed();
        verify(view).setError(R.string.error_invalid_operator);
    }

    @Test
    public void shouldShowInputError() {
        when(model.getResult()).thenReturn(0.0);
        when(view.getFirstInput()).thenReturn("");
        when(view.getSecondInput()).thenReturn("6.0");
        when(view.getOperatorInput()).thenReturn("+");
        presenter.onCountButtonPressed();
        verify(view).setError(R.string.error_empty_field);
    }

}