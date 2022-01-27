import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Stevenjee
 */
public class CreateCourseDemo {
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

            // create some courses
            Course tempCourse1 = new Course("Air Guitar - The Ultimate Guitar");
            Course tempCourse2 = new Course("The Pinball Master class");

            // add courses to instructor
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);

            // save the courses
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
