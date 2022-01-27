import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Stevenjee
 */
public class FetchJoinDemo {
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

            // option 2: Hibernate query with HQL
            Query<Instructor> query =
                    currentSession.createQuery("select i from Instructor i" + " JOIN FETCH i.courses" + " where i.id=:theId", Instructor.class);

            // set parameter on query
            query.setParameter("theId", theId);

            // execute query and get instructor
            Instructor tempInstructor = query.getSingleResult();

            // close session
            currentSession.close();
            System.out.println("The session is now closed!\n");

            // get courses from the instructor
            System.out.println("Debug: Courses: " + tempInstructor.getCourses());

            // commit the transaction
            currentSession.getTransaction().commit();

            System.out.println("Debug: Done!");

        } finally {
            currentSession.close();
            sessionFactory.close();
        }


    }

}
