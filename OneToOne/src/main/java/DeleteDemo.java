import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Stevenjee
 */
public class DeleteDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session currentSession = sessionFactory.getCurrentSession();

        try {
            // start a transaction
            currentSession.beginTransaction();

            // get instructor by primary key
            int theId = 1;
            Instructor tempInstructor = currentSession.get(Instructor.class, theId);

            System.out.println("Found Instructor: " + tempInstructor);

            // delete the instructors
            if(tempInstructor != null) {
                System.out.println("Deleting: " + tempInstructor);

                //Note: will also delete associated "details" object, because of CasscadeType.All
                currentSession.delete(tempInstructor);

            }

            // commit the transaction
            currentSession.getTransaction().commit();

            System.out.println("Done");

        } finally {
            currentSession.close();
            sessionFactory.close();
        }


    }
}
