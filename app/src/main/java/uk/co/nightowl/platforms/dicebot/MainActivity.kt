package uk.co.nightowl.platforms.dicebot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val btt = AnimationUtils.loadAnimation(this, R.anim.btt)
        val rotateOut = AnimationUtils.loadAnimation(this, R.anim.rotate)
        val rotateIn = AnimationUtils.loadAnimation(this, R.anim.rotate_in)

        val headerTitle = findViewById<LinearLayout>(R.id.ll_dice_size_selector)
        val rollBtn = findViewById<TextView>(R.id.tv_roll)
        val virtualDiceOld = findViewById<TextView>(R.id.tv_virtual_dice_old)
        val virtualDiceNew = findViewById<TextView>(R.id.tv_virtual_dice_new)
        val headerInstructions = findViewById<TextView>(R.id.tv_dice_select_instructions)

        headerTitle.startAnimation(ttb)
        rollBtn.startAnimation(btt)
        headerInstructions.startAnimation(ttb)

        virtualDiceNew.setOnClickListener {
            virtualDiceOld.startAnimation(rotateOut)
            val num = generateNumber(
                et_dice_size.text.toString().toInt())
                .toString()
            virtualDiceNew.text = num

            virtualDiceNew.startAnimation(rotateIn)
            Handler().postDelayed({
                virtualDiceOld.text = num
            }, 500)
        }
    }

    private fun generateNumber(sides : Int) : Int {
        return (1..sides).random()
    }

}
