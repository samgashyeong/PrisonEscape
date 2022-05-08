package Scene

import com.soywiz.klock.seconds
import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.interpolation.Easing
import ending.EndSceneFall
import ending.EndSceneWLqn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Game5Scene : Scene() {
    override suspend fun Container.sceneInit() {
        val prisonBitmap = resourcesVfs["robloxPrison.png"].readBitmap()
        val prison = image(prisonBitmap).scale(0.7)
        val johnsinaWallLeft = image(resourcesVfs["johncenaWallLeft.png"].readBitmap()).xy(-720, 0)
        val johnsinaWallRight = image(resourcesVfs["johncenaWallRight.png"].readBitmap()).xy(1370,0)
        val music = resourcesVfs["watchOut.mp3"].readMusic()
        val exit = roundRect(110, 220 , 0, 0, fill = Colors.BLACK).xy(600, 620)
        var dead = resourcesVfs["DeadSound.mp3"].readMusic()
        music.play()
        addUpdater {
            johnsinaWallLeft.x+=15
            johnsinaWallRight.x-=15
        }

        prison.addUpdater {
            x = views.nativeMouseX - (prison.width / 2 * 0.7)
            y = views.nativeMouseY - ((prison.height / 2 * 0.7))


            if(collidesWith(johnsinaWallLeft) or collidesWith(johnsinaWallRight)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    dead.play()
                    sceneContainer.changeTo<EndSceneWLqn>()
                }
            }

            if(collidesWith(exit)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    sceneContainer.changeTo<Game6Scene>()
                }
            }
        }
    }
}