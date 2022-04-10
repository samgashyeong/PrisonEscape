import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import kotlin.reflect.KClass

object ConfigModule : Module() {
    override val bgcolor = Colors.BEIGE
    override val size = SizeInt(1280, 720)
    override val mainScene: KClass<out Scene>
        get() = GameStart::class
    override suspend fun AsyncInjector.configure(){
        mapPrototype { GameStart() }
        mapPrototype { EndScene() }
        mapPrototype { Game1Scene() }
        mapPrototype { Game2Scene() }
    }
}