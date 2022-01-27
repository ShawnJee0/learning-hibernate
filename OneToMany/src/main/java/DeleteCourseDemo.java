import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Stevenjee
 */
public class DeleteCourseDemo {
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

            // get a course
            int theId = 10;
            Course tempCourse = currentSession.get(Course.class, theId);

            // delete the course
            System.out.println("Deleting Course: " + tempCourse);
            currentSession.delete(tempCourse);

            // commit the transaction
            currentSession.getTransaction().commit();

        } finally {
            currentSession.close();
            sessionFactory.close();
        }


    }

}
