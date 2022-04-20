package ending

import Scene.Game1Scene
import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class EndSceneSans : Scene() {

    override suspend fun Container.sceneInit() {
        val deadMusic = resourcesVfs["DeadScence.mp3"].readMusic()
        val deadImageBitmap = resourcesVfs["sansEnding.png"].readBitmap()
        deadMusic.play()
        val deadImage = image(deadImageBitmap).xy(110, 210)
        deadImage.scale = 1.2
        val circle = circle(16.0, Colors.RED)
        val startGame = text("Restart").xy(640, 420)
        startGame.color = Colors.BLACK
        startGame.fontSize = 123.0
        val prisonescape = text("You Die").xy(640, 360)

        startGame.onClick {
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