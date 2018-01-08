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

import com.nemanjanedic.nofragmentviewpagermvi.R;

public class IntroPagerAdapter extends PagerAdapter {

    private final Context context;

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
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
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
        return context.getString(page.titleResId);
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
