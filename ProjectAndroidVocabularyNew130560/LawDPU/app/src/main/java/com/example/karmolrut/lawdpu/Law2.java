package com.example.karmolrut.lawdpu;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;


public class Law2  extends AppCompatActivity {
    private Switch T;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ImageButton Favoriteshow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.law2);


        // toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        T = (Switch) findViewById(R.id.switch1);
        T.setChecked(true);
        T.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if(T.isChecked() == false){
                    Intent intent1 = new Intent(getApplicationContext() , Law.class);
                    startActivity(intent1);
                    finish();
                }
            }
        });

        Favoriteshow = (ImageButton) findViewById(R.id.favoriteshow);
        Favoriteshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext() , Favorite.class);
                startActivity(intent1);
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new tab_two2(), "Two");
        viewPager.setAdapter(adapter);

    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Law2.this);

        alertDialog.setTitle("ออกจากโปรแกรม...");
        alertDialog.setMessage("คุณต้องการออกจากโปรแกรมใช่หรือไม่ ?");
        //alertDialog.setIcon(R.drawable.bgblood);

        alertDialog.setPositiveButton("ใช่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //คลิกใช่ ออกจากโปรแกรม
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);

                    }
                });

        alertDialog.setNegativeButton("ไม่ใช่",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //คลิกไม่ cancel dialog
                        dialog.cancel();
                    }
                });

        alertDialog.show();

    }
}

