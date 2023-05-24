package com.naurellia.naurelliaguard.cheats.players;

import com.naurellia.naurelliaguard.NaurelliaGuard;
import com.naurellia.naurelliaguard.cheats.CheatLevels;
import com.naurellia.naurelliaguard.utils.blocks.GuardBlocks;
import com.naurellia.naurelliaguard.utils.louise.Louise;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class GuardPlayers {

    private static Map<Player, GuardPlayers> guardPlayers = new HashMap<>();

    private final Player player;
    private CheatLevels cheatLevels;
    private double score;
    private final GuardPlayersData guardPlayersData;
    private BukkitRunnable moveTimerTask;

    public GuardPlayers(Player player) {
        this.player = player;
        this.cheatLevels = CheatLevels.NONE;
        this.score = 0;

        this.guardPlayersData = new GuardPlayersData(player);
        guardPlayers.put(player, this);
    }

    /**
     * Get the guard players
     *
     * @return Guard players
     */
    public Map<Player, GuardPlayers> getGuardPlayers() {
        return guardPlayers;
    }

    /**
     * Set the guard players
     *
     * @param guardPlayers Guard players
     */
    public static void setGuardPlayers(Map<Player, GuardPlayers> guardPlayers) {
        GuardPlayers.guardPlayers = guardPlayers;
    }

    /**
     * Get the player
     *
     * @param player Player
     * @return Player
     */
    public static GuardPlayers getGuardPlayer(Player player) {
        return guardPlayers.get(player);
    }

    /**
     * Check if a player is a guard player
     *
     * @return true if the player is a guard player
     */
    public static boolean isGuardPlayer(Player player) {
        return guardPlayers.containsKey(player);
    }

    /**
     * Get the player
     *
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the cheat levels
     *
     * @return Cheat levels
     */
    public CheatLevels getCheatLevels() {
        return cheatLevels;
    }

    /**
     * Set the cheat levels
     *
     * @param cheatLevels Cheat levels
     */
    public void setCheatLevels(CheatLevels cheatLevels) {
        this.cheatLevels = cheatLevels;
    }

    /**
     * Get the score
     *
     * @return Score
     */
    public double getScore() {
        return score;
    }

    /**
     * Set the score
     *
     * @param score Score
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Get the guard players data
     *
     * @return Guard players data
     */
    public GuardPlayersData getGuardPlayersData() {
        return guardPlayersData;
    }

    /**
     * Start the move time
     */
    public void startMoveTime() {

        moveTimerTask = new BukkitRunnable() {

            private Location previousLocation = guardPlayersData.getPreviousLocation();
            private long moveStartTime;
            private double moveScore = 0;

            @Override
            public void run() {
                Location currentLocation = player.getLocation();

                if (!currentLocation.equals(previousLocation)) {

                    guardPlayersData.setPreviousLocation(currentLocation);
                    previousLocation = currentLocation;

                    if (moveStartTime == 0) {
                        moveStartTime = System.currentTimeMillis();
                    }

                    if (GuardBlocks.isBlockLiquid(currentLocation.getBlock().getRelative(BlockFace.DOWN))) {

                        moveScore += 80;
                    }

                } else {

                    if (moveStartTime != 0) {
                        long moveDuration = System.currentTimeMillis() - moveStartTime;
                        moveStartTime = 0;

                        guardPlayersData.setMoveTime(moveDuration);
                    }

                    if (GuardBlocks.isBlockLiquid(currentLocation.getBlock().getRelative(BlockFace.DOWN))) {

                        moveScore += 80;

                        if (moveScore > 10000) {

                            update(moveScore);
                            stopMoveTimer();
                        }

                    } else {

                        stopMoveTimer();
                        update(moveScore);
                    }
                }

                previousLocation = currentLocation;
                guardPlayersData.setPreviousLocation(previousLocation);
            }
        };

        moveTimerTask.runTaskTimerAsynchronously(NaurelliaGuard.getPlugin(), 0L, 10L);
    }

    public boolean isMoving() {
        return moveTimerTask != null;
    }

    public void stopMoveTimer() {
        if (moveTimerTask != null) {
            moveTimerTask.cancel();
            moveTimerTask = null;
            guardPlayersData.setMoveTime(0L);
        }
    }

    public void update(double newScore) {

        CheatLevels oldCheatLevels = cheatLevels;
        score += newScore;

        if (score < 100) {

            cheatLevels = CheatLevels.NONE;
        } else if (score >= 100 && score < 200) {

            cheatLevels = CheatLevels.POSSIBLE;
        } else if (score >= 200 && score < 300) {

            cheatLevels = CheatLevels.SUSPECT;
        } else if (score >= 300 && score < 400) {

            cheatLevels = CheatLevels.PROBABLE;
        } else if (score >= 400 && score < 500) {

            cheatLevels = CheatLevels.CERTAIN;
        } else if (score >= 500) {

            cheatLevels = CheatLevels.CRITICAL;
        }

        if (oldCheatLevels != cheatLevels) {
            System.out.println(Louise.getName() + "§bI changed the cheat level of §c" + player.getName() + "§b to §c" + cheatLevels.name() + "§b because his score is now §c" + score);
            Bukkit.broadcastMessage(Louise.getName() + "§bI changed the cheat level of §c" + player.getName() + "§b to §c" + cheatLevels.name() + "§b because his score is now §c" + score);
        }
    }
}
