package com.example.csdict.AdminWoork.main;

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

import com.example.csdict.AdminWoork.UserProfileAdminView;
import com.example.csdict.DBHelper;
import com.example.csdict.DataModels.DataModelUser;
import com.example.csdict.R;
import com.example.csdict.RecycleViewUsers.ViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    RecyclerView recyclerView ;
    ArrayList<DataModelUser> all_users ;
    private ViewAdapter.RecycleViewListener listener;


    public Fragment4() {
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
    public static Fragment4 newInstance(String param1, String param2) {
        Fragment4 fragment = new Fragment4();
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
        View view =  inflater.inflate(R.layout.fragment_4, container, false);
        recyclerView = view.findViewById(R.id.users_view);
        adapter();
        return  view;
    }





    void adapter(){
        setOnClickListener();
        DBHelper db = new DBHelper(getContext());
        all_users =  db.getAllUsers();
        ViewAdapter adapter = new ViewAdapter( all_users,listener);

        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration  = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);




    }


    private void setOnClickListener() {
        listener = new ViewAdapter.RecycleViewListener() {
            @Override
            public void onClick(View v, int postion) {
                Intent intent = new Intent(getContext(), UserProfileAdminView.class);
                String idd = String.valueOf(all_users.get(postion).getId());

                intent.putExtra("name", all_users.get(postion).getName());
                intent.putExtra("pass", all_users.get(postion).getPass());
                intent.putExtra("mail", all_users.get(postion).getMail());
                intent.putExtra("phone", all_users.get(postion).getPhone());
                intent.putExtra("id", idd);
                intent.putExtra("pass_a", all_users.get(postion).getReset_answer());
                intent.putExtra("pass_q", all_users.get(postion).getReset_qu());





                 Toast.makeText(getContext(), "id 111 = "+all_users.get(postion).getId(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        };
    }


}