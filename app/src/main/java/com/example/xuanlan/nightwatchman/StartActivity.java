package com.example.xuanlan.nightwatchman;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Fade fade = new Fade();
                fade.setDuration(100);
                getWindow().setExitTransition(fade);
                getWindow().setEnterTransition(fade);
                Intent intent = new Intent(StartActivity.this, mainActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(StartActivity.this).toBundle());
                StartActivity.this.finish();
            }
        }, 2000);

    }
}
