package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
    public static void main(String[] args){

        //create session Factory
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            //use the session object to save Java object

            //create a student object
            System.out.println("creating a new Student object");
            Student tempStudent = new Student("Paul", "Wall","paul@coder,com");

            //start a transaction
            session.beginTransaction();

            //save the student
            System.out.println("Saving the student");
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }finally{
            //close the session factory
            factory.close();
        }

    }
}
