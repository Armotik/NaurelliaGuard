package com.naurellia.naurelliaguard;

import com.naurellia.naurelliaguard.cheats.detection.MoveListener;
import com.naurellia.naurelliaguard.cheats.players.GuardPlayers;
import com.naurellia.naurelliaguard.commands.NaurelliaGuardScoreCommand;
import com.naurellia.naurelliaguard.commands.NaurelliaGuardScoreResetCommand;
import com.naurellia.naurelliaguard.listeners.EventListeners;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class NaurelliaGuard extends JavaPlugin {

    private static NaurelliaGuard plugin;

    public static NaurelliaGuard getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();

        Objects.requireNonNull(this.getCommand("ngsr")).setExecutor(new NaurelliaGuardScoreResetCommand());
        Objects.requireNonNull(this.getCommand("ngs")).setExecutor(new NaurelliaGuardScoreCommand());

        this.getServer().getPluginManager().registerEvents(new EventListeners(), this);
        this.getServer().getPluginManager().registerEvents(new MoveListener(), this);

        for (Player player : this.getServer().getOnlinePlayers()) {
            new GuardPlayers(player);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
