package com.aztlan.lambda.email.handler;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.aztlan.lambda.apigateway.entity.EmailDTO;
import com.aztlan.lambda.apigateway.entity.Request;
import com.aztlan.lambda.apigateway.entity.ResponseHTTP;
import com.aztlan.lambda.email.util.EmaillUtil;
import com.aztlan.lambda.email.util.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class LambdaFunctionHandler implements RequestHandler<Request, ResponseHTTP> {

    @Override
    public ResponseHTTP handleRequest(Request request, Context context) {
        context.getLogger().log("Input: " + request.toString());
         
        String template = System.getenv("email_template");
        
        EmaillUtil email=new EmaillUtil();
        boolean succesSend=false;
		final Map<String, String> headers = new HashMap<>();
		final Map<String, Object> bodyResponse = new HashMap<>();
		
		EmailDTO emailDTO=request.getEmail();
		headers.put("Content-Type", "application/json");
		
		String templateConDatos=template.replace("{titulo}","Requerimiento")
				.replace("{nombre}", emailDTO.getNombre())
				.replace("{fechaYhora}", Util.getTodayDate())
				.replace("{requerimiento}", emailDTO.getRequerimiento())
				.replace("{telefono}", emailDTO.getTelefono());
		
		email.setTo(emailDTO.getCorreo());
		email.setSubject("Solicitud de Requerimiento - Grupo Dirsio");
		
		email.setMessage(templateConDatos);
		succesSend=email.sendEmail();
		
		bodyResponse.put("code", "200");
		bodyResponse.put("mensaje", succesSend?"Transaccion Exitosa":"No se pudo completar la transaccion");
		bodyResponse.put("exito", succesSend);

		return new ResponseHTTP(200, headers, bodyResponse);
    }

}
