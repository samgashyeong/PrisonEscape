import Scene.*
import com.soywiz.korge.scene.Module
import com.soywiz.korge.scene.Scene
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import ending.*
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
        mapPrototype { Game3Scene() }
        mapPrototype { Game4Scene() }
        mapPrototype { Game5Scene() }
        mapPrototype { Game6Scene() }
        mapPrototype { Game7Scene() }
        mapPrototype { Game8Scene() }
        mapPrototype { ClearScene() }
        mapPrototype { EndSceneEtrect() }
        mapPrototype { EndSceneSans() }
        mapPrototype { EndSceneFall() }
        mapPrototype { EndSceneWLqn() }
        mapPrototype { EndSceneWire() }
    }
}