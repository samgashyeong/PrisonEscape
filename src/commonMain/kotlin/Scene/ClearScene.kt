package Scene

import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korge.view.ktree.readKTree
import com.soywiz.korim.color.Colors
import com.soywiz.korio.file.std.resourcesVfs

class ClearScene() : Scene() {
    override suspend fun Container.sceneInit() {
        val sceneTree = resourcesVfs["clearScene.ktree"].readKTree(views)
        addChild(sceneTree)
        val circle = circle(16.0, Colors.RED)
        val music = resourcesVfs["clap.mp3"].readMusic()
        music.play()

        val restart = sceneTree["restart"]

        restart.onClick {
            sceneContainer.changeTo<Game1Scene>()
        }
        circle.addUpdater {
            x = views.nativeMouseX-circle.radius
            y = views.nativeMouseY-circle.radius

            if(collidesWith(restart)){
                restart.colorMul = Colors.RED
            }
            else{
                restart.colorMul = Colors.BLACK
            }
        }
    }
}
