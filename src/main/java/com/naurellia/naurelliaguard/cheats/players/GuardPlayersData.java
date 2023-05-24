package com.naurellia.naurelliaguard.cheats.players;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GuardPlayersData {

    private Location previousLocation;
    private Player player;
    private long moveTime;

    public GuardPlayersData(Player player) {
        this.player = player;
    }

    /**
     * Get the player
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set the player
     * @param player Player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get the location
     * @return Location
     */
    public Location getPreviousLocation() {
        return previousLocation;
    }

    /**
     * Set the location
     * @param previousLocation Location
     */
    public void setPreviousLocation(Location previousLocation) {
        this.previousLocation = previousLocation;
    }

    /**
     * Get the move start time
     * @return Move start time
     */
    public long getMoveTime() {
        return moveTime;
    }

    /**
     * Set the move start time
     * @param moveTime Move start time
     */
    public void setMoveTime(long moveTime) {
        this.moveTime = moveTime;
    }
}
