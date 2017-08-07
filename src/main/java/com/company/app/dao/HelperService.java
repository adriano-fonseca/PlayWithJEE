package com.company.app.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.company.app.exception.DAOException;
import com.company.app.model.Teacher;


@Stateless
public class HelperService {
	private static final Logger LOGGER = Logger.getLogger(HelperService.class); 
	

	public void validateUser(String identificacao,String senha){
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
}
