package com.alliance.entity.type;

public enum SuperPower {

    SPEED(0),
    STRENGTH(1),
    INVISIBLE(2),
    FIRE_MAGIC(3);

    private Integer power;

    SuperPower(int power) {
        this.power = power;
    }

    public Integer getPowerNumber() {
        return power;
    }
}
