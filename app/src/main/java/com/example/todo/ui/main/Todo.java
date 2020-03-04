package com.example.todo.ui.main;

public class Todo {
    public int id;
    public  String content;
    public boolean done;

    public Todo(String content) {
        this.content = content;
        this.done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return content;
    }
}
