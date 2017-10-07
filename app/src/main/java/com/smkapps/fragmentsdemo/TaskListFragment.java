package com.smkapps.fragmentsdemo;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class TaskListFragment extends Fragment {
    private RecyclerView rvTaskList;
    private FloatingActionButton fab;
    private TaskAdapter mTaskAdapter;
    private TaskListListener listener;

    public interface TaskListListener {
        void onAddFabClick();
        void onEditItemClick(int position);
    }
    public static TaskListFragment getInstance() {
        return new TaskListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TaskListListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        rvTaskList = view.findViewById(R.id.rvTasksList);
        fab = view.findViewById(R.id.fab);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTaskAdapter = new TaskAdapter(new TaskAdapter.TaskAdapterListener() {
            @Override
            public void onItemClicked(int position) {
                listener.onEditItemClick(position);
            }
        });
        rvTaskList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTaskList.setAdapter(mTaskAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAddFabClick();
            }
        });

    }
}
