package rf.pole.lib.core.logging.logback

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.pattern.color.ANSIConstants
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase

class LevelColorConverter : ForegroundCompositeConverterBase<ILoggingEvent>() {
    override fun getForegroundColorCode(event: ILoggingEvent?): String {
        val level = event?.level ?: "INFO"
        return when (level) {
            Level.ERROR -> ANSIConstants.BOLD + ANSIConstants.RED_FG
            Level.WARN -> ANSIConstants.BOLD + ANSIConstants.YELLOW_FG
            Level.INFO -> ANSIConstants.BOLD + ANSIConstants.GREEN_FG
            Level.DEBUG -> ANSIConstants.CYAN_FG
            Level.TRACE -> ANSIConstants.MAGENTA_FG
            else -> ANSIConstants.DEFAULT_FG
        }
    }
}