import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;

/**
 * @author Stevenjee
 */
public class AddCoursesForMaryDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();

        try {
            // start a transaction
            currentSession.beginTransaction();

            // get the student mary from database
            int theStudentId = 11;
            Student theStudent = currentSession.get(Student.class, theStudentId);

            System.out.println("\nLoad the Student: " + theStudent.getFirstName() + " " + theStudent.getLastName());
            System.out.println("Courses: " + theStudent.getCourses());

            // create more courses
            Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
            Course tempCourse2 = new Course("Atari 2600 - Game Development");

            // add student to courses
            tempCourse1.addStudent(theStudent);
            tempCourse2.addStudent(theStudent);

            // save the courses
            System.out.println("\nSaving the courses ...");

            currentSession.save(tempCourse1);
            currentSession.save(tempCourse2);

            // commit the transaction
            currentSession.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            currentSession.close();
            sessionFactory.close();
        }


    }

}
