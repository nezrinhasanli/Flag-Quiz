package com.nezrin.flagquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.nezrin.flagquiz.databinding.ActivityMainBinding
import com.nezrin.flagquiz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding:ActivityQuizBinding
    private lateinit var questions:ArrayList<Flags>
    private lateinit var wrongOptions:ArrayList<Flags>
    private lateinit var rightQuestion:Flags
    private lateinit var allOptions:HashSet<Flags>
    private lateinit var vt:VeriTabaniYardimcisi

    private var questionCounter=0
    private var rightCounter=0
    private var wrongCounter=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vt=VeriTabaniYardimcisi(this) //veritabaninda is gore bilerik
        questions=Flagsdao().random5Flag(vt) //5 eded random suali question array listine aktarir

        questionDownload()

        binding.buttonA.setOnClickListener {
            rightControl(binding.buttonA)
            questionCounterControl()
        }
        binding.buttonB.setOnClickListener {
            rightControl(binding.buttonB)
            questionCounterControl()
        }
        binding.buttonC.setOnClickListener {
            rightControl(binding.buttonC)
            questionCounterControl()
        }
        binding.buttonD.setOnClickListener {
            rightControl(binding.buttonD)
            questionCounterControl()
        }
    }
    fun questionDownload(){ //suali yuklemek
        binding.tvQuestionCount.text="${questionCounter+1}. Question"

        rightQuestion=questions.get(questionCounter) //dogru suali secir

        binding.imageViewFlag.setImageResource(resources.getIdentifier(
            rightQuestion.flagImage,"drawable",packageName)) //sekli dinamik olaraq yukleyir

        wrongOptions=Flagsdao().random3WrongFlag(vt,rightQuestion.flagId) //sehv cavablari getirir

        allOptions= HashSet() //3 sehv optionlar,hashset cavablarin yerini qarisdirir
        allOptions.add(rightQuestion)
        allOptions.add(wrongOptions.get(0))
        allOptions.add(wrongOptions.get(1))
        allOptions.add(wrongOptions.get(2))

        binding.buttonA.text=allOptions.elementAt(0).flagName  //ilk veriyi button A'ya otururuk
        binding.buttonB.text=allOptions.elementAt(1).flagName
        binding.buttonC.text=allOptions.elementAt(2).flagName
        binding.buttonD.text=allOptions.elementAt(3).flagName

    }
    fun questionCounterControl(){ //eger sual sayacini artirsaq butun suallari sira ile goreceyik mes.1 sual,2 sual

        questionCounter++ //sual sayini artirir

        if (questionCounter!=5){ 5 //5 suala kimi sual yukle
            questionDownload()

        }
        else{ //5 sualdan sonra bitir
            startActivity(Intent(this,ResultActivity::class.java))
            finish()
        }
    }
    fun rightControl(button: Button){ //dogru cavabi yoxlamaq
        val buttonYazi=button.text.toString() //buttonun uzerindeki yazini alir,hansi buttona click etmisikse
        //button nesnesini bura oturur,button uzerindeki yazini elde edirik

        val rightAnswer=rightQuestion.flagName //bayraq adini elde edirik

        if (buttonYazi==rightAnswer){ //dogru ve yanlis cavablari muqayise edir
            rightCounter++
        }else{

            wrongCounter++
        }
        //ekranda yazdirmaq
        binding.tvRight.text="Right: $rightCounter"
        binding.tvWrong.text="Wrong: $wrongCounter"

    }
}