package com.company.resources;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.modules.junit4.PowerMockRunner;

import com.company.app.dao.HelperService;
import com.company.app.dao.StudentDAO;
import com.company.app.dto.StudentDTO;
import com.company.app.model.Student;

@RunWith(PowerMockRunner.class)
//Static classes need to be prepar before to use 
//@PrepareForTest(UtilUsuario.class)
public class StudentUT {

  @Spy
  StudentResource  studentResource = new StudentResource();

  @Mock
  HelperService helperService;

  @Mock
  StudentDAO    studentDAO;
  
  //UtilUsuario utilUsuario;
  
  StudentDTO studentDTO;

  @Before
  public void setUp() throws Exception {
  	//studentResource.turmaRNVal = turmaRNValImpl;
  	//studentResource.turmaBD = turmaBD;
  	studentDTO = new StudentDTO();
//    turmaED.setCursoED(new CursoED());
//    turmaED.setCalendarioEstabED(new CalendarioEstabED());
//    turmaED.getCalendarioEstabED().setNroIntDuracCal(DuracaoCalendario.ANUAL.getId());
    
    doNothing().when(helperService).validateUser(any(String.class), any(String.class));
    
    //Mockando Classe estatica com powermock
    //PowerMockito.mockStatic(UtilUsuario.class);
    //PowerMockito.when(UtilUsuario.verificaPermissao(any(TipoOperacao.class), any(Integer.class))).thenReturn(true);
    
    //Mockando find chamado indiretamente por alguns metodos da RN
    doAnswer(new Answer<Student>() {
      @Override
      public Student answer(InvocationOnMock invocation) throws Throwable {
      	Student t = new Student();
        return t;
      }
    }).when(studentDAO).find(any(Student.class));
  }
  
  @Test
  public void deveFalharSeStudentNotAdriano() {
    //  create mock
//    TurmaRN turmaRN = Mockito.mock(TurmaRN.class);
  	 //Mockando find chamado indiretamente por alguns metodos da RN
    doAnswer(new Answer<Student>() {
      @Override
      public Student answer(InvocationOnMock invocation) throws Throwable {
      	Student t = new Student();
      	t.setName("Adriano");
        return t;
      }
    }).when(studentDAO).find(any(Student.class));

    // use mock in test....
    Student student = studentDAO.find(new Student());
    assertEquals(student.getName(), "Adriano");
  }
  
//  @Test
//  public void deveFalharSeCursoDiferenteDeNulo() {
//    turmaED.setCursoED(null);
//    try{
//    TurmaQuadroDTO turmaQuadro = new TurmaQuadroDTO();
//    assertEquals(false,turmaRN.isValidaHomologacao(turmaED, turmaQuadro));
//    fail();
//    }catch(RNException rne){
//      assertEquals(rne.getMessage(), "Turma ou Curso da Turma n�o definido.");
//    }
//  }
  
}
