import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CodeToRefactor {
    
    public static class People {
        private static final OffsetDateTime Under16 = OffsetDateTime.now().minusYears(15);
        private String name;
        private OffsetDateTime dob;

        public People(String name)
        {
            this(name, Under16.toLocalDateTime());
        }

        public People(String name, LocalDateTime dob) {
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

    public static class BirthingUnit
    {
        /**
         * MaxItemsToRetrieve
         */
        private List<People> people;

        public BirthingUnit() {
            people = new ArrayList<>();
        }

        /**
         * GetPeoples
         * @param j
         * @return List<Object>
         */
        public List<People> getPeople(int i)
        {
            for (int j = 0; j < i; j++) {
                try 
                {
                    // Creates a dandon Name
                    String name = "";
                    Random random = new Random();
                    if (random.nextInt(1) == 0) {
                        name = "Bob";
                    } else { name = "Betty"; }
                    // Adds new people to the list
                    people.add(new People(name, LocalDateTime.now().minusDays(random.nextInt(18, 85) * 356)));
                } 
                catch (Exception e)
                {
                    // Dont think this should ever happen
                    throw new RuntimeException("Something failed in user creation");
                }
            }
            return people;
        }

        private List<People> getBobs(boolean olderThan30) {
            return olderThan30 ? people.stream().filter(x -> x.getName().equals("Bob") && !x.getDOB().isBefore(LocalDateTime.now().minus(Duration.ofDays(30 * 356)))).collect(Collectors.toList()) : people.stream().filter(x -> x.getName().equals("Bob")).collect(Collectors.toList());
        }

        public String getMarried(People p, String lastName)
        {
            if (lastName.contains("test"))
                return p.getName();
            if ((p.getName().length() + lastName).length() > 255) {
                (p.getName() + " " + lastName).substring(0, 255);
            }

            return p.getName() + " " + lastName;
        }
    }
}
