package net.tfj.scoreboardAPI.entities

import net.kyori.adventure.text.Component
import net.tfj.scoreboardAPI.ScoreboardAPI
import org.bukkit.entity.Player

// Line: containing data
data class DataLineEntry(val provider: (Player) -> String, val interval: Int) : LineBaseEntry() {
    // Returns the data text
    override fun getText(player: Player): Component = ScoreboardAPI.instance.miniMessage.deserialize(provider(player))

    // Updates when interval reached
    override fun shouldUpdate(player: Player): Boolean = tick % interval == 0L
}
