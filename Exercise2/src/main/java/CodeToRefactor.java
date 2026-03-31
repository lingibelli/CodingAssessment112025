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
            people.clear();
            for (int j = 0; j < numberOfPeople; j++) {  
                    people.add(createRandomPerson());
            }   
            return people;
        }

        private Person createRandomPerson() {
            Random random = new Random();
            String personName = DUMMY_FIRSTNAMES.get(random.nextInt(DUMMY_FIRSTNAMES.size()));
            int age = random.nextInt(MIN_AGE, MAX_AGE);
            LocalDateTime dob = LocalDateTime.now().minusYears(age);

            return new Person(personName, dob);
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
