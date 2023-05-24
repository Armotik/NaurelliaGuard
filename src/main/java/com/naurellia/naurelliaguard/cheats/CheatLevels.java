package com.naurellia.naurelliaguard.cheats;

public enum CheatLevels {

    NONE(0, "None", "§a"),
    POSSIBLE(1, "Possible", "§e"),
    SUSPECT(2, "Suspect", "§6"),
    PROBABLE(3, "Probable", "§c"),
    CERTAIN(4, "Certain", "§4"),
    CRITICAL(5, "Critical", "§4§l"),
    ;

    private final int level;
    private final String name;
    private final String color;

    CheatLevels(int level, String name, String color) {
        this.level = level;
        this.name = name;
        this.color = color;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
