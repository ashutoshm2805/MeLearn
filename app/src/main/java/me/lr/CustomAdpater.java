package me.lr;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 11/22/2017.
 */

public class CustomAdpater extends ArrayAdapter<MeModel> {

    public CustomAdpater(@NonNull Context context, int resource, @NonNull ArrayList<MeModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MeModel meModel = getItem(position);
        LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.textView);
        TextView tv2 = (TextView) convertView.findViewById(R.id.textView2);
        tv.setText(meModel.getName());
        tv2.setText(meModel.getAge());
        return convertView;
    }
}
