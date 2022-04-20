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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Game2Scene() : Scene() {
    override suspend fun Container.sceneInit() {
        val prisonBitmap = resourcesVfs["robloxPrison.png"].readBitmap()
        val prison = image(prisonBitmap).scale(0.7)
        val policeBitmap = resourcesVfs["johncenaPolice.png"].readBitmap()
        val police1 = image(policeBitmap).xy(0, 400)
        val police2 = image(policeBitmap).xy(1100, 500)
        val police3 = image(policeBitmap).xy(550, 500)
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

            if(collidesWith(police1) or collidesWith(police2) or collidesWith(police3)){
                CoroutineScope(Dispatchers.Unconfined).launch {
                    mainMusic.volume = -50.0
                    dead.play()
                    sceneContainer.changeTo<EndScene>()
                }
            }
        }

        police1.addUpdater {
            CoroutineScope(Dispatchers.Default).launch {
                police1.tween(police1::x[police1.x, prison.x], time = 0.5.seconds, easing = Easing.EASE_IN_OUT)
                police1.tween(police1::y[police1.y, prison.y], time = 0.5.seconds, easing = Easing.EASE_IN_OUT)
            }
        }

        police2.addUpdater {
            CoroutineScope(Dispatchers.Default).launch {
                police2.tween(police2::x[police2.x, prison.x], time = 0.5.seconds, easing = Easing.SMOOTH)
                police2.tween(police2::y[police2.y, prison.y], time = 0.5.seconds, easing = Easing.SMOOTH)
            }
        }
        police3.addUpdater {
            CoroutineScope(Dispatchers.Default).launch {
                police3.tween(police3::x[police1.x, prison.x], time = 0.5.seconds, easing = Easing.SMOOTH)
                police3.tween(police3::y[police1.y, prison.y], time = 0.5.seconds, easing = Easing.SMOOTH)
            }
        }
    }
}
