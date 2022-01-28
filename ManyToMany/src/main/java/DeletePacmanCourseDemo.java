import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Stevenjee
 */
public class DeletePacmanCourseDemo {
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

            // get the pacman course from db
            int theCourseId = 10;
            Course tempCourse = currentSession.get(Course.class, theCourseId);

            //delete the course
            System.out.println("Deleting course: " + tempCourse.getTitle());
            currentSession.delete(tempCourse);



            // commit the transaction
            currentSession.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            currentSession.close();
            sessionFactory.close();
        }


    }

}
