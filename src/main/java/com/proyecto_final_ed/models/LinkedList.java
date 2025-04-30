package com.proyecto_final_ed.models;



public class LinkedList<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void add(int index, T element) {
        checkIndexForAdd(index);
        Node<T> newNode = new Node<>(element);

        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else if (index == size) {
            add(element); 
            return;
        } else {
            Node<T> current = getNodeAt(index);
            Node<T> previous = current.prev;

            previous.next = newNode;
            newNode.prev = previous;

            newNode.next = current;
            current.prev = newNode;
        }
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        return getNodeAt(index).data;
    }

    public T remove(int index) {
        checkIndex(index);
        Node<T> toRemove = getNodeAt(index);

        if (toRemove.prev != null) {
            toRemove.prev.next = toRemove.next;
        } else {
            head = toRemove.next;
        }

        if (toRemove.next != null) {
            toRemove.next.prev = toRemove.prev;
        } else {
            tail = toRemove.prev;
        }

        size--;
        return toRemove.data;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
    
        Node<T> current = head;
        int index = 0;
    
        while (current != null) {
            a[index++] = current.data;
            current = current.next;
        }
    
        if (a.length > size) {
            a[size] = null;
        }
    
        return a;
    }
    
    public boolean remove(T element) {
        Node<T> current = head;

        while (current != null) {
            if ((current.data == null && element == null) || (current.data != null && current.data.equals(element))) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int indexOf(T element) {
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if ((current.data == null && element == null) || (current.data != null && current.data.equals(element))) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    private Node<T> getNodeAt(int index) {
        Node<T> current;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else { 
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }

        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public boolean contains(int id) {
        Node<T> current = head;

        while (current != null) {
            if (current.data != null && extractId(current.data) == id) {
                return true;
            }
            current = current.next;
        }

        return false;
    }


    private int extractId(T element) {

        try {
            return (int) element.getClass().getMethod("getId").invoke(element);
        } catch (Exception e) {
            throw new IllegalStateException("Error");
        }
    }


    public boolean removeById(int id) {
        Node<T> current = head;
    
        while (current != null) {
            if (current.data != null && extractId(current.data) == id) {

                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
    
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev; 
                }
    
                size--;
                return true; 
            }
            current = current.next;
        }
    
        return false; 
    }


    public T getById(int id) {
        Node<T> current = head;
    
        while (current != null) {
            if (current.data != null && extractId(current.data) == id) {
                return current.data; 
            }
            current = current.next;
        }
    
        return null;
    }


    
}
