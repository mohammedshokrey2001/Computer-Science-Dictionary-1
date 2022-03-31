package com.example.csdict.messageApp.RecycleViewMessages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csdict.DataModels.DataModelMessage;
import com.example.csdict.R;

import java.util.ArrayList;

public class ViewAdapterMessages extends RecyclerView.Adapter<ViewAdapterMessages.MyViewHolder> {

    private ArrayList<DataModelMessage> all_messages;
    private RecycleViewListener listener;
    public ViewAdapterMessages(ArrayList<DataModelMessage> allmessage, RecycleViewListener listener) {

        this.all_messages = allmessage;
        this.listener = listener;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_message,parent,false);
        return  new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String sender = all_messages.get(position).getSender();
        String title = all_messages.get(position).getTitle();
        //  String isActive = All_Users.get(position). ==true ? "Active User" :" Disabled User";

        String isActive = "Active message";

        holder.sender.setText(sender);
        holder.title.setText(title);
        holder.flag.setText(isActive);

    }



    @Override
    public int getItemCount() {
        return all_messages.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnLongClickListener {
        private TextView sender;
        private TextView title;
        private TextView flag;
        public MyViewHolder( View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.name_view_holder);
            title = itemView.findViewById(R.id.isActive_viewholder);
            flag = itemView.findViewById(R.id.phone_viewholder);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View view) {

            listener.onClick(view , getAdapterPosition());
        }


        @Override
        public boolean onLongClick(View view) {

            listener.onLongClick(view,getAdapterPosition());
            return  true;
         }


    }


   public  interface  RecycleViewListener{
        void onClick( View v,int postion);

       boolean onLongClick(View v, int postion);
   }


}







