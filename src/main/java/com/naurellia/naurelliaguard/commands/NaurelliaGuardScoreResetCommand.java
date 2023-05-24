package com.naurellia.naurelliaguard.commands;

import com.naurellia.naurelliaguard.cheats.players.GuardPlayers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NaurelliaGuardScoreResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!commandSender.isOp()) return false;

        if (!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if (!GuardPlayers.isGuardPlayer(player)) return false;

        GuardPlayers.getGuardPlayer(player).setScore(0);

        return true;
    }
}
