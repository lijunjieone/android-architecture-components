package com.android.test;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        A a = new A();
        getLifecycle().addObserver(a);

        if (savedInstanceState == null) {
            openF3(a);
        }

    }

    private void openF1(A a) {
        MxFragment fragment = new MxFragment();
        fragment.getLifecycle().addObserver(a);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, MxFragment.TAG).commit();
    }

    private void openF2(A a) {
        MxFragment2 fragment = new MxFragment2();
        fragment.getLifecycle().addObserver(a);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, MxFragment.TAG).commit();
    }

    private void openF3(A a) {
        MxFragmentFromDb fragment = new MxFragmentFromDb();
        fragment.getLifecycle().addObserver(a);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, MxFragment.TAG).commit();
    }

    class A implements LifecycleObserver{


        private static final String LOGTAG = "A";

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void onPasuse() {
            Log.e(LOGTAG,"log,onpause");

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {
            Log.e(LOGTAG,"log,onResume");
        }

    }
}
