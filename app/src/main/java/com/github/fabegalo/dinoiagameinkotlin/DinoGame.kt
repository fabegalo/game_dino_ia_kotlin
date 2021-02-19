package com.github.fabegalo.dinoiagameinkotlin

import android.os.SystemClock.sleep
import android.util.Size
import android.widget.ImageView

class DinoGame(screenSize: Size, status: Boolean, velocity: Float, bck1: ImageView, bck2: ImageView) : Runnable {
    val screenSize: Size = screenSize
    val gameStatus: Boolean = status
    val velocity: Float = velocity
    val background1: ImageView = bck1
    val background2: ImageView = bck2

    override fun run() {

        println("------ COMEÇOU O GAME !!!! :D ------")
        var lastTime = System.nanoTime()
        val nsPerTick = 1000000000.0 / 60.0

        var frames = 0
        var ticks = 0

        var lastTimer = System.currentTimeMillis()
        var delta = 0.0

        Thread.sleep(1)

        while (this.gameStatus) {
            val now = System.nanoTime()
            delta += (now - lastTime) / nsPerTick
            lastTime = now

            while(delta >= 1){
                ticks++;
                //tick();
                delta-= 1;
            }

            frames++
            animateBackground() //render

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000
                println("TICK: $ticks, FPS: $frames")
                frames = 0
                ticks = 0
            }
        }
    }

    fun animateBackground() {
        val screnWidth: Int = (screenSize.width - screenSize.width) - screenSize.width

        background1.x = background1.x - velocity / 3000

        background2.x = background2.x - velocity / 3000

        if(background1.x < screnWidth){
            //println("REINICIA A POSIÇÃO SAIU DA TELA!!")
            background1.x = 900F
        }

        if(background2.x < screnWidth){
            //println("REINICIA A POSIÇÃO SAIU DA TELA!!")
            background2.x = 900F
        }
    }

}