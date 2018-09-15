package functionaltdd;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentRepositoryTest {

    private List<Student> studentList = Arrays.asList(
            new Student("Jane", 23),
            new Student("John", 21),
            new Student("Tom", 25)
    );

    private StudentRepository studentRepository = new StudentRepository(studentList);

    @Test
    public void whenStudentIsNotFoundThenReturnEmpty() {
        assertThat(studentRepository.findByName("Samantha"))
                .isNotPresent();
    }

    @Test
    public void whenStudentIsFoundThenReturnStudent() {
        assertThat(studentRepository.findByName("John"))
                .hasValueSatisfying(s -> {
                    assertThat(s.name).isEqualTo("John");
                    assertThat(s.age).isEqualTo(21);
                });
    }

    // learn FILTER
    @Test
    public void filterByNameReturnsCollectionFiltered() {
        List<String> names = Arrays.asList("Alex", "Paul", "Viktor", "Kobe", "Tom", "Andrea");
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        assertThat(filteredNames)
                .hasSize(2)
                .containsExactlyInAnyOrder("Alex", "Andrea");
    }

    // learn MAP
    @Test
    public void mapToUppercaseTransformsAllElementsToUppercase() {
        List<String> names = Arrays.asList("Alex", "Paul", "Viktor");
        List<String> namesUppercase = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        assertThat(namesUppercase)
                .hasSize(3)
                .containsExactly("ALEX", "PAUL", "VIKTOR");
    }

    // learn FLATMAP
    @Test
    public void gettingLetterUsedInNames() {
        List<String> names = Arrays.asList("Alex", "Paul", "Viktor");
        List<String> lettersUsed = names.stream()
                .map(String::toLowerCase)
                .flatMap(name -> Stream.of(name.split("")))
                .distinct()
                .collect(Collectors.toList());

        assertThat(lettersUsed)
                .hasSize(12)
                .containsExactly("a","l","e","x","p","u","v","i","k","t","o","r");
    }

    // learn REDUCE
    @Test
    public void countingLetterUsedInNames() {
        List<String> names = Arrays.asList("Alex", "Paul", "Viktor");
        long count = names.stream()
                .map(String::toLowerCase)
                .flatMap(name -> Stream.of(name.split("")))
                .distinct()
                .count();


        assertThat(count).isEqualTo(12);
    }


}
