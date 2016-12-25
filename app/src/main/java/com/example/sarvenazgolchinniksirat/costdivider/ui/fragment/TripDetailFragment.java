package com.example.sarvenazgolchinniksirat.costdivider.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sarvenazgolchinniksirat.costdivider.R;

import butterknife.ButterKnife;

public class TripDetailFragment extends Fragment {

    @NonNull
    public static TripDetailFragment newInstance(){

        final TripDetailFragment fragment = new TripDetailFragment();

        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.details_fragment, container, false);
        ButterKnife.bind(this, rootView);

       /* stepListRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this));
        initAdapter();*/

        return rootView;
    }
}
