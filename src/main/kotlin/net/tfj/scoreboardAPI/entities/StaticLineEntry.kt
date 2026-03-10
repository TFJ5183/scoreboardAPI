package net.tfj.scoreboardAPI.entities

import net.kyori.adventure.text.Component
import net.tfj.scoreboardAPI.ScoreboardAPI
import org.bukkit.entity.Player

// Line: Static content
data class StaticLineEntry(val line: String) : LineBaseEntry() {

    // Formats content once
    val text = ScoreboardAPI.instance.miniMessage.deserialize(line)

    // Returns the static text
    override fun getText(player: Player): Component = text

    // Never updates
    override fun shouldUpdate(player: Player): Boolean = false
}
