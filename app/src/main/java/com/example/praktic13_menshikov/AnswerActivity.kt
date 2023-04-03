package com.example.praktic13_menshikov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.praktic13_menshikov.databinding.ActivityAnswerBinding

class AnswerActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnswerBinding
    val listRadioName:ArrayList<String> = arrayListOf<String>("radioOne", "radioTwo","radioThree","radioFour","radioFive",
        "radioSix", "radioSeven","radioEight","radioNine","radioTen")
    var ListAnswer: ArrayList<String>? = null
    lateinit var intentResult: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnProv.isEnabled = false
        ListAnswer = intent.getStringArrayListExtra("lstAnswer")
        intentResult = Intent(this, MainActivity::class.java)
        val rg = RadioGroup(this)
        rg.orientation = RadioGroup.VERTICAL
        for(i in ListAnswer!!.indices){
            var rb = RadioButton(this)
            rb.id = View.generateViewId()
            rb.text = ListAnswer?.get(i)
            rb.textSize = 28f
            rb.setOnClickListener{
                intentResult.putExtra("Result", rb.text);
                binding.btnProv.isEnabled = true
            }
            rg.addView(rb)
        }
        binding.linearLayoutRBTN.addView(rg)
    }
    fun btnValidate(view: View){
        setResult(RESULT_OK,intentResult)
        finish()
    }
}