package com.company.app.test;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.company.app.Greeter;
import com.company.app.dao.DAO;
import com.company.app.dao.SchoolGroupDAO;
import com.company.app.dao.TeacherDAO;
import com.company.app.dto.SchoolGroupDTO;
import com.company.app.dto.StudentDTO;
import com.company.app.dto.TeacherDTO;
import com.company.app.model.School;
import com.company.app.model.SchoolGroup;
import com.company.app.model.Student;
import com.company.app.model.StudentSchoolGroup;
import com.company.app.model.Teacher;

@RunWith(Arquillian.class)
public class GreeterTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
            .addClass(Type.class)
            .addClass(ResultTransformer.class)
            .addClass(Greeter.class)
            .addClass(SchoolGroup.class)
            .addClass(DAO.class)
            .addClass(SchoolGroupDAO.class)
            .addClass(TeacherDAO.class)
            .addClass(Teacher.class)
            .addClass(School.class)
            .addClass(StudentSchoolGroup.class)
            .addClass(Student.class)
            .addClass(SchoolGroupDTO.class)
            .addClass(StudentDTO.class)
            .addClass(TeacherDTO.class)
            .addAsResource("test-persistence.xml","META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        
        
//        .addPackages(true, "com.company.app.dao")
    }

    @Inject
    SchoolGroupDAO schoolGroupDAO;
    
    @Inject
    Greeter greeter;

    @Test
    public void should_create_greeting() {
        Assert.assertEquals("Hello, Earthling!",greeter.createGreeting("Earthling"));
        greeter.greet(System.out, "Earthling");
    }
    
    @Test
    public  void populateDataBase(){
      School schoolA = new School();
      schoolA.setNameSchool("Tiririca");
      
      SchoolGroup group = new SchoolGroup();
      group.setNameSchoolGroup("TESTE");
      schoolGroupDAO.add(group);
      List<SchoolGroup> list = schoolGroupDAO.list();
      Assert.assertEquals(list.size(), 1);
    }
    
}