import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Stevenjee
 */
public class GetInstructorCoursesDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();

        try {

            // start a transaction
            currentSession.beginTransaction();

            // get the instructor from db
            int theId = 4;
            Instructor tempInstructor = currentSession.get(Instructor.class, theId);

            // get courses from the instructor
            System.out.println("Instructor: " + tempInstructor);
            System.out.println("Courses: " + tempInstructor.getCourses());


            // commit the transaction
            currentSession.getTransaction().commit();

        } finally {
            currentSession.close();
            sessionFactory.close();
        }


    }

}
