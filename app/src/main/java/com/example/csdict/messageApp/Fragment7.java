package com.example.csdict.messageApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csdict.DataModels.DataModelMessage;
import com.example.csdict.R;
import com.example.csdict.main.DBHelper;
import com.example.csdict.messageApp.RecycleViewMessages.ViewAdapterMessages;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment7#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment7 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    RecyclerView recyclerView ;
    ArrayList<DataModelMessage> all_messages ;
    private ViewAdapterMessages.RecycleViewListener listener;
    private String data_mails ;

    public Fragment7() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment7 newInstance(String param1, String param2) {
        Fragment7 fragment = new Fragment7();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment7, container, false);
        recyclerView = view.findViewById(R.id.messages_in);
        data_mails = getArguments().getString("user_mail");

         adapter();
        return  view;
    }





    void adapter(){
        setOnClickListener();

        DBHelper db = new DBHelper(getContext());
        all_messages =  db.schMessages(data_mails,"MESSAGES_RECIVER");
            if (all_messages==null){
                Toast.makeText(getContext(), "s", Toast.LENGTH_SHORT).show();
            }
            else {
                ViewAdapterMessages adapter = new ViewAdapterMessages(all_messages, listener);

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(dividerItemDecoration);

                recyclerView.setAdapter(adapter);


            }

    }


    private void setOnClickListener() {
        listener = new ViewAdapterMessages.RecycleViewListener() {

            @Override
            public void onClick(View v, int postion) {
                String idd = String.valueOf(all_messages.get(postion).getId());

                Intent intent = new Intent(getContext(), MessageProfile.class);
                String sender = all_messages.get(postion).getSender();
                String title = all_messages.get(postion).getTitle();
                String message_content = all_messages.get(postion).getMessage();

                intent.putExtra("title",title);
                intent.putExtra("sender",sender) ;
                intent.putExtra("message",message_content);

               //  Toast.makeText(getContext(), "id 111 = "+all_users.get(postion).getId(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

            @Override
            public boolean onLongClick(View v, int postion) {
                                deleteMessage(all_messages.get(postion).getId());
                                return true;
            }


        };



    }



    void deleteMessage(String id){
         DBHelper dbHelper = new DBHelper(getContext());
         dbHelper.deleteMessage(id);
    }


}