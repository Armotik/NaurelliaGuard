package com.naurellia.naurelliaguard.utils.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class GuardBlocks {

    private GuardBlocks () {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Check if a material is a liquid
     * @param material Material to check
     * @return true if the material is a liquid
     */
    public static boolean isLiquid(Material material) {
        return material == Material.WATER || material == Material.LAVA;
    }

    /**
     * Check if a block is a liquid
     * @param block Block to check
     * @return true if the block is a liquid
     */
    public static boolean isBlockLiquid(Block block) {
        return isLiquid(block.getType());
    }
}
