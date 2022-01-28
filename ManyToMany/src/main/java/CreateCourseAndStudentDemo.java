import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Stevenjee
 */
public class CreateCourseAndStudentDemo {
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

            // create a course
            Course tempCourse = new Course("Pacman - How to score one million points");

            // save the course
            System.out.println("\nSaving the course ...");
            currentSession.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse.getTitle());

            // create the students
            Student tempStudent1 = new Student("John", "Doe", "john.doe@gmail.com");
            Student tempStudent2 = new Student("Mary", "Public", "mary.public@gmail.com");

            // add students to the course
            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            // save the students
            System.out.println("\nSaving the students ...");
            currentSession.save(tempStudent1);
            currentSession.save(tempStudent2);
            System.out.println("Saved students: " + tempCourse.getStudents());


            // commit the transaction
            currentSession.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            currentSession.close();
            sessionFactory.close();
        }


    }

}
