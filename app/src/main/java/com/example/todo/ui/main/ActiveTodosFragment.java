package com.example.todo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ActiveTodosFragment extends Fragment implements SchulfachDialogListener {

    private OnListFragmentInteractionListener mListener;
    private RecyclerView.Adapter adapter;
    private TodosViewModel todosVM;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActiveTodosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        todosVM = new ViewModelProvider(this).get(TodosViewModel.class);

        final RecyclerView listView = view.findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(layoutManager);
        adapter = new ActiveTodosAdapter(null, mListener, todosVM);
        listView.setAdapter(adapter);

        final TextView summaryView = view.findViewById(R.id.summary);

        todosVM.getTodos().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                listView.swapAdapter(new ActiveTodosAdapter(todos, mListener, todosVM), false);
                summaryView.setText(String.format("%d completed, %d left", todosVM.selectCompleted().size(), todosVM.selectIncomplete().size()));
            }
        });

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
        todosVM.addTodo(text);
    }

}
