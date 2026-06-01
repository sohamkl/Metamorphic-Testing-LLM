import java.util.List;

public class GradeUtil {
    public static double average(Student student) {
        List<Double> grades = student.getGrades();
        if (grades.isEmpty()) return 0.0;
        double sum = 0.0;
        for (double g : grades) {
            sum += g;
        }
        return sum / grades.size();
    }
}
