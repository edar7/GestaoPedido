package br.com.segurossura.api.util;

public class Excecao extends RuntimeException{
	
	Excecao(String mensagem)
    {
        super(mensagem);
    }

    public static void Validar(Boolean validacao, String mensagemDeErro){
        if(validacao)
            throw new Excecao(mensagemDeErro);
    }

}
