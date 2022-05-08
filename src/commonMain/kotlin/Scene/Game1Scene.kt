package Scene

import ending.EndScene
import ending.EndSceneEtrect
import ending.EndSceneSans
import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.internal.KorgeInternal
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Game1Scene() : Scene() {
    @OptIn(KorgeInternal::class, kotlinx.coroutines.DelicateCoroutinesApi::class)
    override suspend fun Container.sceneInit() {
        val e1 = roundRect(720, 110 , 0, 0, fill = Colors.RED).xy(0, 400)
        val e2 = roundRect(720, 110 , 0, 0, fill = Colors.RED).xy(1000, 400)
        var mainMusic = resourcesVfs["MainMusic.mp3"].readMusic()
        mainMusic.play()
        var dead = resourcesVfs["DeadSound.mp3"].readMusic()
        val exit = roundRect(110, 220 , 0, 0, fill = Colors.BLACK).xy(900, 0)
        val prisonBitmap = resourcesVfs["robloxPrison.png"].readBitmap()
        val prison = image(prisonBitmap).scale(0.7)
        val sansBitmap = resourcesVfs["sans.png"].readBitmap()
        val sans = image(sansBitmap).xy(220, 450).scale(0.1)
        val policeBitmap = resourcesVfs["johncenaPolice.png"].readBitmap()
        val police = image(policeBitmap).xy(1249, 110)
        val police1 = image(policeBitmap).xy(29, 210)
        var temp = -1
        var temp2 = 1
        var px = police.x+police.width/2
        var px1 = police1.x+police1.width/2
        println(prison.x + prison.y)
        println(police.width)
        prison.addUpdater {
            x = views.nativeMouseX-(prison.width/2 * 0.7)
            y = views.nativeMouseY-((prison.height/2 * 0.7))

            if(collidesWith(sans)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    mainMusic.volume = -50.0
                    dead.play()
                    sceneContainer.changeTo<EndSceneSans>()
                }
            }
            if(collidesWith(police) or collidesWith(police1)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    mainMusic.volume = -50.0
                    dead.play()
                    sceneContainer.changeTo<EndScene>()
                }
            }

            if(collidesWith(exit)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    mainMusic.volume = -50.0
                    sceneContainer.changeTo<Game2Scene>()
                }
            }

            if(collidesWith(e1) or collidesWith(e2)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    mainMusic.volume = -50.0
                    dead.play()
                    sceneContainer.changeTo<EndSceneEtrect>()
                }
            }
        }
        police.addUpdater {
            if(px<=30.0){
                temp = 1
                println("$x $y")
            }
            else if(px>=1250.0) {
                temp = -1
            }
            px += (10 * temp)
            x = px-police.width/2
        }
        police1.addUpdater {
            if(px1<=30.0){
                temp2 = 1
                println("$x $y")
            }
            else if(px1>=1250.0) {
                temp2 = -1
            }
            px1 += (10 * temp2)
            x = px1-police1.width/2

        }
    }
}
