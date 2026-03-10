package net.tfj.scoreboardAPI.entities

import net.kyori.adventure.text.Component
import net.tfj.scoreboardAPI.ScoreboardAPI
import org.bukkit.entity.Player

/**
 * Animated line entity. The frame switches every [interval] ticks
 *
 * @param frames list of string. Each line entry is a frame en
 * @param interval amount of ticks
 * @param updateInterval amount of ticks until the line should be updated. The counter will not be reset on frame change.
 * @since 1.0
 */
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

    // Updates when interval reached
    override fun shouldUpdate(player: Player): Boolean = tick % updateInterval == 0L
}
