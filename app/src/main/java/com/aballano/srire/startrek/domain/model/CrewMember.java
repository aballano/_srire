package com.aballano.srire.startrek.domain.model;

public class CrewMember {
    public Area area;
    public String name;
    public Race race;

    public CrewMember(String name, Race race, Area area) {
        this.name = name;
        this.race = race;
        this.area = area;
    }
}
