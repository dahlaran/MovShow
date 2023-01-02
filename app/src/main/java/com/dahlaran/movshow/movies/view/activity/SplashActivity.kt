package com.dahlaran.movshow.movies.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import com.dahlaran.movshow.R
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*
import timber.log.Timber

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        hideNavigationSupportBar()
        initMotionLayout()
    }

    private fun initMotionLayout() {
        motionLayoutSplash.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(motionLayout: MotionLayout, triggerId: Int, positive: Boolean, progress: Float) {
                Timber.d("onTransitionTrigger triggerId = $triggerId, positive = $positive, progress = $progress")
            }

            override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) {
                Timber.d("onTransitionStarted startId = $startId, endId= $endId")
            }

            override fun onTransitionChange(motionLayout: MotionLayout, startId: Int, endId: Int, progress: Float) {
                Timber.d("onTransitionChange startId = $startId, endId= $endId, progress= $progress")
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                Timber.d("onTransitionCompleted currentId = $currentId")
                if (currentId == R.id.endPlay) {
                    goToMediaList()
                }
            }
        })
        GlobalScope.launch(Dispatchers.IO) {
            delay(100)
            withContext(Dispatchers.Main) {
                motionLayoutSplash.setTransition(R.id.transitionPlay)
                motionLayoutSplash.transitionToEnd()
            }
        }
        playImage.setOnClickListener {
            goToMediaList()
        }
    }

    override fun onDestroy() {
        showNavigationSupportBar()
        super.onDestroy()
    }

    fun goToMediaList() {
        Timber.i("launch MainActivity")
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}