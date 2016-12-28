package com.example.sarvenazgolchinniksirat.costdivider.ui.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sarvenazgolchinniksirat.costdivider.R;
import com.example.sarvenazgolchinniksirat.costdivider.data.model.TripOverviewData;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TripOverviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<TripOverviewData> mTripsNameList;

    public TripOverviewAdapter(@NonNull final List<TripOverviewData> mTripsNameList) {
        this.mTripsNameList = mTripsNameList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_overview_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        onBindItemViewHolder((ItemViewHolder) holder, position);
    }

    /**
     * Sets the item related information to the view.
     *
     * @param holder
     * @param position
     */
    private void onBindItemViewHolder(@NonNull final ItemViewHolder holder, @NonNull final int position) {

        holder.tripNameTV.setText(mTripsNameList.get(position).getTripName());

    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return mTripsNameList.size();
    }

    public void addItem(TripOverviewData data) {
        mTripsNameList.add(data);
        notifyItemInserted(mTripsNameList.size());
    }

    public void removeItem(int position) {
        mTripsNameList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mTripsNameList.size());
    }
    /**
     * The items view holder.
     */
    public static final class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.trip_name_textview)
        protected TextView tripNameTV;

        private ItemViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
