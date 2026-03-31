import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.time.Duration;
import java.time.Period;

public class CodeToRefactor {
    
    public static class Person {

        private static final int DEFAULT_AGE_YEARS = 15;
        
        private String name;
        private OffsetDateTime dob;

        public Person(String name)
        {
            this(name, LocalDateTime.now().minusYears(DEFAULT_AGE_YEARS));
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

        public int getAge() {
            LocalDateTime birthDate = dob.toLocalDateTime();
            LocalDateTime now = LocalDateTime.now();
            return Period.between(birthDate.toLocalDate(), now.toLocalDate()).getYears();
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
        private static final int START_OF_STRING = 0;
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

        public void addPerson(Person person) {
            people.add(person);
        }

        private List<Person> findPerson(String firstName,boolean olderThan30) {
            return people.stream()
                         .filter(p -> p.getName().equals(firstName) && (!olderThan30 || p.getAge() > THIRTY_YEARS))
                        .collect(Collectors.toList());
        }

        public List<Person> getPeopleByFirstnameWithAge30Filter(String firstName, boolean olderThan30) {
            return findPerson(firstName, olderThan30);
        }

        public String formatPersonFullName(Person person, String lastName)
        {
            String fullName = person.getName() + " " + lastName;
            if (lastName.contains("test"))
                return person.getName();
            if (fullName.length() > MAX_NAME_LENGTH) {
                fullName = (person.getName() + " " + lastName).substring(START_OF_STRING, MAX_NAME_LENGTH);
            }
            return fullName;
        }
    }
}
