package com.betul.bas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OneriAdapter extends RecyclerView.Adapter<OneriAdapter.Holder>
  {

      Context context;
      ArrayList<OneriList> oneriList;
      DeleteUpdate deleteUpdate;
      public interface DeleteUpdate{
          void delUpdate(int id, String turu, String ismi, String yayinYili, String sayfaSayisi, String imdbPuani
                  , String not);
      }

    Detay detay;
    public interface Detay{
        void onItemClick(int position);
    }

    public OneriAdapter(Context context, ArrayList<OneriList> oneriList, Detay detay, DeleteUpdate deleteUpdate) {
        this.context = context;
        this.oneriList = oneriList;
        this.detay = detay;
        this.deleteUpdate = deleteUpdate;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        Holder Holder = new Holder(view,detay,deleteUpdate);
        return Holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder Holder, int position) {
        OneriList oneriListt = oneriList.get(position);
        Holder.ismi.setText(oneriListt.getIsmi());
        Holder.yayinYili.setText(oneriListt.getYayinYili());
    }
    @Override
    public int getItemCount() {
        return oneriList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView ismi;
        TextView yayinYili;
        Detay detay;
        DeleteUpdate deleteUpdate;

        @SuppressLint("ClickableViewAccessibility")
        public Holder(@NonNull View itemView, final Detay detay, final DeleteUpdate deleteUpdate) {

            super(itemView);

            ismi = itemView.findViewById(R.id.ismi);
            yayinYili = itemView.findViewById(R.id.yayinyili);
            this.detay = detay;
            this.deleteUpdate = deleteUpdate;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    detay.onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    deleteUpdate.delUpdate(oneriList.get(getAdapterPosition()).getId(),
                            oneriList.get(getAdapterPosition()).getTuru(),
                            oneriList.get(getAdapterPosition()).getIsmi(),
                            oneriList.get(getAdapterPosition()).getYayinYili(),
                            oneriList.get(getAdapterPosition()).getSayfaSayisi(),
                            oneriList.get(getAdapterPosition()).getImdbPuani(),
                            oneriList.get(getAdapterPosition()).getNot());
                    return false;
                }
            });
        }
    }
}

