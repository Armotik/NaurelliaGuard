package com.naurellia.naurelliaguard.listeners;

import com.naurellia.naurelliaguard.cheats.players.GuardPlayers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        new GuardPlayers(player);
    }
}
