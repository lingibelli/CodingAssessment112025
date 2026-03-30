import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CodeToRefactorTest {

    @Test
    void shouldCreatePersonWithName() {
        CodeToRefactor.People person = new CodeToRefactor.People("Alan");

        assertEquals("Alan", person.getName());
        assertNotNull(person.getDob());
    }

    @Test
    void shouldGenerateCorrectNumberOfPeople() {
        CodeToRefactor.BirthingUnit unit = new CodeToRefactor.BirthingUnit();
        List<CodeToRefactor.People> people = unit.getPeople(5);
        
        assertEquals(5, people.size());
    }

    @Test
    void shouldReturnFullName() {
        CodeToRefactor.People person = new CodeToRefactor.People("Bob");
        CodeToRefactor.BirthingUnit unit = new CodeToRefactor.BirthingUnit();

        String result = unit.getMarried(person, "Smith");

        assertEquals("Bob Smith", result);
    }

    @Test
    void shouldIgnoreLastNameContainingTest() {
        CodeToRefactor.People person = new CodeToRefactor.People("James");
        CodeToRefactor.BirthingUnit unit = new CodeToRefactor.BirthingUnit();

        String result = unit.getMarried(person, "testUser");

        assertEquals("James", result);
    }

    @Test
    void shouldTruncateFullNameTo255Characters() {
        CodeToRefactor.People person = new CodeToRefactor.People("Richard");

        CodeToRefactor.BirthingUnit unit = new CodeToRefactor.BirthingUnit();

        String longLastName = "A".repeat(300);

        String nameWith255Chars = unit.getMarried(person, longLastName);

        assertEquals(255, nameWith255Chars.length());

    }
}