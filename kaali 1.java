import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return name + ": " + grade;
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<>();

        System.out.print("Enter the number of students: ");
        int num = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Input student data
        for (int i = 0; i < num; i++) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter grade for " + name + ": ");
            int grade = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            studentList.add(new Student(name, grade));
        }

        // Calculate average, highest, and lowest
        int total = 0, highest = Integer.MIN_VALUE, lowest = Integer.MAX_VALUE;
        String highestStudent = "", lowestStudent = "";

        for (Student s : studentList) {
            total += s.grade;
            if (s.grade > highest) {
                highest = s.grade;
                highestStudent = s.name;
            }
            if (s.grade < lowest) {
                lowest = s.grade;
                lowestStudent = s.name;
            }
        }

        double average = (double) total / studentList.size();

        // Display report
        System.out.println("\n===== Student Grade Summary =====");
        for (Student s : studentList) {
            System.out.println(s);
        }
        System.out.println("----------------------------------");
        System.out.printf("Average Grade: %.2f%n", average);
        System.out.println("Highest Grade: " + highest + " (" + highestStudent + ")");
        System.out.println("Lowest Grade: " + lowest + " (" + lowestStudent + ")");

        // Debug information
        System.out.println("\n[DEBUG] Number of students: " + num);
        System.out.println("[DEBUG] Student list: " + studentList);
        System.out.println("[DEBUG] Total grades: " + total);
        System.out.printf("[DEBUG] Average: %.2f%n", average);
        System.out.println("[DEBUG] Highest: " + highest + " (" + highestStudent + ")");
        System.out.println("[DEBUG] Lowest: " + lowest + " (" + lowestStudent + ")");

        scanner.close();
    }
}
