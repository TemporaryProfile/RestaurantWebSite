package deque;

public interface Dequeue<E> extends Iterable<E> {
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    E first();
    E last();

    void pushBack(E e);
    void pushFront(E e);

    E popBack();
    E popFront();

    class EmptyDequeException extends RuntimeException {
        public EmptyDequeException(String message) {
            super(message);
        }
    }
}
