package com.nemanjanedic.nofragmentviewpagermvi.presentation.intro;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvi.MviFragment;
import com.jakewharton.rxbinding2.support.v4.view.RxViewPager;
import com.nemanjanedic.nofragmentviewpagermvi.MainActivity;
import com.nemanjanedic.nofragmentviewpagermvi.MviApplication;
import com.nemanjanedic.nofragmentviewpagermvi.R;
import com.nemanjanedic.nofragmentviewpagermvi.businesslogic.interactor.intro.IntroViewState;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

public class IntroFragment extends MviFragment<IntroView, IntroPresenter> implements IntroView {

    public static final String LOADING = "Loading...";
    @BindView(R.id.intro_pager)
    ViewPager introPager;

    private IntroPagerAdapter introPagerAdapter;

    private Unbinder unbinder;

    public IntroFragment() {
        // Required empty public constructor
    }

    public static IntroFragment newInstance() {
        return new IntroFragment();
    }

    @NonNull
    @Override
    public IntroPresenter createPresenter() {
        return MviApplication.getDependencyInjection(getActivity()).newIntroPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        unbinder = ButterKnife.bind(this, view);

        introPagerAdapter = new IntroPagerAdapter(getActivity());
        introPager.setAdapter(introPagerAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Observable<Object> loadFirstPageIntent() {
        return Observable.just(new Object()).mergeWith(RxViewPager.pageSelections(introPager).filter(page -> page == 0).cast(Object.class));
    }

    @Override
    public Observable<Object> loadSecondPageIntent() {
        return RxViewPager.pageSelections(introPager).filter(page -> page == 1).cast(Object.class);
    }

    @Override
    public Observable<Object> loadThirdPageIntent() {
        return RxViewPager.pageSelections(introPager).filter(page -> page == 2).cast(Object.class);
    }

    @Override
    public Observable<Object> continueToHomeIntent() {
        return introPagerAdapter.getContinueToHomeClicks();
    }

    @Override
    public void render(IntroViewState state) {
        if (state instanceof IntroViewState.LoadingFirstPage) {
            introPagerAdapter.setFirstPageGreetingText(LOADING);
        } else if (state instanceof IntroViewState.LoadedFirstPage) {
            introPagerAdapter.setFirstPageGreetingText(((IntroViewState.LoadedFirstPage) state).getGreeting());
        } else if (state instanceof IntroViewState.LoadingSecondPage) {
            introPagerAdapter.setSecondPageGreetingText(LOADING);
        } else if (state instanceof IntroViewState.LoadedSecondPage) {
            introPagerAdapter.setSecondPageGreetingText(((IntroViewState.LoadedSecondPage) state).getGreeting());
        } else if (state instanceof IntroViewState.ThirdPage) {
            // nothing to do
        } else if (state instanceof IntroViewState.ContinueToHome) {
            // exit this MVI fragment and load another
            // navigation in the app should be handled better, this is used only for simplicity of the sample
            ((MainActivity) getActivity()).loadHome();
        }
    }
}
