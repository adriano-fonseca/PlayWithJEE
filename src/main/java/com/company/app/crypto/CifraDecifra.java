package com.company.app.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

import com.company.app.business.DAOException;

/**
 * 
 * @author adriano-silva - 16/11/2015
 * 
 * Classe criada para tratar a cifragem e decifragem da senha do usário.
 * Tanto serviço REST quanto o aplicativo possuem um par de chaves (chave privada e chave pública)
 * (chave privada e chave pública) [RestPrivate.key e RestPublic.key] [AppPrivate.key e AppPublic.key]
 * Esta chaves ficam armazendas no file system.
 * O serviço conhece a chave pública da App e a App conhece a chave pública do serviço;
 * A criação da chaves pode ser feito usando o método gerarChaves;
 * Funcionamento:
 * - Para Cifrar a senha utililzar a chave pública do serviço para criptografar a senha;
 * - Para recuperar a senha: - Decifrar a senha usando a chave privada do serviço
 *                           - Cifrar a senha com a chave pública do app
 *                           - O app usa a própria chave privada para decifrar a senha
 *    
 */
public class CifraDecifra {
	
	private static final Logger LOGGER = Logger.getLogger(CifraDecifra.class); 
	public static final String ALGORITHM = "RSA";
	public static final String PATH_CHAVE_PRIVADA_REST = "C:/poc/keys/RSA/RestPrivate.key";
	public static final String PATH_CHAVE_PUBLICA_REST = "C:/poc/keys/RSA/RestPublic.key";
	public static final String PATH_CHAVE_PUBLICA_APP = "C:/poc/keys/RSA/AppPublic.key";
	
	 /**
     * Gera a chave que contém um par de chave Privada e Pública usando 1025 bytes.
     * Armazena o conjunto de chaves nos arquivos private.key e public.key
     */
    public static void geraChave(String PATH_CHAVE_PRIVADA, String PATH_CHAVE_PUBLICA) {
      try {
        final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        
        keyGen.initialize(1024);
        final KeyPair key = keyGen.generateKeyPair();
   
        File chavePrivadaFile = new File(PATH_CHAVE_PRIVADA);
        File chavePublicaFile = new File(PATH_CHAVE_PUBLICA);
   
        // Cria os arquivos para armazenar a chave Privada e a chave Publica
        if (chavePrivadaFile.getParentFile() != null) {
          chavePrivadaFile.getParentFile().mkdirs();
        }
        
        chavePrivadaFile.createNewFile();
   
        if (chavePublicaFile.getParentFile() != null) {
          chavePublicaFile.getParentFile().mkdirs();
        }
        
        chavePublicaFile.createNewFile();
   
        // Salva a Chave Pública no arquivo
        ObjectOutputStream chavePublicaOS = new ObjectOutputStream(new FileOutputStream(chavePublicaFile));
        chavePublicaOS.writeObject(key.getPublic());
        chavePublicaOS.close();
   
        // Salva a Chave Privada no arquivo
        ObjectOutputStream chavePrivadaOS = new ObjectOutputStream(new FileOutputStream(chavePrivadaFile));
        chavePrivadaOS.writeObject(key.getPrivate());
        chavePrivadaOS.close();
        
      } catch (Exception e) {
			LOGGER.error("Erro na geração das chaves de criptografia: "+e.getMessage());
			throw new DAOException("Erro na geração das chaves criptografia: "+e.getMessage());
      }
   
    }
	
	public static PublicKey consultaChavePublica(final String PATH_CHAVE_PUBLICA){
		PublicKey chavePublica = null;
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PUBLICA));
			chavePublica = (PublicKey) inputStream.readObject();
			inputStream.close();
			return chavePublica;
		} catch (Exception e) {
			LOGGER.error("Erro ao consultar chave publica: "+e.getMessage());
			throw new DAOException("Erro ao consultar chave publica: "+e.getMessage());
		}
	}
	
	public static PrivateKey consultaChavePrivada(final String PATH_CHAVE_PRIVADA){
		PrivateKey chavePrivada = null;
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_CHAVE_PRIVADA));
			chavePrivada = (PrivateKey) inputStream.readObject();
			inputStream.close();
			return chavePrivada;
		} catch (Exception e) {
			LOGGER.error("Erro ao consultar chave privada: "+e.getMessage());
			throw new DAOException("Erro ao consultar chave privada: "+e.getMessage());
		}
	}
	
	 /**
     * Criptografa o texto puro usando chave pública.
     */
	public static String cifra(String texto, PublicKey chave) {
		byte[] cipherText = null;

		try {
			// Criptografa o texto puro usando a chave Púlica
			final Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, chave);
			cipherText = cipher.doFinal(texto.getBytes("UTF-8"));
		} catch (Exception e) {
			LOGGER.error("Erro ao cifrar texto: "+e.getMessage());
			throw new DAOException("Erro ao cifrar texto: "+e.getMessage());
		}
		return DatatypeConverter.printBase64Binary(cipherText);
	}
	
	   
    /**
     * Decriptografa o texto puro usando chave privada.
     * @throws UnsupportedEncodingException 
     */
    public static String decifra(String textoCifrado, PrivateKey chave) {
      byte[] textoencriptado = DatatypeConverter.parseBase64Binary(textoCifrado);
      byte[] dectyptedText = null;
      String textoDecifrado = null;
      try {
    	  // Decriptografa o texto puro usando a chave Privada
        final Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, chave);
        dectyptedText = cipher.doFinal(textoencriptado);
        textoDecifrado = new String(dectyptedText,"UTF-8");
      } catch (Exception ex) {
			LOGGER.error("Erro ao decifrar texto: "+ex.getMessage());
			throw new DAOException("Erro ao decifrar texto: "+ex.getMessage());
      }
      return textoDecifrado;
    }
}
