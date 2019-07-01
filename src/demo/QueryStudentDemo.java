package demo;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args){

        //create session Factory
        SessionFactory factory = new Configuration().configure().addAnnotatedClass(Student.class).buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            //use the session object to save Java object


            //start a transaction
            session.beginTransaction();

            //query students
            List theStudents = session.createQuery("from Student").getResultList();

            //display Students
            displayStudents(theStudents);

            //query stuents: lastname='Doe'
            theStudents= session.createQuery("from Student s where s.lastName='Doe'").getResultList();

            //display the students
            System.out.println("\n\nStudents who have last name of Doe");
            displayStudents(theStudents);

            //query students: lastName='Doe' OR firstName='Daffy'
            theStudents = (List) session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Daffy'").getResultList();

            //display the Students
            System.out.println("\n\nStudents who have last name of Doe or first name of Daffy");
            displayStudents(theStudents);

            //query students: email LIKE '%coder.com'
            theStudents = session.createQuery("from Student s where s.email LIKE '%coder.com' ").getResultList();

            //display the students
            System.out.println("\n\nStudents whose email ends with coder.com");
            displayStudents(theStudents);


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        }finally{
            //close the session factory
            factory.close();
        }

    }

    private static void displayStudents(List theStudents) {
        for(Object tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }
}
