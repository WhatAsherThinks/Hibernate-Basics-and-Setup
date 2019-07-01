package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
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

            //delete the student
           // System.out.println("Deleting student: " + myStudent);
            //session.delete(myStudent);

            //delete student id=2
            System.out.println("deleteing student id=2");
            session.createQuery("delete from Student where id=2").executeUpdate();

            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally{
            //close the session factory
            factory.close();
        }

    }
}
