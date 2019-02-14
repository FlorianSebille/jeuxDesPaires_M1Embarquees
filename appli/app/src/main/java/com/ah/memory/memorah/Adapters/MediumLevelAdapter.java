package com.ah.memory.memorah.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ah.memory.memorah.R;

import java.util.ArrayList;

public class MediumLevelAdapter extends RecyclerView.Adapter<MediumLevelAdapter.ViewHolder> {

    private ArrayList<Integer> cardFront;

    public MediumLevelAdapter(ArrayList<Integer> cardFront) {
        this.cardFront = cardFront;
    }

    @Override
    public MediumLevelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        view.setMinimumWidth(parent.getMeasuredWidth() / 3);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardFr.setImageResource(cardFront.get(position));
    }

    @Override
    public int getItemCount() {
        return cardFront.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView cardFr;

        public ViewHolder(View itemView) {
            super(itemView);
            cardFr = itemView.findViewById(R.id.cardfront);
        }
    }
}
