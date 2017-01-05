package com.aballano.srire.startrek.presentation.presenter;

import com.aballano.common.BasePresenter;
import com.aballano.common.BaseView;
import com.aballano.common.TransformersProvider;
import com.aballano.srire.startrek.domain.model.Area;
import com.aballano.srire.startrek.domain.model.CrewMember;
import com.aballano.srire.startrek.domain.model.Race;
import com.aballano.srire.startrek.domain.model.SortingOption;
import com.aballano.srire.startrek.presentation.presenter.MainPresenter.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;

public class MainPresenter extends BasePresenter<View> {

    private static final int CREW_NUMBER = 500;
    private final TransformersProvider transformersProvider;
    private CharSequence[] options;
    ArrayList<CrewMember> items;

    @Inject
    public MainPresenter(TransformersProvider transformersProvider) {
        this.transformersProvider = transformersProvider;
    }

    @Override
    public void bind(View view) {
        super.bind(view);

        // Here we could show some loading progress
        addSubscription(generateRandomData()
              .compose(transformersProvider.<ArrayList<CrewMember>>ioSingleTransformer())
              .subscribe(new BiConsumer<ArrayList<CrewMember>, Throwable>() {
                  @Override
                  public void accept(ArrayList<CrewMember> members, Throwable throwable) throws Exception {
                      if (members != null) {
                          items = members;
                          // By default those are Alphabetically ordered, but in a different scenario we could simply
                          //add a map to sort them by a default sorting.
                          MainPresenter.this.view.showItems(members);
                      } else if (throwable != null) {
                          //show error
                      }
                  }
              }));
    }

    private Single<ArrayList<CrewMember>> generateRandomData() {
        return Single.fromCallable(new Callable<ArrayList<CrewMember>>() {
            @Override
            public ArrayList<CrewMember> call() throws Exception {
                Random random = new Random();
                Race[] races = Race.values();
                Area[] areas = Area.values();
                ArrayList<CrewMember> members = new ArrayList<>(CREW_NUMBER);

                for (int n = 0; n < CREW_NUMBER; n++) {
                    members.add(new CrewMember("Crew member #" + n,
                          races[random.nextInt(races.length)],
                          areas[random.nextInt(areas.length)]));
                }

                return members;
            }
        });
    }

    public void onSortingMenuClicked() {
        view.showSortingOptions(generateSortingOptions());
    }

    private CharSequence[] generateSortingOptions() {
        if (options == null) {
            SortingOption[] sortingOptions = SortingOption.values();
            options = new CharSequence[sortingOptions.length];
            for (int i = 0, areasLength = sortingOptions.length; i < areasLength; i++) {
                SortingOption option = sortingOptions[i];
                options[i] = option.name();
            }
        }
        return options;
    }

    public void onSortingItemClicked(int which) {
        SortingOption sortingOption = SortingOption.values()[which];
        // Here we could show some loading progress
        addSubscription(doSortBy(sortingOption)
              .compose(transformersProvider.<ArrayList<CrewMember>>ioSingleTransformer())
              .subscribe(new BiConsumer<ArrayList<CrewMember>, Throwable>() {
                  @Override
                  public void accept(ArrayList<CrewMember> crewMembers, Throwable throwable) throws Exception {
                      if (crewMembers != null) {
                          // A cool effect here would be to use the DiffUtil to move the items around instead
                          view.clearItems();
                          view.showItems(crewMembers);
                      } else if (throwable != null) {
                          //show error
                      }
                  }
              }));
    }

    private Single<ArrayList<CrewMember>> doSortBy(final SortingOption sortingOption) {
        return Single.fromCallable(new Callable<ArrayList<CrewMember>>() {
            @Override
            public ArrayList<CrewMember> call() throws Exception {
                Collections.sort(items, sortingOption.getComparator());
                return items;
            }
        });
    }

    public interface View extends BaseView {
        void showItems(ArrayList items);

        void showSortingOptions(CharSequence[] options);

        void clearItems();
    }
}
