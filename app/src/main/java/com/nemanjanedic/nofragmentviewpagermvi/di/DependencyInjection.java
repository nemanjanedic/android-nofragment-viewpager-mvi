package com.nemanjanedic.nofragmentviewpagermvi.di;

import com.nemanjanedic.nofragmentviewpagermvi.businesslogic.interactor.intro.IntroInteractor;
import com.nemanjanedic.nofragmentviewpagermvi.businesslogic.repository.GreetingRepository;

/**
 * Primitive example of dependency inject not to be used in the real projects.
 */
public class DependencyInjection {

    private final GreetingRepository greetingRepository = new GreetingRepository();

    private IntroInteractor newIntroInteractor() {
        return new IntroInteractor(greetingRepository);
    }
}
