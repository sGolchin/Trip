package com.example.sarvenazgolchinniksirat.costdivider.util;


import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentNavigationUtil {

    /**
     * Displays the group overview fragment.
     */
    public static void showFragment(@NonNull final Fragment newFragment, @NonNull final FragmentManager manager, @IdRes final int containerViewId) {

        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(containerViewId, newFragment, newFragment.getClass().getName()).addToBackStack(null).commit();

    }
}
