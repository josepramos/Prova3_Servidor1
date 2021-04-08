package com.example.OTES12Exemplo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Server {

    @GetMapping("/indicadores/nivelg/{gpr1}/{gpr5}/{gpr6}/{gpr8}/{gpr9}/{req1}/{req5}/{req6}/{req7}")
    public String indicadoresG(
    					   @PathVariable(value = "gpr1") String gpr1,
    					   @PathVariable(value = "gpr5") String gpr5,
    					   @PathVariable(value = "gpr6") String gpr6,
    					   @PathVariable(value = "gpr8") String gpr8,
    					   @PathVariable(value = "gpr9") String gpr9,
    					   @PathVariable(value = "req1") String req1,
    					   @PathVariable(value = "req5") String req5,
    					   @PathVariable(value = "req6") String req6,
    					   @PathVariable(value = "req7") String req7
    					   ) {
    	
    	float nota_gpr1 = transformaNota(gpr1);
    	float nota_gpr5 = transformaNota(gpr1);
    	float nota_gpr6 = transformaNota(gpr6);
    	float nota_gpr8 = transformaNota(gpr8);
    	float nota_gpr9 = transformaNota(gpr9);
    	float nota_req1 = transformaNota(req1);
    	float nota_req5 = transformaNota(req5);
    	float nota_req6 = transformaNota(req6);
    	float nota_req7 = transformaNota(req7);
    	
    	/*
    	 	GPR1 - Execucao (Resultados)
			GPR5 - Execucao (Resultados)
			GPR6 - Pessoas
			GPR8 - Execucao
			GPR9 - Pessoas
			REQ1 - Resultados (Execucao)
			REQ5 - Pessoas
			REQ6 - Resultados
			REQ7 - Execucao (Resultados)
    	 */

    	//Calcula a nota:
    	float nota_resultados = (nota_gpr1 + nota_gpr5 + nota_req1 + nota_req6 + nota_req7)/5;
    	float nota_execucao = (nota_gpr1 + nota_gpr5 + nota_gpr8 + nota_req1 + nota_req7)/5;
    	float nota_pessoas = (nota_gpr6 + nota_gpr9 + nota_req5)/3;
    	
    	//Formata os numeros para duas casas decimais:
    	DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
    	DecimalFormat df = new DecimalFormat("00.00", dfs);
    	String nota_resultadosS = df.format(nota_resultados);
    	String nota_execucaoS = df.format(nota_execucao);
    	String nota_pessoasS = df.format(nota_pessoas);
    	
    	//Os transforma em float e atribui de volta nas variáveis do tipo float:
    	nota_resultados = Float.parseFloat(nota_resultadosS);
    	nota_execucao = Float.parseFloat(nota_execucaoS);
    	nota_pessoas = Float.parseFloat(nota_pessoasS);

    	String nota_resultados_letra = transformaNotaString(nota_resultados);
    	String nota_execucao_letra = transformaNotaString(nota_execucao);
    	String nota_pessoas_letra = transformaNotaString(nota_pessoas);
    	
    	String responseJson = "{\"resultados\": "+nota_resultados+",\"execucao\" :"+nota_execucao+",\"pessoas\": "+nota_pessoas+",\"resultados_string\": \""+nota_resultados_letra+"\",\"execucao_string\": \""+nota_execucao_letra+"\",\"pessoas_string\":\""+nota_pessoas_letra+"\"}";
    	
    	return responseJson;
    }

    public float transformaNota (String nota) {
    	float notaNumero = -1;
    	
    	switch (nota) {
    	case "T":
    		notaNumero = 10;
    		break;
    	case "t":
    		notaNumero = 10;
    		break;
    	case "L":
    		notaNumero = (float) 7.5;
    		break;
    	case "l":
    		notaNumero = (float) 7.5;
    		break;
    	case "P":
    		notaNumero = 5;
    		break;
    	case "p":
    		notaNumero = 5;
    		break;
    	case "N":
    		notaNumero = (float) 2.5;
    		break;
    	case "n":
    		notaNumero = (float) 2.5;
    		break;
    	case "NA":
    		notaNumero = 0;
    		break;
    	case "na":
    		notaNumero = 0;
    		break;   
    	}
    	
    	return notaNumero;
    }

    public String transformaNotaString (float nota) {
    	String nota_string = null;
    	
    	if(nota == 10) {
    		nota_string = "T";
    	}else {
    		if (nota < 10 && nota >= 6.6) {
    			nota_string = "L";
    		}else {
    			if(nota < 6.6 && nota > 3.3) {
    				nota_string = "P";
    			}else {
    				if(nota <= 3.3 && nota > 0) {
    					nota_string = "N";
    				} else {
    					nota_string = "NA";
    				}
    			}
    		}
    	}
    	
    	return nota_string;
    }
}
