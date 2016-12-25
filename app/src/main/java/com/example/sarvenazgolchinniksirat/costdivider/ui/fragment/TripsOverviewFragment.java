package com.example.sarvenazgolchinniksirat.costdivider.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sarvenazgolchinniksirat.costdivider.R;
import com.example.sarvenazgolchinniksirat.costdivider.util.FragmentNavigationUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class TripsOverviewFragment extends Fragment{

    @NonNull
    public static TripsOverviewFragment newInstance(){

        final TripsOverviewFragment fragment = new TripsOverviewFragment();

        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.overview_fragment, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick(R.id.add_button)
    public void goToTripDetail(){
        FragmentNavigationUtil.showFragment(TripDetailFragment.newInstance(), getActivity().getSupportFragmentManager(), R.id.content_pane);

    }
}
