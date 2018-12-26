package deque;

import java.util.Iterator;

public class LinkedDeque<E> implements Dequeue<E> {
    private static class Node<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public LinkedDeque() {
        initSentinels();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        if (isEmpty())
            throw new EmptyDequeException("first() invoked on empty Deque");
        return header.next.data;
    }

    @Override
    public E last() {
        if (isEmpty())
            throw new EmptyDequeException("last() invoked on empty Deque");
        return trailer.prev.data;
    }

    @Override
    public void pushBack(E e) {
        addBetween(e, trailer.prev, trailer);
    }

    @Override
    public void pushFront(E e) {
        addBetween(e, header, header.next);
    }

    @Override
    public E popBack() {
        if (isEmpty())
            throw new EmptyDequeException("popBack() invoked on empty Deque");
        return removeNode(trailer.prev);
    }

    @Override
    public E popFront() {
        if (isEmpty())
            throw new EmptyDequeException("popFront() invoked on empty Deque");
        return removeNode(header.next);
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedDequeIterator();
    }

    private void initSentinels() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.next = trailer;
    }

    private void addBetween(E newElement, Node<E> prev, Node<E> next) {
        Node<E> newNode = new Node<>(newElement, prev, next);
        prev.next = newNode;
        next.prev = newNode;
        ++size;
    }

    private E removeNode(Node<E> node) {
        Node<E> predecessor = node.prev;
        Node<E> successor = node.next;
        node.next = null;
        node.prev = null;
        predecessor.next = successor;
        successor.prev = predecessor;
        --size;
        return node.data;
    }

    private class LinkedDequeIterator implements Iterator<E> {
        Node<E> current = header;

        @Override
        public boolean hasNext() {
            return current != trailer.prev;
        }

        @Override
        public E next() {
            E toReturn = current.next.data;
            current = current.next;
            return toReturn;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder().append('[');
        LinkedDequeIterator iter = new LinkedDequeIterator();
        while (iter.hasNext()) {
            s.append(iter.next());
            if (iter.hasNext())
                s.append(", ");
        }
        s.append(']');
        return s.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime;
        for (E e : this) {
            result += e.hashCode();
            result *= prime;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o.getClass().equals(LinkedDeque.class)) {
            LinkedDeque<E> other = (LinkedDeque<E>) o;

            if (other.size() != this.size())
                return false;

            LinkedDequeIterator otherIterator = (LinkedDequeIterator) other.iterator();
            LinkedDequeIterator thisIterator = (LinkedDequeIterator) this.iterator();

            while (thisIterator.hasNext())
                if (!otherIterator.next().equals(thisIterator.next()))
                    return false;
            return true;
        }
        return false;
    }

    @Override
    public Object clone() {
        throw new UnsupportedOperationException("clone() deprecated");
    }
}
