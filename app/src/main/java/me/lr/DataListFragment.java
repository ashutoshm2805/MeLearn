package me.lr;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 11/22/2017.
 */

public class DataListFragment extends ListFragment {
    OnDataListSelectedListener mCallback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView lv = getActivity().findViewById(android.R.id.list);
        /*ArrayList<String> items = new ArrayList<>();
        items.add("Me-1");
        items.add("Me-2");
        items.add("Me-3");
        items.add("Me-4");
        items.add("Me-5");
        items.add("Me-6");
        items.add("Me-7");*/
        ArrayList<MeModel> items = new ArrayList<>();
        items.add(new MeModel("Me", "10"));
        items.add(new MeModel("Me-1", "11"));
        items.add(new MeModel("Me-2", "12"));
        items.add(new MeModel("Me-3", "13"));
        items.add(new MeModel("Me-4", "14"));
        items.add(new MeModel("Me-5", "11"));
        items.add(new MeModel("Me-6", "12"));
        items.add(new MeModel("Me-7", "13"));
        items.add(new MeModel("Me-8", "14"));
        CustomAdpater customAdpater = new CustomAdpater(getContext(), R.layout.list_item, items);
        lv.setAdapter(customAdpater);
        //ArrayAdapter<String> _arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, items);
        //lv.setAdapter(_arrayAdapter);
    }

    public interface OnDataListSelectedListener {
        public void onDataListSelected(int position);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //System.out.println("*******************"+((TextView)v.findViewById(R.id.textView)).getText());
        mCallback.onDataListSelected(position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnDataListSelectedListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement OnDataListSelectedListener");
        }
    }
}
