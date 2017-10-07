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
import android.widget.EditText;
import android.widget.Toast;

public class TaskDetailFragment extends Fragment {
    public static final String POSITION_KEY = "positionKey";
    private FloatingActionButton fabSave;
    private EditText etTitle, etDescription;
    private TaskDetailListListener listener;

    public interface TaskDetailListListener {
        void onSaveFabClick();
    }

    public static TaskDetailFragment getInstance() {
        return new TaskDetailFragment();
    }

    public static TaskDetailFragment getInstance(int position) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        Bundle args = new Bundle();
        args.putInt(POSITION_KEY, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TaskDetailListListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        etTitle = view.findViewById(R.id.etTitle);
        etDescription = view.findViewById(R.id.etDescription);
        fabSave = view.findViewById(R.id.fabSave);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int position = -1;
        if (getArguments() != null) {
            position = getArguments().getInt(POSITION_KEY, -1);
            Task task = TaskLab.getInstance().getTaskList().get(position);
            etTitle.setText(task.getTitle());
            etDescription.setText(task.getDescription());
            fabSave.setImageResource(R.drawable.ic_edit);

        }
        final int finalPosition = position;
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = !etTitle.getText().toString().isEmpty() ? etTitle.getText().toString() : getString(R.string.new_todo);
                if (getArguments() == null) {
                    TaskLab.getInstance().getTaskList().add(new Task(title, etDescription.getText().toString()));
                    etTitle.setText("");
                    etDescription.setText("");
                } else {
                    TaskLab.getInstance().getTaskList().set(finalPosition, new Task(title, etDescription.getText().toString()));
                }
                listener.onSaveFabClick();
            }
        });
    }
}
