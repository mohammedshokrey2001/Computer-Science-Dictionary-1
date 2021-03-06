package com.example.csdict.AdminWoork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.csdict.AdminWoork.main.SectionsPagerAdapter2;
import com.example.csdict.databinding.ActivityAdminWorkBinding;
import com.example.csdict.messageApp.Messages;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class AdminWork extends AppCompatActivity {

    private ActivityAdminWorkBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminWorkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter2 sectionsPagerAdapter = new SectionsPagerAdapter2(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab2;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Welcom to Messages App", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent1 = new Intent(getApplicationContext(), Messages.class);
                intent1.putExtra("user_mail","admin");
                startActivity(intent1);
            }
        });
    }


    }