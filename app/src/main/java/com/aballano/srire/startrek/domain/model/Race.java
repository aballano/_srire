package com.aballano.srire.startrek.domain.model;

import java.util.EnumMap;

public enum Race {
    HUMAN(3, 1, 2),
    VULCAN(2, 3, 1),
    BETAZOID(1, 2, 3);

    private final EnumMap<Area, Integer> multipliers = new EnumMap<>(Area.class);

    Race(int commandAreaLevel, int scienceAreaLevel, int engineeringAreaLevel) {
        multipliers.put(Area.COMMAND, commandAreaLevel);
        multipliers.put(Area.SCIENCE, scienceAreaLevel);
        multipliers.put(Area.ENGINEERING, engineeringAreaLevel);
    }

    public int getRaceMultiplierForArea(Area area) {
        return multipliers.get(area);
    }
}
