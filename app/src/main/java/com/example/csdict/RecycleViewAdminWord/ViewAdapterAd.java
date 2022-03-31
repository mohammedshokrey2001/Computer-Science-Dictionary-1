package com.example.csdict.RecycleViewAdminWord;

import   android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csdict.DataModels.DataModelWord;
import com.example.csdict.R;

import java.util.ArrayList;

public class ViewAdapterAd extends RecyclerView.Adapter<ViewAdapterAd.MyViewHolder> {

    private ArrayList<DataModelWord> All_Words;
    private RecycleViewListener listener;
    public ViewAdapterAd(ArrayList<DataModelWord> all_Users, RecycleViewListener listener) {
        All_Words = all_Users;
        this.listener = listener;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.words_layout_admin,parent,false);
        return  new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = All_Words.get(position).getName();
      //  String isActive = All_Words.get(position). ==true ? "Active User" :" Disabled User";


        String app = All_Words.get(position).getApservation();
         holder.name.setText(name);
         holder.app.setText(app);


    }



    @Override
    public int getItemCount() {
        return All_Words.size();
    }


    protected class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView app;

        public MyViewHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_word_view_holder);
            app = itemView.findViewById(R.id.word_apps_viewholder);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            listener.onClick(view , getAdapterPosition());
        }


    }


   public  interface  RecycleViewListener{
        void onClick( View v,int postion);
   }

}







