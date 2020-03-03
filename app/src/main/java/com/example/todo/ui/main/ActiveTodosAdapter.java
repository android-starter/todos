package com.example.todo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.ui.main.ActiveTodosFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Todo} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ActiveTodosAdapter extends RecyclerView.Adapter<ActiveTodosAdapter.MyViewHolder> {

    private List<Todo> mValues;
    private final OnListFragmentInteractionListener mListener;
    private TodosViewModel tVM;

    public ActiveTodosAdapter(List<Todo> items, OnListFragmentInteractionListener listener, TodosViewModel tVM) {
        if (items != null) {
            mValues = items;
        }
        mListener = listener;
        this.tVM = tVM;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_todo, parent, false);
        return new MyViewHolder(view, new OnCheckboxClickedListener());
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Todo todo = mValues.get(position);
        holder.mItem = todo;
        holder.mContentView.setText(todo.content);
        holder.mCheckboxView.setChecked(todo.done);

        holder.doneChangeListener.updatePosition(position);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(todo);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mValues != null) {
            return mValues.size();
        }
       return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final CheckBox mCheckboxView;
        public Todo mItem;
        public OnCheckboxClickedListener doneChangeListener;

        public MyViewHolder(View view, OnCheckboxClickedListener cbListener) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            mCheckboxView = (CheckBox) view.findViewById(R.id.checkBox);
            mCheckboxView.setOnCheckedChangeListener(cbListener);
            doneChangeListener = cbListener;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


    private class OnCheckboxClickedListener implements CompoundButton.OnCheckedChangeListener {
        private int position;

        public OnCheckboxClickedListener() {
        }

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Todo todo = tVM.getTodo(position);
            todo.setDone(b);
            tVM.updateTodo(position, todo);
        }
    }


}
