package Scene

import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korge.view.ktree.readKTree
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import ending.EndSceneWire
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Game7Scene : Scene() {
    override suspend fun Container.sceneInit() {
        val prisonBitmap = resourcesVfs["robloxPrisonGlove.png"].readBitmap()
        val music = resourcesVfs["bgm1.mp3"].readMusic()
        val prison = image(prisonBitmap).scale(0.7)
        val sceneTree = resourcesVfs["game7Scene.ktree"].readKTree(views)
        var dead = resourcesVfs["DeadSound.mp3"].readMusic()
        addChild(sceneTree)
        music.play()


        val wire = sceneTree["wire1"]
        val wire2 = sceneTree["wire2"]
        val wire3 = sceneTree["wire3"]
        val wire4 = sceneTree["wire4"]
        val wire5 = sceneTree["wire5"]
        val wire6 = sceneTree["wire6"]
        val exit = sceneTree["exit"]

        prison.addUpdater {
            x = views.nativeMouseX - (prison.width / 2 * 0.7)
            y = views.nativeMouseY - ((prison.height / 2 * 0.7))

            if(collidesWith(wire)
                    or collidesWith(wire2)
                    or collidesWith(wire3)
                    or collidesWith(wire4)
                    or collidesWith(wire5)
                    or collidesWith(wire6)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    sceneContainer.changeTo<EndSceneWire>()
                    dead.play()
                }
            }

            if(collidesWith(exit)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    sceneContainer.changeTo<Game8Scene>()
                }
            }
        }
    }
}