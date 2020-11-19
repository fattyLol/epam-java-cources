package com.epam.university.java.core.task060;

public class NodeImpl implements Node {

    private int key;
    private String value;
    private Node prev;
    private Node next;

    /**
     * Constructor for cache node.
     *
     * @param key   key of the node
     * @param value value of the node
     */
    public NodeImpl(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public Node getPrev() {
        return prev;
    }

    @Override
    public void setPrev(Node prev) {
        this.prev = prev;
    }

    @Override
    public Node getNext() {
        return next;
    }

    @Override
    public void setNext(Node next) {
        this.next = next;
    }
}
