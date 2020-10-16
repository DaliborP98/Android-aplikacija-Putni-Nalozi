package com.example.putni_nalozi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.putni_nalozi.models.PutniNalog;

import java.util.List;

public class AdapterNedefinirano extends RecyclerView.Adapter<AdapterNedefinirano.AdapterNedefiniranoViewHolder> {

    private List<PutniNalog> putniNalogList;
    private AdapterNedefinirano.OnItemClickLisener lisener;

    public interface OnItemClickLisener{
        void onItemClick(int position);
    }

    public void setOnClickLisener(AdapterNedefinirano.OnItemClickLisener lisener){

        this.lisener=lisener;
    }

    public static class AdapterNedefiniranoViewHolder extends RecyclerView.ViewHolder {

        public TextView danPutnogNaloga;
        public TextView imePrezime;
        public AdapterNedefiniranoViewHolder(@NonNull View itemView, OnItemClickLisener lisener) {
            super(itemView);
            danPutnogNaloga=itemView.findViewById(R.id.danPutnogNaloga);
            imePrezime=itemView.findViewById(R.id.imePrezimeSvi);
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

    public AdapterNedefinirano(List<PutniNalog> putniNalogList){

        this.putniNalogList=putniNalogList;

    }

    @NonNull
    @Override
    public AdapterNedefiniranoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.putni_nalozi_items_recycle_view_nedefinirano,parent,false);
        AdapterNedefinirano.AdapterNedefiniranoViewHolder viewHolder=new AdapterNedefinirano.AdapterNedefiniranoViewHolder(v,lisener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNedefiniranoViewHolder holder, int position) {

        PutniNalog trenutniNalog=putniNalogList.get(position);

        holder.danPutnogNaloga.setText(String.format("Putni nalog %s",trenutniNalog.getDana()));
        holder.imePrezime.setText(String.format("%s %s",trenutniNalog.getUser().getIme(),trenutniNalog.getUser().getPrezime()));
    }

    @Override
    public int getItemCount() {
        return putniNalogList.size();
    }
}
