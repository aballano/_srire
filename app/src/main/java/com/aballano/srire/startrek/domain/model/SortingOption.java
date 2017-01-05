package com.aballano.srire.startrek.domain.model;

import com.aballano.srire.startrek.common.AlphabeticalCrewMemberComparator;
import com.aballano.srire.startrek.common.AreaCrewMemberComparator;

import java.util.Comparator;

public enum SortingOption {
    ALPHABETICALLY(new AlphabeticalCrewMemberComparator()),
    COMMAND(new AreaCrewMemberComparator(Area.COMMAND)),
    SCIENCE(new AreaCrewMemberComparator(Area.SCIENCE)),
    ENGINEERING(new AreaCrewMemberComparator(Area.ENGINEERING));

    private final Comparator<? super CrewMember> comparator;

    SortingOption(Comparator<CrewMember> comparator) {
        this.comparator = comparator;
    }

    public Comparator<? super CrewMember> getComparator() {
        return comparator;
    }

}
