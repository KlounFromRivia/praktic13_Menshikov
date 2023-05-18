package com.example.praktic13_menshikov

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.praktic13_menshikov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var Riddle: RiddlesAnswer

    private var launcher: ActivityResultLauncher<Intent>? = null

    lateinit var answerTXT: String
    lateinit var zagadkaTXT: String

    var lstAnswer: MutableSet<String> = mutableSetOf()
    var lstRiddle: ArrayList<String> = arrayListOf()

    var count = 0
    var maxCount:Int = 10

    var trueCount = 0
    var falseCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Riddle = RiddlesAnswer()
        setContentView(binding.root)

        maxCount = binding.txtAnswerCountMax.text.toString().toInt()

        binding.btnZagadka.isEnabled = true
        binding.btnOtvet.isEnabled = false
        binding.btnStatic.isEnabled = false
        binding.btnExit.isVisible = false
        binding.btnRestore.isVisible = false
        staticBTN()
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                    result: ActivityResult ->
                if(result.resultCode == RESULT_OK){
                    var text = result.data?.getStringExtra("Result")
                    binding.txtAnswer.text = text
                    if(answerTXT == text){
                        binding.txtAnswer.setBackgroundColor(resources.getColor(R.color.green))
                        trueCount++
                    }else{
                        binding.txtAnswer.setBackgroundColor(resources.getColor(R.color.red))
                        falseCount++
                    }
                    if(count < maxCount){
                        btnRiddles(true)
                    }else{
                        СonditioTwo()
                    }
                }
                if(result.resultCode == RESULT_CANCELED){
                    СonditioTwo()
                }
            }
    }
    fun staticBTN(){
        if (binding.btnZagadka.isEnabled == true)
            binding.btnZagadka.setBackgroundColor(resources.getColor(R.color.btnColor))
        else
            binding.btnZagadka.setBackgroundColor(resources.getColor(R.color.gray))
        if (binding.btnOtvet.isEnabled == true)
            binding.btnOtvet.setBackgroundColor(resources.getColor(R.color.btnColor))
        else
            binding.btnOtvet.setBackgroundColor(resources.getColor(R.color.gray))
        if (binding.btnStatic.isEnabled == true)
            binding.btnStatic.setBackgroundColor(resources.getColor(R.color.btnColor))
        else
            binding.btnStatic.setBackgroundColor(resources.getColor(R.color.gray))
        if (binding.btnExit.isEnabled == true)
            binding.btnExit.setBackgroundColor(resources.getColor(R.color.btnColor))
        else
            binding.btnExit.setBackgroundColor(resources.getColor(R.color.gray))
        if (binding.btnRestore.isEnabled == true)
            binding.btnRestore.setBackgroundColor(resources.getColor(R.color.btnColor))
        else
            binding.btnRestore.setBackgroundColor(resources.getColor(R.color.gray))
    }
    fun btnZagadkaClick(view: View)
    {
        binding.txtAnswer.setBackgroundColor(resources.getColor(R.color.white))
        binding.txtAnswer.text = ""
        count = binding.txtAnswerCount.text.toString().toInt()

        var rnd = 0
        while(true){
            rnd = Riddle.RandomFunc()
            if(Riddle.zagadka[rnd] !in lstRiddle){
                break
            }
        }

        answerTXT = Riddle.answer[rnd]
        zagadkaTXT = Riddle.zagadka[rnd]

        lstRiddle.add(zagadkaTXT)
        lstAnswer.clear()

        lstAnswer.add(answerTXT)

        while(lstAnswer.size < 10){
            lstAnswer.add(Riddle.answer[Riddle.answer.indices.random()])
        }

        binding.txtZagadka.text = zagadkaTXT
        count++
        binding.txtAnswerCount.text = count.toString()
        btnRiddles(false)
    }

    fun btnRiddles(Truth:Boolean){
        binding.btnOtvet.isEnabled = !Truth
        binding.btnZagadka.isEnabled = Truth

        staticBTN()
    }

    fun btnOtvetClick(view: View)
    {
        var list = lstAnswer.shuffled()
        var answerList:ArrayList<String> = arrayListOf();
        answerList.addAll(list)
        var intent = Intent(this,AnswerActivity::class.java)
        intent.putStringArrayListExtra("lstAnswer", answerList)
        launcher?.launch(intent)
    }
    fun btnRestore(view: View){
        finish();
        startActivity(getIntent());
    }

    fun btnExit(view: View){
        finishAndRemoveTask()
    }
    fun btnStaticClick(view: View)
    {
        var intent = Intent(this,StaticActivity::class.java)
        intent.putExtra("CountTrue", trueCount)
        intent.putExtra("CountFalse", falseCount)
        startActivity(intent)
    }

    fun СonditioTwo(){
        binding.btnZagadka.isEnabled =false
        binding.btnOtvet.isEnabled = false
        binding.btnStatic.isEnabled = true

        binding.btnExit.isVisible = true
        binding.btnRestore.isVisible = true
        staticBTN()
    }
}