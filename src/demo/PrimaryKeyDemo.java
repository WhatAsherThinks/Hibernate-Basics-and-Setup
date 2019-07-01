package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args){
        //create session Factory
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            //use the session to save the java object

            //create 3 student objects
            System.out.println("Creating 3 Student objects");
            Student tempStudent1 = new Student("John","Doe","JohnD@coder.com");
            Student tempStudent2 = new Student("Mary","Jane","MaryJ@coder.com");
            Student tempStudent3 = new Student("James","Smith","JamesS@coder.com");

            //start a transaction
            session.beginTransaction();

            //save the student
            System.out.println("Saving the Students...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            //Commit Transaction
            session.getTransaction().commit();
            System.out.println("Done!!");

        }finally{
            //close the session Factory
            factory.close();

        }
    }
}
