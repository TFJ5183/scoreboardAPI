package net.tfj.scoreboardAPI

import net.kyori.adventure.text.minimessage.MiniMessage
import net.tfj.scoreboardAPI.entities.LineBaseEntry
import net.tfj.scoreboardAPI.entities.ScoreboardData
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Scoreboard
import java.util.*

class ScoreboardAPI(
    val instance: JavaPlugin,
    val defaultBoard: ScoreboardData,
    val miniMessage: MiniMessage = MiniMessage.miniMessage()
) {

    // Variables
    private var map: HashMap<UUID, ScoreboardData> = hashMapOf()

    companion object {
        lateinit var instance: ScoreboardAPI
    }

    // On class init
    init {
        Companion.instance = this
        startUpdater()
    }

    /**
     * Sets default scoreboard for [player]
     * @param player the player that should be reset
     * @since 1.0
     */
    fun resetScoreboard(player: Player) {
        setScoreboard(player, defaultBoard)
    }

    /**
     * Sets scoreboard for specified [player]
     * @param player the player that the scoreboard should be set
     * @param scoreboardData data entity containing the data about the scoreboard
     * @since 1.0
     */
    fun setScoreboard(player: Player, scoreboardData: ScoreboardData) {
        map[player.uniqueId] = scoreboardData
        player.scoreboard = createScoreboard(player, scoreboardData)
    }

    /**
     * Gets [ScoreboardData]
     * @return possibly null [ScoreboardData] entity.
     * @since 1.0
     */
    fun getScoreboard(player: Player): ScoreboardData? {
        return map[player.uniqueId]
    }

    // Creates new scoreboard
    private fun createScoreboard(player: Player, data: ScoreboardData): Scoreboard {
        val board = Bukkit.getScoreboardManager().newScoreboard

        // Creates viewboard
        val objective = board.registerNewObjective("viewboard", Criteria.DUMMY, miniMessage.deserialize(data.title))

        // Sets display slot
        objective.displaySlot = DisplaySlot.SIDEBAR

        // Force set/update
        applyData(player, board, data, true)

        // Return
        return board
    }

    /**
     * Applies [data] to specified [scoreboard] for specified [player]
     * @param player is required for data frames
     * @param forceUpdate if everything should be updated regardless of the update frame
     * @since 1.0
     */
    private fun applyData(player: Player, scoreboard: Scoreboard, data: ScoreboardData, forceUpdate: Boolean = false) {
        val objective = scoreboard.getObjective(DisplaySlot.SIDEBAR) ?: return

        // Updates hole board
        if (forceUpdate) {
            objective.displayName(miniMessage.deserialize(data.title))
            objective.numberFormat(data.numberFormat)
        }

        val lines = data.lines

        // Every line
        for (i in lines.indices) {
            val score = lines.size - i - 1
            val entryKey = translate(score)
            val line = lines[i]
            var team = scoreboard.getTeam(entryKey)

            // Creates team if not exists
            if (team == null) {
                // Creates team
                team = scoreboard.registerNewTeam(entryKey)

                // Connects team and entry
                team.addEntry(entryKey)

                // Sets team for line
                objective.getScore(entryKey).score = score

                // Sets content
                team.prefix(line.getText(player))
            }

            // Updates line
            if (forceUpdate || line.shouldUpdate(player)) {
                team.prefix(line.getText(player))
            }
        }

        if (forceUpdate) {
            for (i in (lines.size + 1)..15) {
                val entryKey = translate(i)
                scoreboard.resetScores(entryKey)
                scoreboard.getTeam(entryKey)?.unregister()
            }
        }
    }

    /**
     * Turns line number to invisible entry name
     * @param line line number
     * @return paragraph + number or single letter
     * @since 1.0
     */
    private fun translate(line: Int): String {
        return "§" + line.toString(16).chunked(1).joinToString("§")
    }

    /**
     * Starts internal updater
     * @since 1.0
     */
    private fun startUpdater() {
        object : BukkitRunnable() {
            override fun run() {
                val processedLines = mutableSetOf<LineBaseEntry>()
                for (player in Bukkit.getOnlinePlayers()) {
                    val data = map[player.uniqueId] ?: continue

                    data.lines.forEach {
                        if (processedLines.add(it)) it.tick++
                    }

                    applyData(player, player.scoreboard, data)
                }
            }
        }.runTaskTimer(instance, 0L, 1L)
    }

}