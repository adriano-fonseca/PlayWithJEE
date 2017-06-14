import java.util.Arrays;
import java.util.Scanner;

public class Prova {
	/*
	 * Crie um projeto com o seu nome. Exemplo: vetoresSeuNome.
	 * Crie uma Classe com o nome Vets100 com o main().
	 * Crie 3 arrays estaticos de tamanho 5 chamados vGeral, vMaior e vMenor 
	 */
	static int[] vGeral = new int[5];
	static int[] vMaior = new int[5];
	static int[] vMenor = new int[5];
	static int indiceVmenor = 4;
	static int indiceVmaior = 0;

	public static void main(String[] args) {


				/*Fa�a um algoritmo para ler n�meros inteiros, cada n�mero lido dever� ser inserido em vGeral.
				
				Para cada n�mero inserido em vGeral, realize a soma de todos elementos do array vGeral.
				*/
		
				vGeral = readArray(vGeral);
		
				
				
				int soma = somaArray(vGeral);
				/* Se esta soma for menor que 100 dever� ser inserido em vMenor.
				 * O �ndice de vMenor dever� iniciar em 0 e ir at� 4. 
				 * Quando o �ndice de vMenor chegar no limite este �ndice dever� iniciar em 0 novamente.
				 * (N�o apagar o conte�do de vMenor)
				 */
				
				
				//arraySelecionado recebe:
				// 1 para vMenor (menor que 100)
				// 2 para vMaior (maior ou igual a 100)
				int arraySelecionado = selecionaArray(soma);
				
				adicionaNumeroNaposicao(arraySelecionado, numero, posicao);
				
				if(soma<100){
					vMenor[0] = vGeral[vGeral.length-1];
				}
				
				/*
				Se esta soma for maior ou igual a 100 o �ltimo n�mero digitado em vGeral dever� ser inserido
				
				em vMaior. O �ndice de vMaior dever� iniciar em 4 e ir at� 0. Quando o �ndice de vMaior chegar
				
				em 0, este �ndice dever� iniciar em 4 novamente. (N�o apagar o conte�do de vMaior)*/
			
			
	//			asdas
				
				
				/*
				
				Ap�s o �ndice de vGeral chegar em 4, inicialize todo o array vGeral com 0(zeros) e o �ndice de
				
				vGeral tamb�m em 0(zero) e continue digitando.
				
				Ap�s finalizar, exporte o projeto com seu nome e anexe no blackboard.*/
		
		
				System.out.println(somaArray(vGeral));

	}
	
	
	 private static int selecionaArray(int soma) {
		if(soma < 100){
			return 1;
		}else{ 
			return 2;
		}
	}


	static int somaArray(int[] array) {
		 int soma = 0;
		 for(int i=0; i < array.length; i++) {
			 soma+=array[i];
			}
		 return soma;
	  }
	  
	 static int[] readArray(int[] array){
		  int[] arrayLido = new int[5];
	    Scanner scanner = new Scanner(System.in);
	    int tamanho = array.length;
	    System.out.println("Digite os numeros seguido de Enter");
	    for(int x=0; x< tamanho; x++){
	    	System.out.println("Numero: "+(x+1));
	      int  membros = scanner.nextInt();
	      arrayLido[x] = membros;
	    }
	    return arrayLido;
	  }
	
	
	

}
