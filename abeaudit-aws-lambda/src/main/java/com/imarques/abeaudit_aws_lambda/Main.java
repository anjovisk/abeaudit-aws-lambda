package com.imarques.abeaudit_aws_lambda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.handlers.RequestHandler2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.imarques.abeaudit_aws_lambda.model.PaymentAuthorization;
import com.imarques.abeaudit_aws_lambda.model.TransactionTraceability;
import com.imarques.abeaudit_aws_lambda.service.AuditService;
import com.imarques.abeaudit_aws_lambda.util.DataContainer;

public class Main extends RequestHandler2  {
	private static  AuditService auditService = new AuditService();
	
	ObjectMapper objectMapper = new ObjectMapper()
			   .registerModule(new ParameterNamesModule())
			   .registerModule(new Jdk8Module())
			   .registerModule(new JavaTimeModule());
	
	public void postTransaction(InputStream inputStream, OutputStream outputStream) throws IOException {
		TransactionTraceability result = null;
		PaymentAuthorization paymentAuthorization = objectMapper.readValue(inputStream, PaymentAuthorization.class);
		result = auditService.create(paymentAuthorization);
		objectMapper.writeValue(outputStream, result);
	}
	
	public DataContainer<TransactionTraceability> getTransactions(TransactionTraceability parameters, int limit, int offset) {
		DataContainer<TransactionTraceability> result = auditService.find(parameters, limit, offset);
		return result;
	}
}
