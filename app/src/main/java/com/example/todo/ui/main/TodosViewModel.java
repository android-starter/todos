package com.example.todo.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TodosViewModel extends ViewModel {
    public MutableLiveData<List<Todo>> todos ;

    public LiveData<List<Todo>> getTodos() {
        if (todos == null) {
            todos = new MutableLiveData<List<Todo>>();
            loadTodos();
        }

        return todos;
    }

    public LiveData<List<Todo>> getCompletedTodos() {

        List<Todo> all = todos.getValue();
        List<Todo> sub = new ArrayList<Todo>();

        if (all != null) {

            Iterator<Todo> it = all.iterator();

            while(it.hasNext()) {
                Todo t = it.next();
                if (t.done) {
                    sub.add(t);
                }
            }
        }

        return new MutableLiveData<List<Todo>>(sub);
    }

    private void loadTodos() {
        // Do an asynchronous operation to fetch todos.
        List<Todo> initial = new ArrayList<>();
        initial.add(new Todo("Lee Hee Eun"));
        todos.setValue(initial);
    }

    public void addTodo(Todo todo) {
        if (todos == null) {
            todos = new MutableLiveData<List<Todo>>();
            loadTodos();
        }
        List<Todo> exists = todos.getValue();
        exists.add(todo);
        todos.setValue(exists);
    }

    public void addTodo(String content) {
        Todo todo = new Todo(content);
        List<Todo> exists = todos.getValue();
        exists.add(todo);
        todos.setValue(exists);
    }

    public Todo getTodo(int index) {
        List<Todo> exists = todos.getValue();
        return exists.get(index);
    }

    public boolean updateTodo(int index, Todo todo) {
        List<Todo> exists = todos.getValue();
        if (exists.get(index) != null) {
            exists.set(index, todo);
            todos.setValue(exists);
            System.out.println("Set item done successfully");
            return true;
        }

        return false;
    }

}
