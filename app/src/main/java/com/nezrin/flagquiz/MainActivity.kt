package com.nezrin.flagquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import com.nezrin.flagquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        copyVeriTabani()

        binding.buttonStart.setOnClickListener {
            startActivity(Intent(this,QuizActivity::class.java))
        }
    }
    fun copyVeriTabani(){
        val copyHelper=DatabaseCopyHelper(this)
        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()

        }catch (e:java.lang.Exception){
            e.printStackTrace()
        }
    }
}