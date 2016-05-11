package com.example.fanzf.repo_volleyhelper.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fanzf.repo_volleyhelper.R;
import com.example.fanzf.repo_volleyhelper.helper.VolleyHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResponseFragment fragment = (ResponseFragment)getSupportFragmentManager().findFragmentById(R.id.responseContent);
        if (fragment == null){
            fragment = new ResponseFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.responseContent, fragment).commit();
        }
        new RequestPresenter(fragment);
    }

    @Override
    protected void onStop() {
        super.onStop();
        VolleyHelper.cancelRequest();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
