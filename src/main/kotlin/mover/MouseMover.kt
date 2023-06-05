package mover
import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import java.awt.Dimension
import java.awt.Robot
import java.awt.Toolkit
import kotlin.concurrent.thread
import kotlin.system.exitProcess

fun main() {

    useless()

}

var status: String = "stay"

fun useless() {
    //println("Ctrl Q")
    val robot = Robot()
    GlobalScreen.registerNativeHook()

    GlobalScreen.addNativeKeyListener(GlobalKeyListener())

    val screenSize: Dimension = Toolkit.getDefaultToolkit().screenSize
    val width = screenSize.getWidth()
    val height = screenSize.getHeight()



    var verticalPasses = 0
    var horizontalPasses = 0
    var stack = 0

    var verticalAdjustment = 0

    var horizontal = 0
    var vertical = 0

    println(1..50000)

    while(1 == 1) {
        while(status == "pass") {
            horizontal++
            robot.mouseMove(horizontal, vertical)
            if (vertical >= height && horizontal >= width) {
                vertical = 0
                verticalPasses++
            }
            if (horizontal >= width) {
                vertical += 5
                horizontal = 0
                horizontalPasses++
                stack++
                verticalAdjustment++
                if (stack % 200 == 0)
                    println("Horizontal passes: $horizontalPasses, Vertical resets: $verticalPasses, Vertical adjustments: $verticalAdjustment")
            }
        }

        while (status == "stay") {

            robot.mouseMove(400, 400)
        }

        while(status == "square") {

            val startXO = width / 2 - 225
            val startYO = height / 2 - 225
            val startX = startXO.toInt()
            val startY = startYO.toInt()

            var squareWidth = 450
            var squareHeight = 450

            robot.mouseMove(startX, startY)

            while(status == "square") {
                for (i in 1..squareWidth) {
                    robot.mouseMove(startX + i, startY)

                }

                for (i in 1..squareHeight) {
                    robot.mouseMove(startX + squareWidth, startY + i)

                }

                for (i in 1..squareWidth) {
                    robot.mouseMove(startX + squareWidth - i, startY + squareHeight)

                }

                for (i in 1..squareHeight) {
                    robot.mouseMove(startX, startY + squareHeight - i)

                }

//                squareWidth+=10
//                squareHeight+=10
//                if (squareWidth == 1000) { squareWidth = 10; squareHeight = 10 }
            }
        }
    }
}
class GlobalKeyListener : NativeKeyListener {
    override fun nativeKeyPressed(event: NativeKeyEvent) {
        // Exit if user presses Ctrl + Q
        if (event.keyCode == NativeKeyEvent.VC_Q && event.modifiers and NativeKeyEvent.CTRL_MASK != 0) {
            GlobalScreen.unregisterNativeHook()
            exitProcess(69)
        }
        if (event.keyCode == NativeKeyEvent.VC_W) {
            if (status == "pass") {
                status = "square"
            } else if (status == "square") {
                status = "stay"
            } else if (status == "stay") {
                status = "pass"
            }
        }
    }
}



