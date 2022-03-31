package com.example.csdict.messageApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csdict.DataModels.DataModelMessage;
import com.example.csdict.R;
import com.example.csdict.main.DBHelper;
import com.example.csdict.messageApp.RecycleViewMessages.ViewAdapterMessages;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment9 extends Fragment {

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

    public Fragment9() {
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
    public static Fragment9 newInstance(String param1, String param2) {
        Fragment9 fragment = new Fragment9();
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
        View view =  inflater.inflate(R.layout.fragment9, container, false);
        recyclerView = view.findViewById(R.id.messages_inbox);
        data_mails = getArguments().getString("user_mail");
        Toast.makeText(getContext(), data_mails, Toast.LENGTH_SHORT).show();
        EditText mail_reciver =view.findViewById (R.id.editTextTextMailReciver);
        EditText mail_title =view.findViewById (R.id.message_title);
        EditText message =view.findViewById (R.id.editTextTextMultiLine_Message);
        Button send = view.findViewById(R.id.send_message);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getContext());

                DataModelMessage msg= new DataModelMessage(message.getText().toString(),
                        "",data_mails,mail_reciver.getText().toString(),
                        mail_title.getText().toString()) ;

                if (dbHelper.addMessage(msg)){
                    Toast.makeText(getContext(), "send done", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return  view;



    }






}
