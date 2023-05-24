package com.naurellia.naurelliaguard.cheats.detection;

import com.naurellia.naurelliaguard.cheats.players.GuardPlayers;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        GuardPlayers guardPlayer = GuardPlayers.getGuardPlayer(player);

        Location from = event.getFrom();
        Location to = event.getTo();

        //if (player.isOp()) return;

        if (player.isFlying()) {

            // TODO: Add fly detection
        }

        else if (from.distance(to) > 0) {

            if (guardPlayer.isMoving()) return;

            guardPlayer.startMoveTime();
        }
    }
}
