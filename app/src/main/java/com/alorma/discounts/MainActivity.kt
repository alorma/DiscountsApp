package com.alorma.discounts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alorma.discounts.ui.camera.CameraFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CameraFragment.newInstance())
                .commitNow()
        }
    }

}
