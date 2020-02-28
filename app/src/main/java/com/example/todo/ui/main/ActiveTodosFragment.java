package com.example.todo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.ui.main.TodoList.Todo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ActiveTodosFragment extends Fragment implements SchulfachDialogListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView.Adapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActiveTodosFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ActiveTodosFragment newInstance(int columnCount) {
        ActiveTodosFragment fragment = new ActiveTodosFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    public void showAddDialog() {
        // Create an instance of the dialog fragment and show it
        AddTodoDialog dialog = new AddTodoDialog();
        dialog.setTargetFragment(ActiveTodosFragment.this, 0);
        dialog.show(requireActivity().getSupportFragmentManager(), "AddTodoDialogFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_active_todos, container, false);

        FloatingActionButton addBtn = view.findViewById(R.id.add_todo_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });

        RecyclerView listView = view.findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(layoutManager);
        adapter = new ActiveTodosAdapter(TodoList.getItems(), mListener);
        listView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Todo item);
    }

    @Override
    public void applyTexts(String text) {
        TodoList.addItem(TodoList.createDummyItem(text));
        adapter.notifyDataSetChanged();
    }

}
