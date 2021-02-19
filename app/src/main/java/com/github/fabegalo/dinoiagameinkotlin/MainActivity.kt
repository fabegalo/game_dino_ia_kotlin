package com.github.fabegalo.dinoiagameinkotlin

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Size
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {

    var gameIsRunning: Boolean = false
    var mortes: Int = 0
    var populacao: Int = 50
    var velocity: Float = 2F
    var gravity: Float = 2F
    lateinit var screenSize: Size

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()

        val star: ImageView = findViewById<ImageView>(R.id.imageView)


        star.layoutParams.height = screenSize.height / 90
        star.layoutParams.width = screenSize.width / 20

        //star.x = screenSize.width.toFloat()
        //star.y = screenSize.height.toFloat()

        val location: IntArray = IntArray(2)
        star.getLocationOnScreen(location)


        println(location[1])

        //println(getNavigationBarSize(this))

        //println(392.toDPx())
        //println(737.toDPx())




    }

    fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
    fun Int.toDPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun initialize() {
        screenSize = Size(
            //getScreenWidth() - 85,
            getScreenWidth(),
            getScreenHeight(),
            //getScreenHeight() - getNavigationBarSize(this) - getStatusBarHeight(this)
        )
        println(screenSize.width)
        println(screenSize.height)
        this.gameIsRunning = true

        val background1: ImageView = findViewById<ImageView>(R.id.background1)
        val background2: ImageView = findViewById<ImageView>(R.id.background2)

        background1.layoutParams.height = screenSize.height + 900
        background1.layoutParams.width = screenSize.width + 200

        background2.layoutParams.height = screenSize.height + 900
        background2.layoutParams.width = screenSize.width + 200

        background1.x = -85F
        background1.y = background2.y - 570

        background2.x = 990F
        background2.y = background2.y - 570


        val game = DinoGame(screenSize, gameIsRunning, velocity, background1, background2)

        //game.run()

        val threadWithRunnable = Thread(game)
        threadWithRunnable.start()
        //threadWithRunnable.join()
        //val teste2 = teste.getDados()
    }

    //X
    fun getScreenWidth(): Int {
        return Resources.getSystem().getDisplayMetrics().widthPixels
    }

    //Y
    fun getScreenHeight(): Int {
        return Resources.getSystem().getDisplayMetrics().heightPixels
    }

    fun getNavigationBarSize(c: Context): Int {
        val resources: Resources = c.getResources()
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else 0
    }

    fun getStatusBarHeight(c: Context): Int {
        val resources: Resources = c.getResources()
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        }else 0
    }
}