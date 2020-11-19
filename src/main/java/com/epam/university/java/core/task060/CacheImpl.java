package com.epam.university.java.core.task060;

import java.util.ArrayList;

public class CacheImpl implements Cache {

    private ArrayList<Node> nodes;
    private int size;

    public CacheImpl(int size) {
        this.size = size;
        nodes = new ArrayList<>(size);
    }

    @Override
    public String getNode(int key) {
        for (Node node : nodes) {
            if (node.getKey() == key) {
                return node.getValue();
            }
        }
        return "0";
    }

    @Override
    public void setNode(int key, String value) {
        for (Node node : nodes) {
            if (node.getKey() == key) {
                node.setValue(value);
                break;
            }
        }

        nodes.add(new NodeImpl(key, value));

        if (nodes.size() > size) {
            nodes.remove(0);
            nodes.trimToSize();
        }

    }

}
