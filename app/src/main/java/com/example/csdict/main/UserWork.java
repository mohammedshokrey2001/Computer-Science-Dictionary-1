package com.example.csdict.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.csdict.UserFragments.ui.main.SectionsPagerAdapter;
import com.example.csdict.databinding.ActivityUserWorkBinding;
import com.example.csdict.messageApp.Messages;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class UserWork extends AppCompatActivity {

    private ActivityUserWorkBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = new Bundle();
        Intent intent = getIntent();


        ArrayList<String> arrayList =intent.getStringArrayListExtra("data");
         bundle.putStringArrayList("data",arrayList);




        binding = ActivityUserWorkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),bundle);
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Welcom to Messages App", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent1 = new Intent(getApplicationContext(), Messages.class);
                intent1.putExtra("user_mail",arrayList.get(1));
                Log.i("mailTracing", "onClick: in user  work  "+arrayList.get(1));
                startActivity(intent1);

            }
        });
    }
}