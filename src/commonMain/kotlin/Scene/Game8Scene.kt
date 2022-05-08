package Scene

import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korge.view.ktree.readKTree
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import ending.EndScene
import ending.EndSceneSans
import ending.EndSceneWire
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Game8Scene() : Scene() {
    override suspend fun Container.sceneInit() {
        val prisonBitmap = resourcesVfs["robloxPrisonGlove.png"].readBitmap()
        val prison = image(prisonBitmap).scale(0.7)
        val sceneTree = resourcesVfs["game8Scene.ktree"].readKTree(views)
        val music = resourcesVfs["watchOut.mp3"].readMusic()
        var dead = resourcesVfs["DeadSound.mp3"].readMusic()
        addChild(sceneTree)
        val johncenaWall = sceneTree["johncenaWallLeft"]
        val sans = sceneTree["sans"]
        val exit = sceneTree["exit"]

        music.play()

        addUpdater {
            johncenaWall.x+=12
        }

        prison.addUpdater {
            x = views.nativeMouseX - (prison.width / 2 * 0.7)
            y = views.nativeMouseY - ((prison.height / 2 * 0.7))

            if(collidesWith(johncenaWall)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    sceneContainer.changeTo<EndScene>()
                }
            }
            if(collidesWith(sans)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    dead.play()
                    sceneContainer.changeTo<EndSceneSans>()
                }
            }
            if(collidesWith(exit)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    sceneContainer.changeTo<ClearScene>()
                }
            }
        }
    }
}
