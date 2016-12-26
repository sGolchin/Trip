package com.example.sarvenazgolchinniksirat.costdivider.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sarvenazgolchinniksirat.costdivider.R;
import com.example.sarvenazgolchinniksirat.costdivider.ui.adapter.RecyclerItemClickListener;
import com.example.sarvenazgolchinniksirat.costdivider.ui.adapter.TripOverviewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TripsOverviewFragment extends Fragment implements RecyclerItemClickListener.OnItemClickListener{

    @Bind(R.id.trip_list)
    protected RecyclerView tripsRecyclerView;

    private TripOverviewAdapter mAdapter;

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

    /*@OnClick(R.id.add_button)
    public void goToTripDetail(){
        FragmentNavigationUtil.showFragment(TripDetailFragment.newInstance(), getActivity().getSupportFragmentManager(), R.id.content_pane);

    }*/

    /**
     * Initializes the recyclerview.
     */
    private void initRecyclerView() {
        /*tripsRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this));
        tripsRecyclerView.addItemDecoration(new CustomItemDecoration(getActivity(), CustomItemDecoration.VERTICAL_LIST, true));*/
    }

    private void initAdapter() {
        tripsRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        tripsRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new TripOverviewAdapter();
        tripsRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(View view, int position) {
            handleAdapterOnClickEvent(position);

    }

    private void handleAdapterOnClickEvent(@NonNull final int position) {
       // FotoNavigation.showStepOverview(getFragmentManager(), caseIdValidationResponseBean.getCaseId(), mAdapter.getIdentificationGroup(position));
    }
}
