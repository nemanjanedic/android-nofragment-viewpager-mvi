package com.nemanjanedic.nofragmentviewpagermvi.businesslogic.interactor.intro;

import com.nemanjanedic.nofragmentviewpagermvi.businesslogic.repository.GreetingRepository;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class IntroInteractor {

    private final GreetingRepository greetingRepository;

    private String lastLoadedGreeting;

    public IntroInteractor(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Observable<IntroViewState> loadFirstPage() {
        if (lastLoadedGreeting != null) {
            return Observable.just(new IntroViewState.LoadedFirstPage(lastLoadedGreeting));
        } else {
            return greetingRepository.getGreeting().subscribeOn(Schedulers.io()).doOnNext(greeting -> lastLoadedGreeting = greeting).map
                    (IntroViewState.LoadedFirstPage::new).cast(IntroViewState.class).startWith(new IntroViewState.LoadingFirstPage());
        }
    }

    public Observable<IntroViewState> loadSecondPage() {
        if (lastLoadedGreeting != null) {
            return Observable.just(new IntroViewState.LoadedSecondPage(lastLoadedGreeting));
        } else {
            return greetingRepository.getGreeting().subscribeOn(Schedulers.io()).doOnNext(greeting -> lastLoadedGreeting = greeting).map
                    (IntroViewState.LoadedSecondPage::new).cast(IntroViewState.class).startWith(new IntroViewState.LoadingSecondPage());
        }
    }

    public Observable<IntroViewState> loadThirdPage() {
        return Observable.just(new IntroViewState.ThirdPage());
    }

    public Observable<IntroViewState> continueToHome() {
        return Observable.just(new IntroViewState.Continue());
    }

}
