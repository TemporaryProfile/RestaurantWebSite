package dequeSpecification;

import deque.*;
import deque.Dequeue.*;
import org.junit.jupiter.api.*;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedDequeSpecification {
    Dequeue<Integer> deck;
    Dequeue<Integer> anotherDeck;

    @BeforeEach
    void init() {
        deck = new LinkedDeque<>();
        anotherDeck = new LinkedDeque<>();
        Stream.of(1, 2, 3).forEach(e -> anotherDeck.pushBack(e));
    }

    @Nested
    @DisplayName("pushFront(), pushBack()")
    class AddOperations {
        @Test
        @DisplayName("contains [3, 2, 1] when consequently added 1, 2, 3 with pushFront()")
        void pushFrontAddsElementsToFront() {
            Stream.of(1, 2, 3).forEach(e -> deck.pushFront(e));
            assertEquals(deck.toString(), "[3, 2, 1]");
        }

        @Test
        @DisplayName("contains [1, 2, 3] when consequently added 1, 2, 3 with pushBack()")
        void pushBackAddsElementsToBack() {
            Stream.of(1, 2, 3).forEach(e -> deck.pushBack(e));
            assertEquals(deck.toString(), "[1, 2, 3]");
        }

        @Test
        @DisplayName("contains [2, 1, 3, 4] when consequently added 1, 2 with pushFront(), 3, 4 with pushBack()")
        void pushBackPushFrontCombination() {
            deck.pushFront(1);
            deck.pushFront(2);
            deck.pushBack(3);
            deck.pushBack(4);
            assertEquals(deck.toString(), "[2, 1, 3, 4]");
        }
    }

    @Nested
    @DisplayName("first(), last()")
    class FirstLastOperations {
        @Test
        @DisplayName("received 3 when invoked first() on [3, 2, 1]")
        void threeIsTheFirstElement() {
            Stream.of(1, 2, 3).forEach(e -> deck.pushFront(e));
            assertThat(deck.first()).isEqualTo(3);
        }

        @Test
        @DisplayName("received 3 when invoked last() on [1, 2, 3]")
        void threeIsTheLastElement() {
            Stream.of(1, 2, 3).forEach(e -> deck.pushBack(e));
            assertThat(deck.last()).isEqualTo(3);
        }

        @Test
        @DisplayName("throws EmptyDequeException() when first() invoked on empty deque")
        void firstOnEmptyDeque() {
            assertThrows(EmptyDequeException.class,
                    () -> deck.first());
        }
    }

    @Nested
    @DisplayName("equals() and hashCode()")
    class EqualsAndHashCodeMethods {
        @Test
        @DisplayName("equal Deques have equal hashCodes")
        void equivalentHashCodeForEqualDeques() {
            Stream.of(1, 2, 3).forEach(e -> deck.pushBack(e));
            assertThat(deck.hashCode()).isEqualTo(anotherDeck.hashCode());

            deck.pushFront(5);
            assertNotEquals(deck.hashCode(), anotherDeck.hashCode());
        }

        @Test
        @DisplayName("Deques with same contents are equal")
        void equalityTest() {
            Stream.of(1, 2, 3).forEach(e -> deck.pushBack(e));
            assertEquals(anotherDeck, deck);

            deck.pushFront(1);
            assertNotEquals(deck, anotherDeck);
        }
    }

    @Nested
    @DisplayName("popBack(), popFront()")
    class PopBackPopFrontOperations {
        @Test
        @DisplayName("popBack invoked three times returns 3, 2, 1 from [1, 2, 3] deque")
        void popBackTest() {
            final int upperBound = anotherDeck.last();
            final int initialSize = anotherDeck.size();

            for (int i = upperBound; i > 0; --i) {
                assertThat(anotherDeck.popBack()).isEqualTo(i);
            }
        }
    }
}
