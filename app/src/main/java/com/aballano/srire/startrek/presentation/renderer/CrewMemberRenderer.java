package com.aballano.srire.startrek.presentation.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aballano.srire.R;
import com.aballano.srire.startrek.domain.model.CrewMember;
import com.pedrogomez.renderers.Renderer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CrewMemberRenderer extends Renderer<CrewMember> {

    @BindView(R.id.crew_name)
    TextView crewNameTextView;
    @BindView(R.id.crew_race)
    TextView crewRaceTextView;
    @BindView(R.id.position_color)
    View positionColor;
    @BindView(R.id.race_multiplier)
    TextView raceMultiplierTextView;

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(R.layout.crewmember_list_item, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void render(List<Object> payloads) {
        CrewMember member = getContent();
        crewNameTextView.setText(member.name);
        crewRaceTextView.setText(member.race.name());
        positionColor.setBackgroundColor(member.area.color);
        raceMultiplierTextView.setText("x" + member.race.getRaceMultiplierForArea(member.area));
    }
}
