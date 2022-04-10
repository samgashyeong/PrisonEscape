import com.soywiz.korge.view.Container
import com.soywiz.korge.view.centerBetween
import com.soywiz.korge.view.roundRect
import com.soywiz.korge.view.text
import com.soywiz.korim.color.Colors

class Block(val number: Int, val cellSize : Double) : Container() {
    init {
        roundRect(cellSize, cellSize, 5.0, fill = Colors.WHITE)
        val textColor = Colors.WHITE
        text(number.toString(), textSize = 5.0, textColor) {
            centerBetween(0.0, 0.0, cellSize, cellSize)
        }
    }
}