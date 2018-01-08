package com.nemanjanedic.nofragmentviewpagermvi.presentation.intro;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvi.MviFragment;
import com.jakewharton.rxbinding2.support.v4.view.RxViewPager;
import com.nemanjanedic.nofragmentviewpagermvi.MviApplication;
import com.nemanjanedic.nofragmentviewpagermvi.R;
import com.nemanjanedic.nofragmentviewpagermvi.businesslogic.interactor.intro.IntroViewState;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

public class IntroFragment extends MviFragment<IntroView, IntroPresenter> implements IntroView {

    @BindView(R.id.intro_pager)
    ViewPager introPager;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        unbinder = ButterKnife.bind(this, view);

        introPager.setAdapter(new IntroPagerAdapter(getActivity()));

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
        return Observable.just(new Object()).delay(10, TimeUnit.SECONDS);
    }

    @Override
    public void render(IntroViewState state) {
        // to be implemented
    }
}
