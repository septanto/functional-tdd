package functionaltdd;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class StudentRepository {

    private final Set<Student> studentSet;

    StudentRepository(Collection<Student> students) {
        studentSet = new HashSet<>(students);
    }

    public Optional<Student> findByName(String name) {
        for (Student student : this.studentSet) {
            if (student.name.equals(name))
                return Optional.of(student);
        }
        return Optional.empty();
    }
}
