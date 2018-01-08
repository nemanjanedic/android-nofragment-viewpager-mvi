package com.nemanjanedic.nofragmentviewpagermvi.presentation.intro;

import com.hannesdorfmann.mosby3.mvp.MvpView;
import com.nemanjanedic.nofragmentviewpagermvi.businesslogic.interactor.intro.IntroViewState;

import io.reactivex.Observable;

public interface IntroView extends MvpView {

    /**
     * The intent to load the first page.
     */
    Observable<Object> loadFirstPageIntent();

    /**
     * The intent to load the second page.
     */
    Observable<Object> loadSecondPageIntent();

    /**
     * The intent to load the third page.
     */
    Observable<Object> loadThirdPageIntent();

    /**
     * The intent to continue to another the Home MVI section.
     */
    Observable<Object> continueToHomeIntent();

    /**
     * Render the state of the UI.
     */
    void render(IntroViewState state);
}
