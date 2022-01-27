import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Stevenjee
 */
public class DeleteInstructorDetailDemo {
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

            // get instructor detail object
            int theInstructorDetailId = 3;
            InstructorDetail tempInstructorDetail = currentSession.get(InstructorDetail.class, theInstructorDetailId);

            // print the instructor detail
            System.out.println("TempInstructorDetail: " + tempInstructorDetail);
            // print the associated instructor
            System.out.println("The associated instructor: " + tempInstructorDetail.getInstructor());

            // now let's delete the instructor detail
            System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);

            // remove the associated object reference
            // Note: before you delete tempInstructorDetail, should set instructor.InstructorDetail to null. break bi-directional link
            // Otherwise, you can't delete it.
            tempInstructorDetail.getInstructor().setInstructorDetail(null);
            currentSession.delete(tempInstructorDetail);

            // commit the transaction
            currentSession.getTransaction().commit();

            System.out.println("Done");

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            // handle connection leak issue
            currentSession.close();
            sessionFactory.close();
        }


    }
}
