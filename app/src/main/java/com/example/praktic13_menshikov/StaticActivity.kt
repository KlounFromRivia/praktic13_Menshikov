package com.example.praktic13_menshikov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.praktic13_menshikov.databinding.ActivityStaticBinding

class StaticActivity : AppCompatActivity() {
    lateinit var binding: ActivityStaticBinding
    lateinit var intentMain: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaticBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var CountTrue: Int =  intent.getIntExtra("CountTrue", 0)
        var CountFalse: Int = intent.getIntExtra("CountFalse", 0)

        binding.txtAllCount.text = (CountTrue + CountFalse).toString()
        binding.txtTrue.text = CountTrue.toString()
        binding.txtFalse.text = CountFalse.toString()

        intentMain = Intent(this, MainActivity::class.java)
    }

    fun btnMain(view: View){
        setResult(RESULT_CANCELED,intentMain)
        finish()
    }
}