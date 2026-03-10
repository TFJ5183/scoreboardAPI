package net.tfj.scoreboardAPI.entities

import net.kyori.adventure.text.Component
import net.tfj.scoreboardAPI.ScoreboardAPI
import org.bukkit.entity.Player

// Line: Animated
data class AnimatedLineEntry(
    val frames: List<(Player) -> String>,
    val interval: Int,
    val updateInterval: Int = 1
) : LineBaseEntry() {

    // Returns the animated text
    override fun getText(player: Player): Component {
        if (frames.isEmpty()) return Component.empty()
        val index = (tick / interval) % frames.size
        return ScoreboardAPI.instance.miniMessage.deserialize(frames[index.toInt()](player))
    }

    // Updates when update interval reached
    override fun shouldUpdate(player: Player): Boolean = tick % updateInterval == 0L
}
