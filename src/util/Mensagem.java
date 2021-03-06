package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Mensagem implements Serializable {

	private String operacao;
	private Status status;

	Map<String, Object> params;

	public Mensagem(){
	}

	public Mensagem(String operacao){
		this.operacao = operacao;
		params = new HashMap<>();
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public Status getStatus(){
		return status;
	}

	public void setParam(String chave, Object valor){
		params.put(chave, valor);
	}

	public Object getParam(String chave){
		return params.get(chave);
	}

	public String getOperacao(){
		return operacao;
	}

	@Override
	public String toString(){
		String msg = "Operação: " + operacao;
		msg += "\nStatus: " + status;

		msg += "\nParâmetros:";
		for (String p : params.keySet()) {
			msg += "\n" + p + ": " + params.get(p);
		}

		return msg;
	}
}