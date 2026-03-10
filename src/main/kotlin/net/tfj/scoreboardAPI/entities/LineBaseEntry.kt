package net.tfj.scoreboardAPI.entities

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

// Line: base
// Dont use me directly
abstract class LineBaseEntry {
    var tick = 0L

    // Returns the component representation
    abstract fun getText(player: Player): Component

    // Determines if this entry needs a visual update
    open fun shouldUpdate(player: Player): Boolean = false
}
