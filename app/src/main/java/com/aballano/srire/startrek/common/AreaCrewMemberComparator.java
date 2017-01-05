package com.aballano.srire.startrek.common;

import com.aballano.srire.startrek.domain.model.Area;
import com.aballano.srire.startrek.domain.model.CrewMember;

import java.util.Comparator;

public class AreaCrewMemberComparator implements Comparator<CrewMember> {

    private final Area area;

    public AreaCrewMemberComparator(Area area) {
        this.area = area;
    }

    @Override
    public int compare(CrewMember t1, CrewMember t2) {
        int t1AreaMultiplier = t1.race.getRaceMultiplierForArea(area);
        int t2AreaMultiplier = t2.race.getRaceMultiplierForArea(area);

        int result;
        // Give higher priority to the specified Area
        result = (t2.area == area ? 100 : 0) - (t1.area == area ? 100 : 0);
        // Then sort by multiplier
        result += t2AreaMultiplier * 10 - t1AreaMultiplier * 10;
        // And finally by name
        result += t1.name.compareToIgnoreCase(t2.name);

        return result;
    }
}
