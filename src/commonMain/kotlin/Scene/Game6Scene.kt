package Scene

import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class Game6Scene : Scene() {
    override suspend fun Container.sceneInit() {
        var a1004 = image(resourcesVfs["1004.png"].readBitmap())
        val music = resourcesVfs["bgm5.mp3"].readMusic()
        val startGame = text("GameStart").xy(800, 600)
        startGame.fontSize = 100.0
        startGame.color = Colors.BLACK
        val circle = circle(16.0, Colors.RED)
        music.play()
        circle.addUpdater {
            x = views.nativeMouseX-circle.radius
            y = views.nativeMouseY-circle.radius

            if(collidesWith(startGame)){
                startGame.color = Colors.RED
            }
            else{
                startGame.color = Colors.BLACK
            }
        }

        startGame.onClick {
            sceneContainer.changeTo<Game7Scene>()
        }
    }
}