package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args){

        //create session Factory
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            //use the session object to save Java object

            int studentID = 1;

            //now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + studentID);

            Student myStudent = session.get(Student.class, studentID);

            System.out.println("Updating Student");
            myStudent.setFirstName("Scooby");

            //commit the transaction
            session.getTransaction().commit();


            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email for all students
            System.out.println("Updating email for all students");

            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally{
            //close the session factory
            factory.close();
        }

    }
}
