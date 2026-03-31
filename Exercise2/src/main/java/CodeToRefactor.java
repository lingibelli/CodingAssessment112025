import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.time.Duration;

public class CodeToRefactor {
    
    public static class Person {
        private static final int FIFTEEN_YEARS = 15;
        private static final OffsetDateTime Under16 = OffsetDateTime.now().minusYears(FIFTEEN_YEARS);
        private String name;
        private OffsetDateTime dob;

        public Person(String name)
        {
            this(name, Under16.toLocalDateTime());
        }

        public Person(String name, LocalDateTime dob) {
            this.name = name;
            this.dob = dob.atOffset(OffsetDateTime.now().getOffset());
        }

        public String getName() 
        {
            return name;
        }

        public OffsetDateTime getDob() {
            return dob;
        }
    }

    public static class PeoplePool
    {
        /**
         * MaxItemsToRetrieve
         */
        private List<Person> people;

        private static final int MAX_NAME_LENGTH = 255;
        private static final int MIN_AGE = 18;
        private static final int MAX_AGE = 85;
        private static final int DAYS_IN_YEAR = 365;
        private static final int THIRTY_YEARS = 30;
        private static final List<String> DUMMY_FIRSTNAMES = List.of("Bob", "Betty","James","Ruby");

        public PeoplePool() {
            people = new ArrayList<>();
        }

        /**
         * GetPeoples
         * @param j
         * @return List<Object>
         */
        public List<Person> createPeople(int numberOfPeople)
        {
            List<Person> people = new ArrayList<>();
            for (int j = 0; j < numberOfPeople; j++) {
                try 
                {
                    // Creates a dandon Name
                    String name = "";
                    Random random = new Random();
                    if (random.nextInt(2) == 0) {
                        name = DUMMY_FIRSTNAMES.get(0);
                    } else { name = DUMMY_FIRSTNAMES.get(1); }
                    // Adds new person to the list
                    people.add(new Person(name, LocalDateTime.now().minusDays(random.nextInt(MIN_AGE, MAX_AGE) * DAYS_IN_YEAR)));
                } 
                catch (Exception e)
                {
                    // Dont think this should ever happen
                    throw new RuntimeException("Something failed in user creation",e);
                }
            }
            return people;
        }

        private List<Person> findBobs(boolean olderThan30) {
            OffsetDateTime threshold = OffsetDateTime.now().minusDays(THIRTY_YEARS * DAYS_IN_YEAR);
            return olderThan30 ? people.stream().filter(x -> x.getName().equals("Bob") && !x.getDob().isBefore(threshold)).collect(Collectors.toList()) : people.stream().filter(x -> x.getName().equals("Bob")).collect(Collectors.toList());
        }

        public String formatPersonFullName(Person person, String lastName)
        {
            String fullName = person.getName() + " " + lastName;
            if (lastName.contains("test"))
                return person.getName();
            if (fullName.length() > MAX_NAME_LENGTH) {
                fullName = (person.getName() + " " + lastName).substring(0, MAX_NAME_LENGTH);
            }
            return fullName;
        }
    }
}
