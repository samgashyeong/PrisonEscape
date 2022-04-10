import com.soywiz.klock.seconds
import com.soywiz.korge.*
import com.soywiz.korge.input.mouse
import com.soywiz.korge.input.onClick
import com.soywiz.korge.input.onMouseDrag
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.geom.vector.roundRect
import com.soywiz.korma.interpolation.Easing
import kotlin.random.Random

suspend fun main() = Korge(Korge.Config(module = ConfigModule))