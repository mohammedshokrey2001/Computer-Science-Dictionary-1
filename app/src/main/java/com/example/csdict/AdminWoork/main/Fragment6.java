package com.example.csdict.AdminWoork.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.csdict.DBHelper;
import com.example.csdict.DataModels.DataModelWord;
import com.example.csdict.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment6#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment6 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText name ;
    EditText app ;
    EditText desc;
    Button bt_add;
    public Fragment6() {
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
    public static Fragment6 newInstance(String param1, String param2) {
        Fragment6 fragment = new Fragment6();
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
        View view = inflater.inflate(R.layout.fragment_6, container, false);
        name = view.findViewById(R.id.editTextTextwordname);
        app = view.findViewById(R.id.editTextTextabbName);
        desc = view.findViewById(R.id.editTextTextMultiLine);
        bt_add = view.findViewById(R.id.button_addword);


        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                workOnData();
            }
        });

   return  view;
    }



    void workOnData(){

        DBHelper helper = new DBHelper(getContext());

        DataModelWord word = new DataModelWord(name.getText().toString(),desc.getText().toString(),"",app.getText().toString());
        boolean done = helper.addWord(word);

        if (done){
            Toast.makeText(getContext(), "done added word", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(), "can add word", Toast.LENGTH_SHORT).show();

        }
    }


}