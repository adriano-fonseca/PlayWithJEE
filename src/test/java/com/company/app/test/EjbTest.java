package com.company.app.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.company.app.TestSystemProperties;
import com.company.app.dao.SchoolGroupDAO;
import com.company.app.dao.StudentDAO;
import com.company.app.exception.RecordNotFoundException;
import com.company.app.model.Student;

@RunWith(Arquillian.class)
public class EjbTest {

  @Deployment
  public static WebArchive createDeployment() {
    return ShrinkWrap.create(WebArchive.class).addClass(Type.class).addClass(ResultTransformer.class).addClass(TestSystemProperties.class).addPackages(true, "org.apache.commons.lang3").addPackages(true, "com.company.app.dao").addPackages(true, "com.company.app.model").addPackages(true, "com.company.app.dto").addPackages(true, "com.company.app.infra").addPackages(true, "com.company.app.exception")
    .addAsResource("h2-test-persistence.xml", "META-INF/persistence.xml");
  }

 // Creating a .war from nothing starting from package of the project
//  @Deployment 
//  public static WebArchive createDeployment3() {
//    MavenResolverSystem resolver = Maven.resolver();
//    WebArchive ejbWar = ShrinkWrap.create(WebArchive.class, "ise-rnbd.war").addPackages(true, "com.procergs.ise");
//    ejbWar.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.procergs.acr:PRArqjava-core").withTransitivity().asFile());
//    ejbWar.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.procergs.acr:PRAutentica").withTransitivity().asFile());
//    ejbWar.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.procergs.acr:PRSoeweb").withTransitivity().asFile());
//    ejbWar.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.procergs.acr:PRUtil_j2ee").withTransitivity().asFile());
//    ejbWar.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("net.sf.ehcache:ehcache-core:1.7.2").withTransitivity().asFile());
//    ejbWar.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("org.jdom:jdom:1.1.3").withTransitivity().asFile());
//    ejbWar.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("org.springframework:spring-orm:2.5.1").withoutTransitivity().asFile());
//
////    ejbWar.deleteClasses(SituacaoTurmaNaInclusaoTest.class, DiarioClasseTest.class, GeraNotificacaoBaseTest.class, EjbTest.class);
//    ejbWar.addPackages(true, "org.joda.time");
//    ejbWar.addPackages(true, "net.sf.jpacriteria");
//    ejbWar.addPackages(true, "com.procergs.arqjava.auditoria");
////    ejbWar.addClass(JSFUtil.class);
//
//    ejbWar.addAsWebInfResource("test-web.xml", "web.xml").addAsResource("SecureChain.xml", "SecureChain.xml").addAsResource("SecureChain.xml", "com/procergs/ise/estabelecimento/SecureChain.xml").addAsResource("SecureChain.xml", "com/procergs/ise/alunocurso/SecureChain.xml").addAsResource("SecureChain.xml", "com/procergs/ise/ws/firesc/SecureChain.xml").addAsWebInfResource("test-faces-config.xml", "faces-config.xml")
//        .addAsWebInfResource("jboss-deployment-structure.xml", "META-INF/jboss-deployment-structure.xml");
//    ejbWar.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
//
//    .addClass(EjbTest.class).addPackages(true, "com.procergs.util.autentica");
//    ejbWar.addAsResource("h2-test-persistence.xml", "META-INF/persistence.xml");
//    System.out.println(ejbWar.toString(true));
//    return ejbWar;
//  }

  //  Create deploy from complete .ear of application
  //  @Deployment
  //  public static EnterpriseArchive createDeployment() {
  //    MavenResolverSystem resolver = Maven.resolver();
  //    EnterpriseArchive ear = ShrinkWrap.create(ZipImporter.class, "ise.ear").importFrom(new File("target/ise.ear")).as(EnterpriseArchive.class);
  //    JavaArchive jarModificado = ear.getAsType(JavaArchive.class, "ise-rnbd.jar").addAsResource("h2-test-persistence.xml", "META-INF/persistence.xml");
  //    ear.delete("ise-rnbd.jar");
  //    ear.addAsModule(jarModificado);
  //    ear.addAsResource("jboss-deployment-structure.xml", "META-INF/jboss-deployment-structure.xml");
  //    WebArchive warModificado = ear.getAsType(WebArchive.class, "ise-web.war");
  //    warModificado.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
  //    .addClass(EjbTest.class).addPackages(true, "com.procergs.util.autentica");
  //    warModificado.deletePackages(true, "com.procergs.ise");
  //    warModificado.deletePackages(true, "com.procergs.ise.mb");
  //    warModificado.deletePackages(true, "com.procergs.ise.converter");
  //    warModificado.deletePackages(true, "com.procergs.ise.filter");
  //    warModificado.addAsWebInfResource("test-web.xml", "web.xml")
  //    .addAsWebInfResource("test-faces-config.xml", "faces-config.xml");
  //    ear.delete("ise-web.war");
  //    ear.addAsModule(warModificado);
  //    ear.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.procergs.acr:PRArqjava-core").withTransitivity().asFile());
  //    ear.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.procergs.acr:PRAutentica").withTransitivity().asFile());
  //    ear.addAsLibraries(resolver.loadPomFromFile("pom.xml").resolve("com.procergs.acr:PRSoeweb").withTransitivity().asFile());
  //    
  //
  //    System.out.println(ear.toString(true));
  //    return ear;
  //  }

  private static final Logger LOGGER = Logger.getLogger(EjbTest.class);

  @EJB
  SchoolGroupDAO              schoolGroupDAO;

  @EJB
  StudentDAO                  studentDAO;

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

  @Before
  public void cleanStudentData() {
    System.out.println("always before a test method");
  }

  @Test
  public void deveAcessarSystemProperty() {
    assertEquals("teste", System.getProperty("property.test"));
  }

  @Test
  public void shouldFailStudentNotPersisted() {
    Student student = new Student();
    student.setName("Adriano Fonseca");
    Calendar birthDay = Calendar.getInstance();
    birthDay.set(1986, 6, 23); //23/07/1986
    student.setBirthDay(birthDay);
    studentDAO.add(student);

    Student student2 = new Student();
    student.setName("Suelen Torres");
    Calendar birthDay2 = Calendar.getInstance();
    birthDay2.set(1986, 6, 04); //23/07/1986
    student.setBirthDay(birthDay2);
    studentDAO.add(student2);

    List<Student> list = studentDAO.list(new Student());
    Assert.assertEquals(list.size(), 2);

  }

  @Test(expected = RecordNotFoundException.class)
  public void shouldFailIfStudentWasFaound() {
    Student student = new Student();
    student.setIdStudent(1000L);
    //      student.setNameStudent("Adriano da Silva Fonseca");
    student = studentDAO.find(student);
  }

  @Test
  public void shouldFailStudentNotRemoved() {
    List<Student> list = studentDAO.list(new Student());
    Iterator<Student> it = list.iterator();

    while (it.hasNext()) {
      Student student = it.next();
      studentDAO.remove(student);
    }
    List<Student> list2 = studentDAO.list(new Student());
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