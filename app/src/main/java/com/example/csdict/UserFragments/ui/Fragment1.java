package com.example.csdict.UserFragments.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.csdict.DataModels.DataModelUser;
import com.example.csdict.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class Fragment1 extends Fragment {

    TextView fname ;
    TextView lname ;
    TextView mail ;
    TextView phone ;
     ArrayList<String>arr;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  DataModelUser modelUser ;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment1() {
        // Required empty public constructor


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

       View view = inflater.inflate(R.layout.fragment_1, container, false);

       arr = getArguments().getStringArrayList("data");
       fname = view.findViewById(R.id.et_first_name_admin_view);
       lname = view.findViewById(R.id.et_last_name_admin_view);
       mail = view.findViewById(R.id.et_total_balancee);
       phone = view.findViewById(R.id.et_number_loans);


       fname.setText(arr.get(0).split(" ")[0]);
       lname.setText(arr.get(0).split(" ")[1]);
        mail.setText(arr.get(1));
        phone.setText(arr.get(2));





        return view;


    }




}