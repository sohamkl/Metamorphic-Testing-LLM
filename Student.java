import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Double> grades;

    public Student(String name, List<Double> grades) {
        this.name = name;
        this.grades = new ArrayList<>(grades);
    }

    public String getName() { return name; }
    public List<Double> getGrades() { return grades; }
}
