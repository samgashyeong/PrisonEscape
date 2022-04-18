import com.soywiz.korau.sound.readMusic
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Game2Scene() : Scene() {
    override suspend fun Container.sceneInit() {
        val prisonBitmap = resourcesVfs["robloxPrison.png"].readBitmap()
        val prison = image(prisonBitmap).scale(0.7)
        var mainMusic = resourcesVfs["MainMusic.mp3"].readMusic()
        var dead = resourcesVfs["DeadSound.mp3"].readMusic()
        mainMusic.play()
        val pit1 = roundRect(300, 300, 0, 0, Colors.BROWN).xy(220, 110)
        val pit2 = roundRect(200, 200, 0, 0, Colors.BROWN).xy(720, 330)
        val exit = roundRect(110, 220 , 0, 0, fill = Colors.BLACK).xy(0, 600)


        prison.addUpdater {
            x = views.nativeMouseX - (prison.width / 2 * 0.7)
            y = views.nativeMouseY - ((prison.height / 2 * 0.7))

            if(collidesWith(pit1) or collidesWith(pit2)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    mainMusic.volume = -50.0
                    dead.play()
                    sceneContainer.changeTo<EndScene>()
                }
            }

            if(collidesWith(exit)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    mainMusic.volume = -50.0
                    dead.play()
                    sceneContainer.changeTo<Game3Scene>()
                }
            }
        }
    }
}
