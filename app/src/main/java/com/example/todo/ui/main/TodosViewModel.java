package com.example.todo.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TodosViewModel extends ViewModel {
    public MutableLiveData<List<Todo>> todos;
    public MutableLiveData<List<Todo>> completedTodos;
    public MutableLiveData<List<Todo>> incompleteTodos;

    public TodosViewModel() {
        List<Todo> initial = new ArrayList<>();
        initial.add(new Todo("Lee Hee Eun"));
        todos = new MutableLiveData<List<Todo>>(initial);
        completedTodos = new MutableLiveData<>(selectCompleted());
        incompleteTodos = new MutableLiveData<>(selectIncomplete());
    }


    public LiveData<List<Todo>> getTodos() {
        return todos;
    }

    public LiveData<List<Todo>> getCompletedTodos() {
        return completedTodos;
    }

    public LiveData<List<Todo>> getIncompleteTodos() {
        return incompleteTodos;
    }

    private void update(List<Todo> next) {
        todos.setValue(next);
        completedTodos.setValue(selectCompleted());
        incompleteTodos.setValue(selectIncomplete());
    }

    public List<Todo> selectCompleted() {
        List<Todo> all = todos.getValue();
        Iterator<Todo> it = all.iterator();
        List<Todo> completed = new ArrayList<Todo>();
        while (it.hasNext()) {
            Todo t = it.next();
            if (t.done) {
                completed.add(t);
            }
        }
        return completed;
    }

    public List<Todo> selectIncomplete() {
        List<Todo> all = todos.getValue();
        Iterator<Todo> it = all.iterator();
        List<Todo> completed = new ArrayList<Todo>();
        while (it.hasNext()) {
            Todo t = it.next();
            if (!t.done) {
                completed.add(t);
            }
        }
        return completed;
    }


    public void addTodo(Todo todo) {
        List<Todo> exists = todos.getValue();
        exists.add(todo);
        update(exists);
    }

    public void addTodo(String content) {
        Todo todo = new Todo(content);
        List<Todo> exists = todos.getValue();
        exists.add(todo);
        update(exists);
    }

    public Todo getTodo(int index) {
        List<Todo> exists = todos.getValue();
        return exists.get(index);
    }

    public boolean updateTodo(int index, Todo todo) {
        List<Todo> exists = todos.getValue();
        if (exists.get(index) != null) {
            exists.set(index, todo);
            update(exists);
            return true;
        }

        return false;
    }

    public boolean removeTodo(int index) {
        List<Todo> exists = todos.getValue();
        if (exists.get(index) != null) {
            exists.remove(index);
            update(exists);
            return true;
        }

        return false;
    }


}
