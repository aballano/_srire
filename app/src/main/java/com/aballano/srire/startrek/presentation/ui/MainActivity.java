package com.aballano.srire.startrek.presentation.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.aballano.srire.R;
import com.aballano.di.ApplicationComponent;
import com.aballano.di.InjectableActivity;
import com.aballano.srire.startrek.domain.model.CrewMember;
import com.aballano.srire.startrek.presentation.presenter.MainPresenter;
import com.aballano.srire.startrek.presentation.presenter.MainPresenter.View;
import com.aballano.srire.startrek.presentation.renderer.CrewMemberRenderer;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends InjectableActivity implements View {

    RecyclerView recyclerView;

    @Inject
    MainPresenter presenter;

    private RendererAdapter<Object> adapter;

    @Override
    public void onInject(ApplicationComponent graph) {
        graph.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = RendererBuilder.create()
              .bind(CrewMember.class, new CrewMemberRenderer())
              .build()
              .into(recyclerView);

        presenter.bind(this);
    }

    @Override
    public void showItems(ArrayList items) {
        adapter.addAllAndNotify(items);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sorting_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sorting_menu:
                presenter.onSortingMenuClicked();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSortingOptions(CharSequence[] options) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a color");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onSortingItemClicked(which);
            }
        });
        builder.show();
    }

    @Override
    public void clearItems() {
        adapter.clearAndNotify();
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }
}
