package com.naurellia.naurelliaguard.commands;

import com.naurellia.naurelliaguard.cheats.players.GuardPlayers;
import com.naurellia.naurelliaguard.utils.louise.Louise;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NaurelliaGuardScoreCommand implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!commandSender.isOp()) return false;

        if (!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if (!GuardPlayers.isGuardPlayer(player)) return false;

        player.sendMessage(Louise.getName() + "§7Your score is §c" + GuardPlayers.getGuardPlayer(player).getScore() + "§7.");

        return true;
    }
}
