package com.example.todo.ui.main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TodoList {

    /**
     * An array of sample (dummy) items.
     */
    private static List<Todo> items = new LinkedList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Todo> ITEM_MAP = new HashMap<String, Todo>();

    static {
        Todo exist = new  Todo("0", "Lee Hee Eun","Hit Lee Hee Eun");
        items.add(exist);
        ITEM_MAP.put("0", exist);
    }


    public static void addItemAt(Todo item, int position) {
        items.add(position, item);
        ITEM_MAP.put(String.valueOf(position), item);
    }

    public static void addItem(Todo item) {
        items.add(item);
        ITEM_MAP.put(String.valueOf(items.size()), item);
    }

    public static void removeItem(Todo item) {
        items.remove(item);
        ITEM_MAP.remove(item.id);
    }

    public static void removeItemAt(int position) {
        items.remove(position);
        ITEM_MAP.remove(String.valueOf(position));
    }

    public static List<Todo> getItems() {
        return items;
    }

    public int size() {
        return items.size();
    }


    public static Todo createDummyItem(String todo) {
        int position = items.size();
        return new Todo(String.valueOf(position), todo, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Todo {
        public final String id;
        public final String content;
        public final String details;
        public boolean done;

        public Todo(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
            this.done = false;
        }

        public void setDone(boolean done) {
            this.done = done;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
