package org.example;

import com.google.gson.Gson;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonJsonTest {

    @Test
    void shouldBeEqualAfterJsonRoundTrip() {
        Person original = new Person("Сахнюк", "Юля", 19);
        Gson gson = new Gson();

        String json = gson.toJson(original);
        Person restored = gson.fromJson(json, Person.class);

        assertNotSame(original, restored);
        assertEquals(original, restored);
        assertEquals(original.hashCode(), restored.hashCode());
    }

    @Test
    void shouldNotBeEqualForDifferentFields() {
        assertNotEquals(new Person("A", "B", 1), new Person("A", "B", 2));
        assertNotEquals(new Person("A", "B", 1), new Person("A", "C", 1));
        assertNotEquals(new Person("A", "B", 1), new Person("C", "B", 1));
    }

    @Test
    void equalsShouldFollowContract() {
        EqualsVerifier.forClass(Person.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }
}
