package me.lr;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<MeModel> meModels;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.myFirstRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        meModels = new ArrayList<>();
        for (int i=0; i<=10; i++){
            MeModel meModel = new MeModel("Test data " + (i), "This tutorial is going to cover about all the basics of RecyclerView in Android. ");
            meModels.add(meModel);
        }
        adapter = new RVAdapter(meModels, getActivity());
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createItemTochHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private ItemTouchHelper.Callback createItemTochHelperCallback(){
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final MeModel _meModel =  meModels.remove(position);
                adapter.notifyItemRemoved(position);
                Snackbar.make(getActivity().findViewById(R.id.container), "Data removed", Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MeModel temporaryData = _meModel;
                                int temporaryPosition = position;
                                meModels.add(temporaryPosition, temporaryData);
                                adapter.notifyItemInserted(temporaryPosition);
                                temporaryData = null;
                                temporaryPosition = 0;
                            }
                        })
                        .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                super.onDismissed(transientBottomBar, event);
                                MeModel temporaryData = _meModel;
                                int temporaryPosition = position;
                                temporaryData = null;
                                temporaryPosition = 0;
                            }
                        })
                        .show();
            }
        };
        return simpleCallback;
    }
}
