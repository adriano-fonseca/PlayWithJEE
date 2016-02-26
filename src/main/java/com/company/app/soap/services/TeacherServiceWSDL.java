package com.company.app.soap.services;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.WebServiceContext;

import org.jboss.ws.api.annotation.WebContext;

import com.company.app.dao.TeacherDAO;
import com.company.app.model.Student;
import com.company.app.model.Teacher;

@Stateless(mappedName = "AlunoCursoWSRN")
@TransactionManagement(value=TransactionManagementType.CONTAINER)
@XmlSeeAlso({Student.class}) //To see this object inside of pojo
@WebService
//@HandlerChain(file="SecureChain.xml") // security handler
@WebContext(transportGuarantee = "CONFIDENTIAL", secureWSDLAccess = true)
public class TeacherServiceWSDL {

  @Resource
  WebServiceContext wsContext;
  

  @EJB
  private TeacherDAO teacherDAO;

  
  /* Este é o metodo chamado pelo web service*/
  @WebMethod(operationName="getTeacher",exclude=false)
  @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
  public List<Teacher> get() { 
    return teacherDAO.list();
  }
}
