import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

class CodeToRefactorTest {

    @Test
    void shouldCreatePersonWithName() {
        CodeToRefactor.Person person = new CodeToRefactor.Person("Alan");

        assertEquals("Alan", person.getName());
        assertNotNull(person.getDob());
    }

    @Test
    void shouldGenerateCorrectNumberOfPeople() {
        CodeToRefactor.PeoplePool unit = new CodeToRefactor.PeoplePool();
        List<CodeToRefactor.Person> people = unit.createPeople(5);
        
        assertEquals(5, people.size());
    }

    @Test
    void shouldReturnFullName() {
        CodeToRefactor.Person person = new CodeToRefactor.Person("Bob");
        CodeToRefactor.PeoplePool unit = new CodeToRefactor.PeoplePool();

        String result = unit.formatPersonFullName(person, "Smith");

        assertEquals("Bob Smith", result);
    }

    @Test
    void shouldIgnoreLastNameContainingTest() {
        CodeToRefactor.Person person = new CodeToRefactor.Person("James");
        CodeToRefactor.PeoplePool unit = new CodeToRefactor.PeoplePool();

        String result = unit.formatPersonFullName(person, "testUser");

        assertEquals("James", result);
    }

    @Test
    void shouldTruncateFullNameTo255Characters() {
        CodeToRefactor.Person person = new CodeToRefactor.Person("Richard");

        CodeToRefactor.PeoplePool unit = new CodeToRefactor.PeoplePool();

        String longLastName = "A".repeat(300);

        String nameWith255Chars = unit.formatPersonFullName(person, longLastName);

        assertEquals(255, nameWith255Chars.length());

    }

    @Test
    void shouldCreateFreshPeopleListEveryCall() {
        CodeToRefactor.PeoplePool unit = new CodeToRefactor.PeoplePool();

        List<CodeToRefactor.Person> firstGroup = unit.createPeople(3);
        List<CodeToRefactor.Person> secondGroup = unit.createPeople(3);

        assertEquals(3, firstGroup.size());
        assertEquals(3, secondGroup.size());
    }

    @Test
    void shouldReturnAllBobs() {

        CodeToRefactor.PeoplePool peoplePool = new CodeToRefactor.PeoplePool();
       
        peoplePool.addPerson(new CodeToRefactor.Person("Bob", LocalDateTime.now().minusYears(25)));
        peoplePool.addPerson(new CodeToRefactor.Person("Bob", LocalDateTime.now().minusYears(40)));
        peoplePool.addPerson(new CodeToRefactor.Person("Alice", LocalDateTime.now().minusYears(50)));

        List<CodeToRefactor.Person> result = peoplePool.getPeopleByFirstnameWithAge30Filter("Bob", false);

        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnBobsOlderThan30() {

        CodeToRefactor.PeoplePool peoplePool = new CodeToRefactor.PeoplePool();
       
        peoplePool.addPerson(new CodeToRefactor.Person("Bob", LocalDateTime.now().minusYears(25)));
        peoplePool.addPerson(new CodeToRefactor.Person("Bob", LocalDateTime.now().minusYears(40)));
        peoplePool.addPerson(new CodeToRefactor.Person("Alice", LocalDateTime.now().minusYears(50)));

        List<CodeToRefactor.Person> result = peoplePool.getPeopleByFirstnameWithAge30Filter("Bob", true);

        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnEmptyWhenNoMatch() {

        CodeToRefactor.PeoplePool peoplePool = new CodeToRefactor.PeoplePool();
       
        peoplePool.addPerson(new CodeToRefactor.Person("Bob", LocalDateTime.now().minusYears(25)));
        peoplePool.addPerson(new CodeToRefactor.Person("Bob", LocalDateTime.now().minusYears(40)));
        peoplePool.addPerson(new CodeToRefactor.Person("Alice", LocalDateTime.now().minusYears(50)));
        peoplePool.addPerson(new CodeToRefactor.Person("James", LocalDateTime.now().minusYears(50)));
        peoplePool.addPerson(new CodeToRefactor.Person("Colin", LocalDateTime.now().minusYears(50)));

        List<CodeToRefactor.Person> result = peoplePool.getPeopleByFirstnameWithAge30Filter("Rob", true);

        assertEquals(0, result.size());
    }
}