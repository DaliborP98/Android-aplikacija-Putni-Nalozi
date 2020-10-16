package com.example.putni_nalozi;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.putni_nalozi.models.PutniNalog;

import java.util.List;

public class AdapterMojiPutniNalozi extends RecyclerView.Adapter<AdapterMojiPutniNalozi.ViewHolder> {

    private List<PutniNalog> putniNalogList;
    private OnItemClickLisener lisener;

    public interface OnItemClickLisener{
        void onItemClick(int position);
    }

    public void setOnClickLisener(OnItemClickLisener lisener){

        this.lisener=lisener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView danPutnogNaloga;

        public ViewHolder(@NonNull View itemView,OnItemClickLisener lisener) {
            super(itemView);
            danPutnogNaloga=itemView.findViewById(R.id.danPutnogNaloga);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lisener!=null){
                        int position=getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            lisener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }

    public AdapterMojiPutniNalozi(List<PutniNalog> putniNalogList){

        this.putniNalogList=putniNalogList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.putni_nalozi_items_recycle_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(v,lisener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PutniNalog trenutniNalog=putniNalogList.get(position);

        holder.danPutnogNaloga.setText(trenutniNalog.getDana()); //tu promjenis

    }

    @Override
    public int getItemCount() {
        return putniNalogList.size();
    }


}
