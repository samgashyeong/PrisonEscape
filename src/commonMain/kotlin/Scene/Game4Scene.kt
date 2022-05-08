package Scene

import com.soywiz.klock.seconds
import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.animate.animate
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Angle
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.interpolation.Easing
import ending.EndSceneFall
import ending.EndSceneSans
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Game4Scene : Scene(){
    override suspend fun Container.sceneInit() {
        val prisonBitmap = resourcesVfs["robloxPrison.png"].readBitmap()
        val fallwall = roundRect(200, 1200, 0, 0, fill = Colors.BROWN).xy(700, 0).rotation(Angle(-0.0))
        //val brige = roundRect(350, 150, 0, 0, fill = Colors.SANDYBROWN).xy(500, 350)
        val treeBitmap = resourcesVfs["tree.jpg"].readBitmap()
        val treeBitmap1 = resourcesVfs["tree2.png"].readBitmap()
        val treeBitmap2 = resourcesVfs["tree3.jpg"].readBitmap()
        var mainMusic = resourcesVfs["bgm3.mp3"].readMusic()
        val exit = roundRect(110, 220 , 0, 0, fill = Colors.BLACK).xy(500, 0)
        val tree = image(treeBitmap).xy(670, 200).scale(0.7, 0.4).rotation((-90).degrees)
        val tree1 = image(treeBitmap1).xy(670, 400).scale(0.25, 0.2).rotation((-90).degrees)
        val tree2 = image(treeBitmap2).xy(670, 600).scale(0.24, 0.2).rotation((-90).degrees)
        var collide = 0
        var dead = resourcesVfs["DeadSound.mp3"].readMusic()
        mainMusic.play()

        println("${tree.anchorX}, ${tree.anchorY}")
        val prison = image(prisonBitmap).scale(0.7)

        prison.addUpdater {
            x = views.nativeMouseX - (prison.width / 2 * 0.7)
            y = views.nativeMouseY - ((prison.height / 2 * 0.7))

            if(collidesWith(tree1) and collidesWith(fallwall)){

            }
            else if(collidesWith(tree) or collidesWith(tree2) or collidesWith(fallwall)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    dead.play()
                    sceneContainer.changeTo<EndSceneFall>()
                }
            }

            if(collidesWith(exit)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    sceneContainer.changeTo<Game5Scene>()
                }
            }

        }
    }
}
