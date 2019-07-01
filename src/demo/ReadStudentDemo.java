package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {
    public static void main(String[] args){

        //create session Factory
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            //use the session object to save Java object

            //create a student object
            System.out.println("creating a new Student object");
            Student tempStudent = new Student("Daffy", "Duck","daffyD@coder.scom");

            //start a transaction
            session.beginTransaction();

            //save the student
            System.out.println("Saving the student");
            System.out.println(tempStudent);
            session.save(tempStudent);

            //commit transaction
            session.getTransaction().commit();

            //find out the student's id: primary key
            System.out.println("Saved student. Generated ID: " + tempStudent.getId());

            //now get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId());

            Student myStudent = session.get(Student.class,tempStudent.getId());

            System.out.println("Get complete: " + myStudent);

            //commit the transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally{
            //close the session factory
            factory.close();
        }

    }
}
