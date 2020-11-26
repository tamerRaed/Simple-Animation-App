package com.tamer.animation;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    ValueAnimator topAnimator, bottomAnimator, rightAnimator, leftAnimator,
            centerTopAnimator, centerBottomAnimator, centerRightAnimator, centerLeftAnimator;

    View topView, rightView, bottomView, leftView,
            centerTopView, centerBottomView, centerLeftView, centerRightView;

    boolean isRunning = false;
    boolean isFirstTouch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initial the views
        initViews();

        // initial all animators
        initAllAnimators();

        // build all animators
        buildAllAnimators();

        // parent layout
        ConstraintLayout layout = findViewById(R.id.main_container);
        // onClick
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Checking is the first time the user clicks
                if (isFirstTouch) {
                    // if Yes --> start all animations
                    startAllAnimations();
                    // Change to false
                    isFirstTouch = false;
                    // Check if the animation is running
                } else if (isRunning) {
                    // if Yes --> Pause all animations
                    pauseAllAnimation();
                } else {
                    // if No --> Resume all animations
                    resumeAllAnimations();
                }
                // Change the state
                isRunning = !isRunning;
            }
        });
    }

    private void buildAllAnimators() {
        buildAnimator(topAnimator, topView, true);
        buildAnimator(rightAnimator, rightView, false);
        buildAnimator(bottomAnimator, bottomView, true);
        buildAnimator(leftAnimator, leftView, false);

        buildAnimator(centerTopAnimator, centerTopView, false);
        buildAnimator(centerBottomAnimator, centerBottomView, false);
        buildAnimator(centerLeftAnimator, centerLeftView, true);
        buildAnimator(centerRightAnimator, centerRightView, true);
    }

    private void resumeAllAnimations() {
        topAnimator.resume();
        bottomAnimator.resume();
        rightAnimator.resume();
        leftAnimator.resume();
        centerTopAnimator.resume();
        centerBottomAnimator.resume();
        centerRightAnimator.resume();
        centerLeftAnimator.resume();
    }

    private void pauseAllAnimation() {
        topAnimator.pause();
        bottomAnimator.pause();
        rightAnimator.pause();
        leftAnimator.pause();
        centerTopAnimator.pause();
        centerBottomAnimator.pause();
        centerRightAnimator.pause();
        centerLeftAnimator.pause();
    }

    private void startAllAnimations() {
        topAnimator.start();
        bottomAnimator.start();
        rightAnimator.start();
        leftAnimator.start();
        centerTopAnimator.start();
        centerBottomAnimator.start();
        centerRightAnimator.start();
        centerLeftAnimator.start();
    }

    private void initViews() {
        topView = findViewById(R.id.top_center_blue);
        rightView = findViewById(R.id.right_center_view);
        bottomView = findViewById(R.id.center_bottom_view);
        leftView = findViewById(R.id.center_left_view);
        centerTopView = findViewById(R.id.center_top);
        centerBottomView = findViewById(R.id.center_bottom);
        centerLeftView = findViewById(R.id.center_left);
        centerRightView = findViewById(R.id.center_right);
    }

    public void buildAnimator(ValueAnimator animator, final View view, final boolean isHorizontal) {
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                if (isHorizontal) {
                    params.horizontalBias = (float) animation.getAnimatedValue();
                } else {
                    params.verticalBias = (float) animation.getAnimatedValue();
                }
                view.setLayoutParams(params);
            }
        });
    }

    public void initAllAnimators() {
        topAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.top_view_animation);
        bottomAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.bottom_view_animation);
        rightAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.right_view_animation);
        leftAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.left_view_animation);
        centerTopAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.center_top_view_animation);
        centerBottomAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.center_bottom_view_animation);
        centerRightAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.center_right_view_animation);
        centerLeftAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.center_left_view_animation);
    }

}
