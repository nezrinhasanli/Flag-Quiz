package com.nezrin.flagquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nezrin.flagquiz.databinding.ActivityMainBinding
import com.nezrin.flagquiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRepeat.setOnClickListener {
            startActivity(Intent(this,QuizActivity::class.java))
            finish()
        }
    }
}