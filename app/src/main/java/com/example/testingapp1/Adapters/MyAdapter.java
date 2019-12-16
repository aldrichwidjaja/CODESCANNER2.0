package com.example.testingapp1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testingapp1.Model.Listitem;
import com.example.testingapp1.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterViewHolder>
{

    List<Listitem> listItemsArrayList;
    Context context;

    public MyAdapter(List<Listitem>listItemsArrayList, Context context)
    {
        this.listItemsArrayList = listItemsArrayList;
        this.context = context;

    }

    @Override
    public MyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new MyAdapterViewHolder(v);

    }

    @Override
    public void onBindViewHolder(MyAdapterViewHolder holder, int position) {
        Listitem listitem = listItemsArrayList.get(position);

        holder.textViewType.setText(listitem.getType());
        holder.textViewCode.setText(listitem.getCode());
        Linkify.addLinks(holder.textViewCode, Linkify.ALL);
    }

    @Override
    public int getItemCount() {
        return listItemsArrayList.size();
    }



    public class MyAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewCode, textViewType;
        CardView cardView;

        public MyAdapterViewHolder(final View itemView) {
            super(itemView);
            textViewCode = (TextView) itemView.findViewById(R.id.textViewCode);
            textViewType = (TextView) itemView.findViewById(R.id.textViewType);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String type = listItemsArrayList.get(getAdapterPosition()).getType();

                    if(type.contains("http://") | type.contains("https://"))
                    {
                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse(type));
                        itemView.getContext().startActivity(viewIntent);
                    }

                    else
                    {
                        type ="http://www.google.com/#q="+type;
                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse(type));
                        itemView.getContext().startActivity(viewIntent);

                    }


                }
            });
        }



    }




}
