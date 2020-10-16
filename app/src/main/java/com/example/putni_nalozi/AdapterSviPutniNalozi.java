package com.example.putni_nalozi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.putni_nalozi.models.PutniNalog;

import java.util.List;

public class AdapterSviPutniNalozi extends RecyclerView.Adapter<AdapterSviPutniNalozi.AdapterSviPutniNaloziViewHolder> {

    private List<PutniNalog> putniNalogList;
    private AdapterSviPutniNalozi.OnItemClickLisener lisener;

    public interface OnItemClickLisener{
        void onItemClick(int position);
    }

    public void setOnClickLisener(AdapterSviPutniNalozi.OnItemClickLisener lisener){

        this.lisener=lisener;
    }

        public static class AdapterSviPutniNaloziViewHolder extends RecyclerView.ViewHolder{

            public TextView danPutnogNaloga;
            public TextView imePrezime;

            public AdapterSviPutniNaloziViewHolder(@NonNull View itemView,OnItemClickLisener lisener) {
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

    public AdapterSviPutniNalozi(List<PutniNalog> putniNalogList){

        this.putniNalogList=putniNalogList;

    }

    @NonNull
    @Override
    public AdapterSviPutniNaloziViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.putni_nalozi_items_recycle_view_svi_putni_nalozi,parent,false);
        AdapterSviPutniNalozi.AdapterSviPutniNaloziViewHolder viewHolder=new AdapterSviPutniNalozi.AdapterSviPutniNaloziViewHolder(v,lisener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSviPutniNaloziViewHolder holder, int position) {

        PutniNalog trenutniNalog=putniNalogList.get(position);

        holder.danPutnogNaloga.setText(String.format("Putni nalog %s",trenutniNalog.getDana()));
        holder.imePrezime.setText(String.format("%s %s",trenutniNalog.getUser().getIme(),trenutniNalog.getUser().getPrezime()));

    }

    @Override
    public int getItemCount() {
        return putniNalogList.size();
    }
}
