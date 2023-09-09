import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CourseSchedule {
    static class Course {
        private String name;
        public Course(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        @Override
        public String toString() {
            return name;
        }
    }
    private Map<Course, List<Course>> prerequisites;
    public CourseSchedule() {
        prerequisites = new HashMap<>();
    }
    public void addCourse(Course course) {
        if (!prerequisites.containsKey(course)) {
            prerequisites.put(course, new ArrayList<>());
        }
    }
    public void addPrerequisite(Course course, Course prerequisite) {
        if (!prerequisites.containsKey(course) || !prerequisites.containsKey(prerequisite)) {
            throw new IllegalArgumentException("Both courses must exist in the schedule.");
        }
        prerequisites.get(course).add(prerequisite);
    }
    public List<Course> getPrerequisites(Course course) {
        return prerequisites.getOrDefault(course, new ArrayList<>());
    }
    public static void main(String[] args) {
        CourseSchedule schedule = new CourseSchedule();

        Course math101 = new Course("Math 101");
        Course physics101 = new Course("Physics 101");
        Course calculus = new Course("Calculus");
        Course linearAlgebra = new Course("Linear Algebra");

        schedule.addCourse(math101);
        schedule.addCourse(physics101);
        schedule.addCourse(calculus);
        schedule.addCourse(linearAlgebra);

        schedule.addPrerequisite(calculus, math101);
        schedule.addPrerequisite(calculus, linearAlgebra);
        schedule.addPrerequisite(physics101, math101);

        List<Course> prerequisitesForCalculus = schedule.getPrerequisites(calculus);
        System.out.println("Prerequisites for Calculus: " + prerequisitesForCalculus);
        }
}


