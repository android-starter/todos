package com.example.todo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.example.todo.ui.main.AddDialogFragment;
import com.example.todo.ui.main.ItemFragment;
import com.example.todo.ui.main.SectionsPagerAdapter;
import com.example.todo.ui.main.dummy.DummyContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements AddDialogFragment.NoticeDialogListener, ItemFragment.OnListFragmentInteractionListener {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });
    }

    public void showAddDialog() {
        // Create an instance of the dialog fragment and show it
        AddDialogFragment dialog = new AddDialogFragment();
        dialog.show(getSupportFragmentManager(), "Add todo");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        String todo = dialog.getArguments().getString("todo");
        if (todo != null && todo.trim().length() > 0) {
            DummyContent.addItem(DummyContent.createDummyItem(todo));
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        // ignore
    }
}