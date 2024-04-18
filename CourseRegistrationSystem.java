import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public void enrollStudent() {
        if (enrolled < capacity) {
            enrolled++;
        } else {
            System.out.println("Course is already full. Cannot enroll more students.");
        }
    }

    public void dropStudent() {
        if (enrolled > 0) {
            enrolled--;
        } else {
            System.out.println("No students enrolled in this course.");
        }
    }
}

class Student {
    private String id;
    private String name;
    private List<Course> courses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void registerCourse(Course course) {
        courses.add(course);
        course.enrollStudent();
    }

    public void dropCourse(Course course) {
        courses.remove(course);
        course.dropStudent();
    }
}

public class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;
    private Scanner scanner;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void registerStudent(Student student) {
        students.add(student);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course.getCode() + " - " + course.getTitle() + " (" + course.getEnrolled() + "/" + course.getCapacity() + ")");
            System.out.println("Description: " + course.getDescription());
            System.out.println();
        }
    }

    public Course findCourse(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void register() {
        displayCourses();
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code to register: ");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.registerCourse(course);
        System.out.println("Registration successful.");
    }

    public void drop() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = findStudent(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code to drop: ");
        String courseCode = scanner.nextLine();
        Course course = findCourse(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.dropCourse(course);
        System.out.println("Course dropped successfully.");
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Sample courses
        Course c1 = new Course("CSE101", "Introduction to Computer Science", "Basic concepts of programming", 30);
        Course c2 = new Course("MAT101", "Calculus", "Fundamental concepts of calculus", 25);
        system.addCourse(c1);
        system.addCourse(c2);

        // Sample students
        Student s1 = new Student("1001", "Alice");
        Student s2 = new Student("1002", "Bob");
        system.registerStudent(s1);
        system.registerStudent(s2);

        // Main menu
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Register for a course");
            System.out.println("2. Drop a course");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    system.register();
                    break;
                case 2:
                    system.drop();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
