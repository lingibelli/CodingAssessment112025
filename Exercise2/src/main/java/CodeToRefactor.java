import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.time.Duration;

public class CodeToRefactor {
    
    public static class Person {
        private static final OffsetDateTime Under16 = OffsetDateTime.now().minusYears(15);
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

        public PeoplePool() {
            people = new ArrayList<>();
        }

        /**
         * GetPeoples
         * @param j
         * @return List<Object>
         */
        public List<Person> getPeople(int i)
        {
            List<Person> people = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                try 
                {
                    // Creates a dandon Name
                    String name = "";
                    Random random = new Random();
                    if (random.nextInt(2) == 0) {
                        name = "Bob";
                    } else { name = "Betty"; }
                    // Adds new person to the list
                    people.add(new Person(name, LocalDateTime.now().minusDays(random.nextInt(18, 85) * 365)));
                } 
                catch (Exception e)
                {
                    // Dont think this should ever happen
                    throw new RuntimeException("Something failed in user creation",e);
                }
            }
            return people;
        }

        private List<Person> getBobs(boolean olderThan30) {
            OffsetDateTime threshold = OffsetDateTime.now().minusDays(30 * 365);
            return olderThan30 ? people.stream().filter(x -> x.getName().equals("Bob") && !x.getDob().isBefore(threshold)).collect(Collectors.toList()) : people.stream().filter(x -> x.getName().equals("Bob")).collect(Collectors.toList());
        }

        public String getMarried(Person p, String lastName)
        {
            String fullName = p.getName() + " " + lastName;
            if (lastName.contains("test"))
                return p.getName();
            if (fullName.length() > 255) {
                fullName = (p.getName() + " " + lastName).substring(0, 255);
            }
            return fullName;
        }
    }
}
