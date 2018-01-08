package com.nemanjanedic.nofragmentviewpagermvi.presentation.intro;

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter;
import com.nemanjanedic.nofragmentviewpagermvi.businesslogic.interactor.intro.IntroInteractor;
import com.nemanjanedic.nofragmentviewpagermvi.businesslogic.interactor.intro.IntroViewState;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;


public class IntroPresenter extends MviBasePresenter<IntroView, IntroViewState> {

    private final IntroInteractor interactor;

    public IntroPresenter(IntroInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void bindIntents() {
        Observable<IntroViewState> firstPageState = intent(IntroView::loadFirstPageIntent).doOnNext(ignore -> Timber.d("intent: load first page"))
                .switchMap(ignore -> interactor.loadFirstPage()).observeOn(AndroidSchedulers.mainThread());

        Observable<IntroViewState> secondPageState = intent(IntroView::loadSecondPageIntent).doOnNext(ignore -> Timber.d("intent: load second " +
                "page")).switchMap(ignore -> interactor.loadSecondPage()).observeOn(AndroidSchedulers.mainThread());

        Observable<IntroViewState> thirdPageState = intent(IntroView::loadThirdPageIntent).doOnNext(ignore -> Timber.d("intent: load third page"))
                .switchMap(ignore -> interactor.loadThirdPage()).observeOn(AndroidSchedulers.mainThread());

        Observable<IntroViewState> continueToHomeState = intent(IntroView::continueToHomeIntent).doOnNext(ignore -> Timber.d("intent: continue to "
                + "home")).switchMap(ignore -> interactor.continueToHome()).observeOn(AndroidSchedulers.mainThread());

        Observable<IntroViewState> viewState = Observable.merge(firstPageState, secondPageState, thirdPageState, continueToHomeState).doOnNext
                (state -> Timber.d("computed state: " + state));

        subscribeViewState(viewState, IntroView::render);
    }
}
