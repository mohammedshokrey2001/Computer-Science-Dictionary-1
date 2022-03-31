package com.example.csdict.UserFragments.ui;

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

import com.example.csdict.main.DBHelper;
import com.example.csdict.DataModels.DataModelWord;
import com.example.csdict.R;
import com.example.csdict.RecycleViewAdminWord.ViewAdapterAd;
import com.example.csdict.main.WordProfileAdminView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    ViewAdapterAd.RecycleViewListener listener;
    ArrayList<DataModelWord> words;
    ArrayList<String> data_bundle;

    public Fragment2() {
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
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
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
        View view =  inflater.inflate(R.layout.fragment_2, container, false);
         recyclerView = view.findViewById(R.id.view_fav_words_f2);
        data_bundle = getArguments().getStringArrayList("data");

        adapter();
        return view;
    }



    void adapter(){
        setOnClickListener();

        DBHelper db = new DBHelper(getContext());
        words =  db.getWords("user" ,data_bundle.get(4));
        //Toast.makeText(getContext(), words.toString(), Toast.LENGTH_SHORT).show();
        ViewAdapterAd adapter = new ViewAdapterAd(words,listener);

        LinearLayoutManager layoutManager =  new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration  = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);




    }

    @Override
    public void onResume() {
        super.onResume();
        this.onCreate(null);
    }

    private void setOnClickListener() {
        listener = new ViewAdapterAd.RecycleViewListener() {
            @Override
            public void onClick(View v, int postion) {
                Intent intent = new Intent(getContext(), WordProfileAdminView.class);
                String idd = String.valueOf(words.get(postion).getID());

                intent.putExtra("name", words.get(postion).getName());
                intent.putExtra("desc", words.get(postion).getDescription());
                intent.putExtra("app", words.get(postion).getApservation());
                intent.putExtra("id", idd);
                intent.putExtra("id_user",String.valueOf(data_bundle.get(3)));
                intent.putExtra("user_or_admin", "user");





                Toast.makeText(getContext(), "id 111 = "+words.get(postion).getID(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        };
    }


}