package com.aballano.srire.startrek.common;

import com.aballano.srire.startrek.domain.model.CrewMember;

import java.util.Comparator;

public class AlphabeticalCrewMemberComparator implements Comparator<CrewMember> {
    @Override
    public int compare(CrewMember crewMember, CrewMember t1) {
        return crewMember.name.compareToIgnoreCase(t1.name);
    }
}
