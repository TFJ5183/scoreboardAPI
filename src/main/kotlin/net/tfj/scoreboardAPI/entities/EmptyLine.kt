package net.tfj.scoreboardAPI.entities

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

// Line: Empty
object EmptyLine : LineBaseEntry() {
    // Returns empty component
    override fun getText(player: Player): Component = Component.empty()

    // Never updates
    override fun shouldUpdate(player: Player): Boolean = false
}
