package com.smkapps.fragmentsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements TaskListFragment.TaskListListener, TaskDetailFragment.TaskDetailListListener {
    private View containerDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        containerDetail = findViewById(R.id.containerTasksDetail);

        getFragmentManager().beginTransaction().replace(R.id.containerTasksList, TaskListFragment.getInstance())
                .commit();

        if (containerDetail != null) {
            getFragmentManager().beginTransaction().replace(R.id.containerTasksDetail, TaskDetailFragment.getInstance())
                    .commit();
        }
    }

    @Override
    public void onAddFabClick() {
        if (containerDetail == null) {
            getFragmentManager().beginTransaction().replace(R.id.containerTasksList, TaskDetailFragment.getInstance())
                    .addToBackStack(null)
                    .commit();
        }
        else {
            getFragmentManager().beginTransaction().replace(R.id.containerTasksDetail, TaskDetailFragment.getInstance())
                    .commit();
        }
    }

    @Override
    public void onEditItemClick(int position) {
        if (containerDetail == null) {
            getFragmentManager().beginTransaction().replace(R.id.containerTasksList, TaskDetailFragment.getInstance(position))
                    .addToBackStack(null)
                    .commit();
        }
        else {
            getFragmentManager().beginTransaction().replace(R.id.containerTasksDetail, TaskDetailFragment.getInstance(position))
                    .commit();
        }
    }
    @Override
    public void onSaveFabClick() {
        getFragmentManager().beginTransaction().replace(R.id.containerTasksList, TaskListFragment.getInstance())
                .commit();
    }
}
