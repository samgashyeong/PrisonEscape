package Scene

import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.input.onClick
import com.soywiz.korge.internal.KorgeInternal
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class GameStart() : Scene() {
    @OptIn(KorgeInternal::class)
    override suspend fun Container.sceneInit() {
        val MainImage = resourcesVfs["robloxPrison.png"].readBitmap()
        val gameStartMusic = resourcesVfs["GameStartMusic.mp3"].readMusic()
        gameStartMusic.play()
        val circle = circle(16.0, Colors.RED)
        val startGame = text("GameStart").xy(640, 420)
        startGame.color = Colors.BLACK
        startGame.fontSize = 123.0
        val prisonescape = text("Prison Escape").xy(640, 360)
        val mainImage = image(MainImage).xy(110, 210)
        mainImage.scale = 2.0
        startGame.onClick {
            println(gameStartMusic.volume)
            gameStartMusic.volume = -50.0
            println(gameStartMusic.volume)
            sceneContainer.changeTo<Game1Scene>()
        }
        prisonescape.apply {
            color = Colors.BLACK
            fontSize = 64.0
        }

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
    }
}
