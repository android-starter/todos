package com.example.todo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.ui.main.ActiveTodosFragment.OnListFragmentInteractionListener;
import com.example.todo.ui.main.TodoList.Todo;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Todo} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ActiveTodosAdapter extends RecyclerView.Adapter<ActiveTodosAdapter.ViewHolder> {

    private final List<Todo> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ActiveTodosAdapter(List<Todo> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_todo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mCheckboxView.setChecked(holder.mItem.done);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final CheckBox mCheckboxView;
        public Todo mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            mCheckboxView = (CheckBox) view.findViewById(R.id.checkBox);

            mCheckboxView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItem.setDone(!mItem.done);
                    mCheckboxView.setChecked(mItem.done);
                    ActiveTodosAdapter.this.notifyDataSetChanged();
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
