import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;
    private String fileName;

    public StudentManagementSystem(String fileName) {
        this.fileName = fileName;
        this.students = readFromFile();
    }

    public void addStudent(Student student) {
        students.add(student);
        writeToFile();
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
        writeToFile();
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void writeToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Student> readFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ArrayList<Student>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}

public class SMS {
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem("students.dat");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume newline character
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();
                    system.addStudent(new Student(name, rollNumber, grade));
                    break;
                case 2:
                    System.out.print("Enter roll number to remove: ");
                    int removeRollNumber = scanner.nextInt();
                    system.removeStudent(removeRollNumber);
                    break;
                case 3:
                    System.out.print("Enter roll number to search: ");
                    int searchRollNumber = scanner.nextInt();
                    scanner.nextLine();  // Consume newline character
                    Student student = system.searchStudent(searchRollNumber);
                    if (student != null) {
                        System.out.println("Student found: " + student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    system.displayAllStudents();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
