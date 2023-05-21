package com.example.writenow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.MenuItem;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.writenow.Home.HomeFragment;
import com.example.writenow.Set.SetFragment;
import com.example.writenow.Star.StarFragment;
import com.example.writenow.Write.WriteFragment;
import com.example.writenow.Write.WriteStudentFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    HomeFragment homeFragment;
    WriteFragment writeFragment;
    StarFragment starFragment;
    SetFragment setFragment;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isNetworkAvailable()) {
            Toast.makeText(this, "인터넷에 연결되었습니다.", Toast.LENGTH_SHORT).show();
            // 인터넷을 사용하는 기능을 실행할 수 있는 코드 작성
        } else {
            Toast.makeText(this, "인터넷에 연결되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
            // 인터넷이 연결되지 않은 상태에서 처리할 코드 작성
        }

        init();

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeFragment()).commit();
                    return true;
                }
                else if (id == R.id.nav_write){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new WriteFragment()).commit();
                    return true;
                }
                else if (id == R.id.nav_star){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new StarFragment()).commit();
                    return true;
                }
                else if (id == R.id.nav_set){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new SetFragment()).commit();
                    return true;
                }
                return true;
            }

        });


    }
    private void init(){
        //fragment 객체 생성
        homeFragment = new HomeFragment();
        writeFragment = new WriteFragment();
        starFragment = new StarFragment();
        setFragment = new SetFragment();

        //main.xml상의 bottomNavigationView 연결
        bottomNavigationView = findViewById(R.id.bottomNavi);

        //제일 처음 띄어줄 뷰 세팅
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,homeFragment).commitAllowingStateLoss();

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}