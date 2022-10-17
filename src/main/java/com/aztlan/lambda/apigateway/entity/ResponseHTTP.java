package com.aztlan.lambda.apigateway.entity;

import java.util.Map;

/*
    Response class for an API Gateway proxied lambda
    Must conform to these three properties as per AWS docs
    https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-set-up-simple-proxy.html#api-gateway-simple-proxy-for-lambda-output-format
    Headers map will be merged with whichever header AWS sets, e.g. X-Amzn-Trace-Id
 */

public class ResponseHTTP {

    private int statusCode;
    private Map<String, String> headers;
    private Map<String, Object> body;


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}

	public ResponseHTTP(int statusCode, Map<String, String> headers, Map<String, Object> body) {
		super();
		this.statusCode = statusCode;
		this.headers = headers;
		this.body = body;
	}

}
