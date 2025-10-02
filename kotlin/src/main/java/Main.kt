// Main.kt
import java.util.Scanner

// Global lists
val students = mutableListOf<Student>()
val courses = mutableListOf<Course>()
val enrollments = mutableListOf<Enrollment>()

fun main() {
    val scanner = Scanner(System.`in`)
    while (true) {
        println("\n===== Student Course Registration System =====")
        println("1. Register Student")
        println("2. Create Course")
        println("3. Enroll Student in Course")
        println("4. View Students in a Course")
        println("5. View All Students")
        println("6. View All Courses")
        println("7. Exit")
        print("Choose an option: ")

        when (scanner.nextInt()) {
            1 -> registerStudent(scanner)
            2 -> createCourse(scanner)
            3 -> enrollStudent(scanner)
            4 -> viewCourseStudents(scanner)
            5 -> viewAllStudents()
            6 -> viewAllCourses()
            7 -> {
                println("Goodbye!")
                return
            }
            else -> println("Invalid option. Try again.")
        }
    }
}

// ---------- Student Functions ----------
fun registerStudent(scanner: Scanner) {
    print("Enter Student ID: ")
    val id = scanner.next()
    print("Enter Name: ")
    val name = scanner.next()
    print("Enter Email (or press Enter to skip): ")
    val emailInput = readLine()
    val email = if (emailInput.isNullOrBlank()) null else emailInput
    print("Enter Major: ")
    val major = scanner.next()

    val student = Student(id, name, email, major)
    students.add(student)
    println("✅ Student Registered: $student")
}

fun viewAllStudents() {
    if (students.isEmpty()) {
        println("No students registered yet.")
    } else {
        println("\n--- All Students ---")
        students.forEach { println(it) }
    }
}

// ---------- Course Functions ----------
fun createCourse(scanner: Scanner) {
    print("Enter Course ID: ")
    val courseId = scanner.next()
    print("Enter Course Name: ")
    val courseName = scanner.next()
    print("Enter Credits: ")
    val credits = scanner.nextInt()

    val course = Course(courseId, courseName, credits)
    courses.add(course)
    println("✅ Course Created: $course")
}

fun viewAllCourses() {
    if (courses.isEmpty()) {
        println("No courses available.")
    } else {
        println("\n--- All Courses ---")
        courses.forEach { course ->
            val count = enrollments.count { it.courseId == course.courseId }
            println("${course.courseId} - ${course.courseName} (${course.credits} credits) | Enrolled: $count")
        }
    }
}

// ---------- Enrollment Functions ----------
fun enrollStudent(scanner: Scanner) {
    print("Enter Student ID: ")
    val studentId = scanner.next()
    print("Enter Course ID: ")
    val courseId = scanner.next()

    val student = students.find { it.id == studentId }
    val course = courses.find { it.courseId == courseId }

    if (student == null) {
        println("❌ Student not found!")
        return
    }
    if (course == null) {
        println("❌ Course not found!")
        return
    }

    val enrollment = Enrollment(studentId, courseId)
    enrollments.add(enrollment)
    println("✅ ${student.name} enrolled in ${course.courseName}")
}

fun viewCourseStudents(scanner: Scanner) {
    print("Enter Course ID: ")
    val courseId = scanner.next()
    val course = courses.find { it.courseId == courseId }

    if (course == null) {
        println("❌ Course not found!")
        return
    }

    val enrolledStudents = enrollments.filter { it.courseId == courseId }
    if (enrolledStudents.isEmpty()) {
        println("No students enrolled in ${course.courseName}.")
    } else {
        println("\n--- Students in ${course.courseName} ---")
        enrolledStudents.forEach { e ->
            val student = students.find { it.id == e.studentId }
            println(student)
        }
    }
}
