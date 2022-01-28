import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Stevenjee
 */
public class GetCoursesForMaryDemo {
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
            int theStudentId = 10;
            Student theStudent = currentSession.get(Student.class, theStudentId);

            System.out.println("\nLoad the Student: " + theStudent.getFirstName() + " " + theStudent.getLastName());
            System.out.println("Courses: " + theStudent.getCourses());


            // commit the transaction
            currentSession.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            currentSession.close();
            sessionFactory.close();
        }


    }

}
