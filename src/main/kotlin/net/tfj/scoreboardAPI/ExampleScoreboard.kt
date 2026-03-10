package net.tfj.scoreboardAPI

import io.papermc.paper.scoreboard.numbers.NumberFormat
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.Style
import net.tfj.scoreboardAPI.entities.AnimatedLineEntry
import net.tfj.scoreboardAPI.entities.DataLineEntry
import net.tfj.scoreboardAPI.entities.EmptyLine
import net.tfj.scoreboardAPI.entities.ScoreboardData
import net.tfj.scoreboardAPI.entities.StaticLineEntry
import kotlin.math.roundToInt

object ExampleScoreboard : ScoreboardData(
    "example",
    "<aqua><bold>Scoreboard</bold></aqua> API",
    NumberFormat.styled(Style.style(NamedTextColor.DARK_GRAY)),
    listOf(
        DataLineEntry({ player -> "<gray>Welcome </gray><dark_aqua>${player.name}</dark_aqua>" }, 200),
        EmptyLine,
        AnimatedLineEntry(
            listOf(
                { "<gray>Gamemode: <dark_aqua>${it.gameMode.toString().lowercase()}</dark_aqua></gray>" },
                { "<gray>Health: <red>${it.health.roundToInt()}</red></gray>" },
            ),
            100,
            5
        ),
        EmptyLine,
        StaticLineEntry("<dark_gray>|</dark_gray> <gray>This is an <gold>DEV</gold> build!</gray>"),
    )
)