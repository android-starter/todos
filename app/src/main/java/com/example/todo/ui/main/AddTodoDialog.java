package com.example.todo.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.todo.R;

public class AddTodoDialog extends DialogFragment {
    EditText todoInput;
    Bundle todoDetail;
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        todoDetail = new Bundle();
        setArguments(todoDetail);

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_todo,null);

        todoInput = dialogView.findViewById(R.id.todo_input);
        if (todoInput.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getActivity().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(dialogView, InputMethodManager.SHOW_IMPLICIT);
        }

        todoInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence var1, int var2, int var3, int var4) {

            }

            @Override
            public void onTextChanged(CharSequence var1, int var2, int var3, int var4) {

            }
            @Override
            public void afterTextChanged(Editable var1) {
                todoDetail.putString("todo", var1.toString());
            }
        });


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        dialogBuilder.setView(dialogView)
                .setTitle(R.string.add)



        // Add action buttons
        .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                SchulfachDialogListener listener = (SchulfachDialogListener) getTargetFragment();
                String todo = todoInput.getText().toString();
                listener.applyTexts(todo);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });

        final AlertDialog dialog = dialogBuilder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorAccent));
            }
        });

        return dialog;
    }

}
