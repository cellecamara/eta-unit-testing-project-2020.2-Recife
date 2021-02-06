package school.cesar.eta.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person person;

    class SpyPerson extends Person {
        public LocalDate getNow() {
            return LocalDate.parse("2018-11-01");
        }
    }

    @BeforeEach
    public void setup() {
        person = new Person();
    }

    @Test
    public void getName_firstNameJonLastNameSnow_jonSnow() {
        person.setName("Jon");
        person.setLastName("Snow");

        String expected = "Jon Snow";
        String actual = person.getName();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getName_firstNameJonNoLastName_jon() {
        person.setName("Jon");

        String expected = "Jon";
        String actual = person.getName();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getName_noFirstNameLastNameSnow_snow() {
        person.setLastName("Snow");

        String expected = "Snow";
        String actual = person.getName();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getName_noFirstNameNorLastName_throwsException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            person.getName();
        });
    }

    @Test
    public void isBirthdayToday_differentMonthAndDay_false() {
        LocalDate dt = LocalDate.parse("2018-11-01");
        person.setBirthday(dt);

        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthDifferentDay_false() {
        SpyPerson person = new SpyPerson();
        Assertions.assertFalse(person.isBirthdayToday());
    }

    @Test
    public void isBirthdayToday_sameMonthAndDay_true() {
        fail();
    }

    @Test
    public void addToFamily_somePerson_familyHasNewMember() {
        fail();
    }

    @Test
    public void addToFamily_somePerson_personAddedAlsoHasItsFamilyUpdated() {
        fail();
//        person.setName("Jon");
//        person.addToFamily(person);
//
    }

    @Test
    public void isFamily_nonRelativePerson_false() {
        Person nonRelative = new Person();
        Person relative = new Person();

        relative.setName("Jon");
        person.addToFamily(relative);

        assertFalse(person.isFamily(nonRelative));

    }

    @Test
    public void isFamily_relativePerson_true() {
        Person nonRelative = new Person();
        Person relative = new Person();

        person.addToFamily(relative);

        assertTrue(person.isFamily(relative));
    }
}
