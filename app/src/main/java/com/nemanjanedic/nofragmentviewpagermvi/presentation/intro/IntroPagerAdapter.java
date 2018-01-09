package com.nemanjanedic.nofragmentviewpagermvi.presentation.intro;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;
import com.nemanjanedic.nofragmentviewpagermvi.R;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class IntroPagerAdapter extends PagerAdapter {

    private final Context context;

    // Using BehaviorRelay here to be able to set the text of the text view when app is started
    private Relay<String> firstPageGreetingText = BehaviorRelay.create();
    private Disposable firstPageGreetingTextDisposable;

    private Relay<String> secondPageGreetingText = PublishRelay.create();
    private Disposable secondPageGreetingTextDisposable;

    private Relay<Object> continueToHomeClicks = PublishRelay.create();
    private Disposable continueToHomeClicksDisposable;

    public IntroPagerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        IntroPage page = IntroPage.values()[position];
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(page.getLayoutResId(), container, false);
        container.addView(view);
        switch (position) {
            case 0:
                firstPageGreetingTextDisposable = firstPageGreetingText.subscribe(RxTextView.text(view.findViewById(R.id.greeting)));
                break;
            case 1:
                secondPageGreetingTextDisposable = secondPageGreetingText.subscribe(RxTextView.text(view.findViewById(R.id.greeting)));
                break;
            case 2:
                continueToHomeClicksDisposable = RxView.clicks(view.findViewById(R.id.continue_to_home)).subscribe(continueToHomeClicks);
                break;
            default:
        }
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        switch (position) {
            case 0:
                firstPageGreetingTextDisposable.dispose();
                break;
            case 1:
                secondPageGreetingTextDisposable.dispose();
                break;
            case 2:
                continueToHomeClicksDisposable.dispose();
                break;
            default:
        }
    }

    @Override
    public int getCount() {
        return IntroPage.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        IntroPage page = IntroPage.values()[position];
        return context.getString(page.getTitleResId());
    }

    public void setFirstPageGreetingText(String text) {
        firstPageGreetingText.accept(text);
    }

    public void setSecondPageGreetingText(String text) {
        secondPageGreetingText.accept(text);
    }

    public Observable<Object> getContinueToHomeClicks() {
        return continueToHomeClicks;
    }

    public enum IntroPage {
        FIRST_PAGE(R.string.intro_first_page_title, R.layout.intro_first_page), SECOND_PAGE(R.string.intro_second_page_title, R.layout
                .intro_second_page), THIRD_PAGE(R.string.intro_third_page_title, R.layout.intro_third_page);

        private final int titleResId;
        private final int layoutResId;

        IntroPage(@StringRes int titleResId, @LayoutRes int layoutResId) {
            this.titleResId = titleResId;
            this.layoutResId = layoutResId;
        }

        @StringRes
        public int getTitleResId() {
            return titleResId;
        }

        @LayoutRes
        public int getLayoutResId() {
            return layoutResId;
        }
    }
}
