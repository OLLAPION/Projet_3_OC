package com.openclassrooms.entrevoisins.utils;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.hamcrest.CoreMatchers.allOf;

import android.support.design.widget.TabLayout;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.Objects;

public class ToolBarSelect implements ViewAction{
    private final int tabIndex;

    public ToolBarSelect(int tabIndex) {
        this.tabIndex = tabIndex;
    }
    @Override
    public Matcher<View> getConstraints() {
        return allOf(isDisplayed(), isAssignableFrom(TabLayout.class));
    }

    @Override
    public String getDescription() {
        return "Faire une action sur un TabItem";
    }

    @Override
    public void perform(UiController uiController, View view) {
        TabLayout tabLayout = (TabLayout) view;
        TabLayout.Tab tab = tabLayout.getTabAt(tabIndex);
        try {
            Objects.requireNonNull(tab).select();
        } catch (NullPointerException e) {
            throw new PerformException.Builder().withCause(new Throwable("C'est une erreur")).build();
        }
    }
}
