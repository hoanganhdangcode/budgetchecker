package com.hoanganhdangcode.budgetchecker.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hoanganhdangcode.budgetchecker.R
import com.hoanganhdangcode.budgetchecker.presentation.ui.auth.LoginActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var motionRoot: MotionLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        motionRoot = findViewById<MotionLayout>(R.id.main)
        var formLogin = findViewById<ConstraintLayout>(R.id.form_login)

        motionRoot.post {
            motionRoot.transitionToEnd {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }

                startActivity(intent)
                finish()

             }
        }

    }
}