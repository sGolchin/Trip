package com.example.sarvenazgolchinniksirat.costdivider.ui.fragment;


import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sarvenazgolchinniksirat.costdivider.R;
import com.example.sarvenazgolchinniksirat.costdivider.data.model.TripOverviewData;
import com.example.sarvenazgolchinniksirat.costdivider.ui.adapter.RecyclerItemClickListener;
import com.example.sarvenazgolchinniksirat.costdivider.ui.adapter.TripOverviewAdapter;
import com.example.sarvenazgolchinniksirat.costdivider.ui.component.CustomItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TripsOverviewFragment extends Fragment implements RecyclerItemClickListener.OnItemClickListener{


    private AlertDialog.Builder alertDialog;
    private EditText et_country;
    private View view;

    @Bind(R.id.trip_list)
    protected RecyclerView tripsRecyclerView;

    private TripOverviewAdapter mAdapter;

    List<TripOverviewData> tripsNameList = new ArrayList<>();

    private Paint p = new Paint();
    private int edit_position;
    private boolean add = false;

    @NonNull
    public static TripsOverviewFragment newInstance() {

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

        initRecyclerView();
        initAdapter();
        initDialog();
        initSwipe();

        return rootView;
    }

   private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    mAdapter.removeItem(position);
                    //TODO
                    //show dialog, if yes, delete the item
                } else {
                    removeView();
                    edit_position = position;
                    alertDialog.setTitle(R.string.edit_name);
                    et_country.setText(tripsNameList.get(position).getTripName());
                    alertDialog.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX > 0) {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit_white);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_white);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(tripsRecyclerView);
    }

    private void removeView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    private void initDialog() {
        alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setCancelable(false);
        view = getActivity().getLayoutInflater().inflate(R.layout.dialog_layout, null);
        alertDialog.setView(view);
        alertDialog.setPositiveButton(R.string.Save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(add){
                    add =false;
                    TripOverviewData tripOverviewData = new TripOverviewData();
                    tripOverviewData.setTripName(et_country.getText().toString());
                    mAdapter.addItem(tripOverviewData);
                    dialog.dismiss();
                } else {
                    TripOverviewData tripOverviewData = new TripOverviewData();
                    tripOverviewData.setTripName(et_country.getText().toString());
                    tripsNameList.set(edit_position, tripOverviewData);
                    mAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                mAdapter.notifyDataSetChanged();
            }
        });
        et_country = (EditText) view.findViewById(R.id.et_country);
    }

    @OnClick(R.id.fab_add)
    protected void addTrip() {
        removeView();
        add = true;
        alertDialog.setTitle(R.string.trip_name_dialog_title);
        et_country.setText("");
        alertDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * Initializes the recyclerview.
     */
    private void initRecyclerView() {
        tripsRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this));
        tripsRecyclerView.addItemDecoration(new CustomItemDecoration(getActivity(), CustomItemDecoration.VERTICAL_LIST));
    }

    private void initAdapter() {
        tripsRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        tripsRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new TripOverviewAdapter(tripsNameList);
        tripsRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "The clicked position " + position, Toast.LENGTH_SHORT).show();
    }
}