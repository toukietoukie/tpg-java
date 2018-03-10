package assessment.question2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import assessment.question3.TimingTool;

/**
 * Question 2:
 * Rearrange student information according to GPA in descending order.
 * If students have the same GPA, arrange according to first name in alphabetical order.
 * If students also have the same first name, then sort them in ascending order according to IDs.
 * @author Ginny
 *
 */
public class StudentGPAReport {

	public static void main(String[] args) {
		
		System.out.println("StudentGPAReport start...");
		TimingTool.start();

		List<Student> students = getStudents();
		
		System.out.println("Before Sort:");
		for (Student student : students) {
			System.out.println("ID=" + student.getId() + ", Name=" + student.getName() + ", GPA=" + student.getGpa());
		}
		
		// Sort by ID asc
		Collections.sort(students, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return o1.getId() - o2.getId();
			}
		});
		
		// Sort by first name asc
		Collections.sort(students, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		// Sort by GPA desc
		Collections.sort(students, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return o2.getGpa().compareTo(o1.getGpa());
			}
		});
		
		System.out.println("After Sort:");
		for (Student student : students) {
			System.out.println("ID=" + student.getId() + ", Name=" + student.getName() + ", GPA=" + student.getGpa());
		}
		
		TimingTool.stop();
		System.out.println("StudentGPAReport completed...");
	}
	
	private static List<Student> getStudents() {
		
		List<Student> result = new ArrayList<Student>();
		result.add(new Student(33, "Tina", new BigDecimal("3.68")));
		result.add(new Student(85, "Louis", new BigDecimal("3.85")));
		result.add(new Student(56, "Samil", new BigDecimal("3.75")));
		result.add(new Student(19, "Samar", new BigDecimal("3.75")));
		result.add(new Student(22, "Lorry", new BigDecimal("3.76")));
		result.add(new Student(21, "Samil", new BigDecimal("3.75")));
		result.add(new Student(3, "Chloe", new BigDecimal("3.88")));
		result.add(new Student(1, "Chloe", new BigDecimal("3.98")));
		
		return result;
	}
}

class Student {
	public Student(int id, String name, BigDecimal gpa) {
		this.id = id;
		this.name = name;
		this.gpa = gpa;
	}
	private int id;
	private String name;
	private BigDecimal gpa;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getGpa() {
		return gpa;
	}
	
	public void setGpa(BigDecimal gpa) {
		this.gpa = gpa;
	}
}