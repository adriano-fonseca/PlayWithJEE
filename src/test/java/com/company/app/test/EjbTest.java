package com.company.app.test;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.company.app.TestSystemProperties;
import com.company.app.dao.SchoolGroupDAO;
import com.company.app.dao.StudentDAO;
import com.company.app.exception.RecordNotFoundException;
import com.company.app.model.Book;

@RunWith(Arquillian.class)
public class EjbTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
            .addClass(Type.class)
            .addClass(ResultTransformer.class)
            .addClass(TestSystemProperties.class)
            .addPackages(true, "org.apache.commons.lang3")
            .addPackages(true, "com.company.app.dao")
            .addPackages(true, "com.company.app.model")
            .addPackages(true, "com.company.app.dto")
            .addPackages(true, "com.company.app.exception")
            //.addAsResource("test-persistence.xml","META-INF/persistence.xml")
            
            .addAsResource("h2-test-persistence.xml","META-INF/persistence.xml")

            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml"); //Enabling CDI
    }
    
    
    private static final Logger LOGGER = Logger.getLogger(EjbTest.class);  

    @EJB
    SchoolGroupDAO schoolGroupDAO;
    
    @Inject
    StudentDAO studentDAO;
    
//    @Test
//    public void shouldFailIfHasMoreTheOneGroup(){
//      School schoolA = new School();
//      schoolA.setNameSchool("Tiririca");
//      
//      SchoolGroup group = new SchoolGroup();
//      group.setNameSchoolGroup("TESTE");
//      schoolGroupDAO.add(group);
//      List<SchoolGroup> list = schoolGroupDAO.list();
//      System.out.println(list.size());
//      Assert.assertEquals(list.size(), 1);
//    }
//
    
    @Before 
    public void cleanStudentData(){
        System.out.println("always before a test method");
    }
    
    @Test
    public void deveAcessarSystemProperty(){
      assertEquals("teste", System.getProperty("property.test"));
    }
    
    @Test
    public void shouldFailStudentNotPersisted(){
      Book student = new Book();
      student.setNameStudent("Adriano Fonseca");
      Calendar birthDay = Calendar.getInstance();
      birthDay.set(1986,6,23); //23/07/1986
      student.setBirthDayStudent(birthDay);
      studentDAO.add(student);
      
      Book student2 = new Book();
      student.setNameStudent("Suelen Torres");
      Calendar birthDay2 = Calendar.getInstance();
      birthDay2.set(1986,6,04); //23/07/1986
      student.setBirthDayStudent(birthDay2);
      studentDAO.add(student2);
      
      List<Book>list = studentDAO.list();
      Assert.assertEquals(list.size(), 2);
      
    }
    
    @Test(expected = RecordNotFoundException.class)
    public void shouldFailIfStudentWasFaound(){
      Book student = new Book();
      student.setIdStudent(1000L);
//      student.setNameStudent("Adriano da Silva Fonseca");
      student = studentDAO.find(student);
    }
    
    @Test
    public void shouldFailStudentNotRemoved(){
      List<Book> list = studentDAO.list();
      Iterator<Book> it =list.iterator();
      
      while(it.hasNext()){
        Book student = it.next();
        studentDAO.remove(student);
      }
      List<Book> list2 = studentDAO.list();
      Assert.assertEquals(list2.size(), 0);
    }
    
//    @Test(expected = EJBException.class)
//    public void shouldFailIfThreIsStudentReturned(){
//      Student student = new Student();
//      student.setIdStudent(1L);
//      studentDAO.remove(student);
//      List<Student>list = studentDAO.list();
//    }
}