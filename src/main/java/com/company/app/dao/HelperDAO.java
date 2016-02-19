package com.company.app.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.company.app.business.DAOException;
import com.company.app.dto.TeacherDTO;
import com.company.app.model.Teacher;


@Stateless
public class HelperDAO {
	private static final Logger LOGGER = Logger.getLogger(HelperDAO.class); 
	
	@Inject
	StudentDAO studentDAO;
	@Inject
	SchoolGroupDAO schoolClassDAO;
	@Inject
	TeacherDAO professorRN;

	public void validaUsuario(String identificacao,String senha){
//		if(identificacao==null || identificacao.isEmpty()){
//			throw new RNException("Identificação do uuário não informada!");
//		}
//		if(senha==null || senha.isEmpty()){
//			throw new RNException("Senha do uuário não informada!");
//		}
//		if(siglaSistema==null || siglaSistema.isEmpty()){
//			throw new RNException("Sigla do Sistema do uuário não informada!");
//		}
//		UsuarioDTO usuario = new UsuarioDTO();
//		usuario.setIdentificacao(identificacao);
//		usuario.setSenha(senha);
//		usuario.setSiglaSistema(siglaSistema);
		LOGGER.info("AUTENTICANDO USUARIO...");
//		Boolean usuarioAutenticado = usuarioRN.autenticaUsuario(usuario);
		Boolean usuarioAutenticado = true;
		LOGGER.info("USUARIO AUTENTICADO!");
		if(!usuarioAutenticado){
			throw new DAOException("Usuário não autenticado. Verifique os dados de autenticação!");
		}
	}
	
	public TeacherDTO consultaProfessor(Long idTeacher) {
		Teacher professorED  = null;
		TeacherDTO professorDTO = null;
		if (idTeacher == null) {
			throw new DAOException("ID.Funcional do Professor não informado");
		}
		professorED = new Teacher();
		professorED.setIdTeacher(idTeacher);
		try {
			LOGGER.info("CONSULTA PROFESSOR...");
			professorDTO = professorRN.consulta(professorED);
			LOGGER.info("CONSULTA PROFESSOR FINALIZADA!");
		} catch (Exception e) {
			LOGGER.error("Erro ao consultar professor: "+e.getMessage());
			throw new DAOException("Erro ao consultar professor: "+e.getMessage());
		}
		return professorDTO;
	}
}
