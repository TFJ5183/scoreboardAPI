package net.tfj.scoreboardAPI.entities

import io.papermc.paper.scoreboard.numbers.NumberFormat

open class ScoreboardData(
    val id: String,
    var title: String,
    val numberFormat: NumberFormat = NumberFormat.noStyle(),
    val lines: List<LineBaseEntry>
)
