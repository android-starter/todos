package com.example.todo.ui.main.dummy;

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
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    private static List<DummyItem> items = new LinkedList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    public static void addItemAt(DummyItem item, int position) {
        items.add(position, item);
        ITEM_MAP.put(String.valueOf(position), item);
    }

    public static void addItem(DummyItem item) {
        items.add(item);
        ITEM_MAP.put(String.valueOf(items.size()), item);
    }

    public static void removeItem(DummyItem item) {
        items.remove(item);
        ITEM_MAP.remove(item.id);
    }

    public static void removeItemAt(int position) {
        items.remove(position);
        ITEM_MAP.remove(String.valueOf(position));
    }

    public static List<DummyItem> getItems() {
        return items;
    }

    public int size() {
        return items.size();
    }


    public static DummyItem createDummyItem(String todo) {
        int position = items.size();
        return new DummyItem(String.valueOf(position), todo, makeDetails(position));
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
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
