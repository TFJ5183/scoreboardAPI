package net.tfj.scoreboardAPI.entities

import io.papermc.paper.scoreboard.numbers.NumberFormat

/**
 * Data entity for the scoreboard
 *
 * @param id internally not used. Allows to compare entities more easily
 * @param title Title of the scoreboard. Mini message support
 * @param numberFormat format of the line/value numbers. Hidden by default
 * @param lines List of lines. First value will be display at the top of the scoreboard, last at the button
 * @since 1.0
 */
open class ScoreboardData(
    val id: String,
    var title: String,
    val numberFormat: NumberFormat = NumberFormat.noStyle(),
    val lines: List<LineBaseEntry>
)
