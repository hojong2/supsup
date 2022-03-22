package com.example.supsup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter3 extends RecyclerView.Adapter<adapter3.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileimage;
        TextView profiletext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileimage = (ImageView) itemView.findViewById(R.id.profileimage);
            profiletext = (TextView) itemView.findViewById(R.id.profiletext);

        }
    }

    private ArrayList<data3> profilelist = null;

    public adapter3(ArrayList<data3> profilelist){
        this.profilelist = profilelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mypage_profile_resource, parent, false);
        adapter3.ViewHolder vh = new adapter3.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter3.ViewHolder holder, int position) {
        data3 item = profilelist.get(position);

        holder.profileimage.setImageResource(item.getProfileimage());
        holder.profiletext.setText(item.getProfiletext());
    }

    @Override
    public int getItemCount() {
        return profilelist.size();
    }
}
