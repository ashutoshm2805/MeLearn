package me.lr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 12/25/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolder> {

    List<MeModel> meModels;
    Context context;

    public RVAdapter(List<MeModel> meModels, Context context) {
        this.meModels = meModels;
        this.context = context;
    }

    @Override
    public RVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
        return new RVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RVViewHolder holder, int position) {
        MeModel meModel = meModels.get(position);
        holder.tvHeading.setText(meModel.getName());
        holder.tvDetail.setText(meModel.getAge());
    }

    @Override
    public int getItemCount() {
        return meModels.size();
    }

    public class RVViewHolder extends RecyclerView.ViewHolder{
        public TextView tvHeading, tvDetail;
        public RVViewHolder(View itemView) {
            super(itemView);
            tvHeading = (TextView) itemView.findViewById(R.id.tvHeading);
            tvDetail = (TextView) itemView.findViewById(R.id.tvDetail);
        }
    }

}
