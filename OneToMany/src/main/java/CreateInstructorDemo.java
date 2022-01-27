import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Stevenjee
 */
public class CreateInstructorDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();

        try {

            Instructor tempInstructor = new Instructor("Susan", "Public", "susan.public@gmail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com/madhu-patel", "Video Games");

            // associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            // start a transaction
            currentSession.beginTransaction();

            // save the instructor
            // Note: this will ALSO save the details object, because CascadeType.ALL
            System.out.println("Saving instructor: " + tempInstructor);
            currentSession.save(tempInstructor);


            // commit the transaction
            currentSession.getTransaction().commit();

        } finally {
            currentSession.close();
            sessionFactory.close();
        }


    }

}
