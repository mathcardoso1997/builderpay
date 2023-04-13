package javaproject.builderpay.service;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javaproject.builderpay.client.BuilderPayClient;
import javaproject.builderpay.mapper.BuilderPayMapper;
import javaproject.builderpay.model.dto.BillsPaymentDto;
import javaproject.builderpay.model.entity.PaymentReceipt;
import javaproject.builderpay.model.request.*;
import javaproject.builderpay.model.response.*;
import javaproject.builderpay.repository.BuilderPayRepository;

@Service
public class BuilderPayService {
	
	@Autowired
	private BuilderPayMapper mapper;
	
	@Autowired
	private BuilderPayRepository repository;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private BuilderPayClient client;
	
	private final String BILLS_PAYMENT_CODE_URI = "https://vagas.builders/api/builders/bill-payments/codes";
	
	private List<Header> headers;
	private BillsPaymentRequest request;
	private RecipientResponse response;
	private ArrayList<NameValuePair> params;
	private PaymentReceipt paymentReceipt;
	
    public BillsPaymentDto calculateAmountCharged(BillsPaymentRequest request) throws Exception {
    	return this.validateRequest(request)
			       .setHeaders()
			       .setParams(request)
			       .doPost()
			       .calculateAmountCharged();
    }

	private BuilderPayService doPost() throws ClientProtocolException, IOException {
		HttpEntity entity;
		entity = this.client.doPost(BILLS_PAYMENT_CODE_URI, params, headers);
		this.response = this.client.handleResponse(entity, RecipientResponse.class);
		return this;
	}

	private BillsPaymentDto calculateAmountCharged() throws Exception {
		if (!this.response.getType().equals("NPC")) {
			throw new Exception("The Payment Bill " + this.response.getCode() + " is not an NPC" , new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		}
		
		var paymentDate = LocalDate.parse(this.request.getPaymentDate());
		var dueDate = LocalDate.parse(this.response.getDueDate());
		
		if (paymentDate.compareTo(dueDate) >= 0) {
			throw new Exception("The Payment Bill " + this.response.getCode() + " is not expired" , new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		}		
		
		this.paymentReceipt = this.mapper.toPaymentReceipt(this.response, paymentDate, dueDate);
		repository.save(this.paymentReceipt);
		return this.mapper.toPaymentSlipResponse(this.response, paymentReceipt);
	}

	private BuilderPayService setParams(BillsPaymentRequest request) {
		this.params = new ArrayList<NameValuePair>(1);
    	this.params.add(new BasicNameValuePair("code", request.getBarCode()));
		return this;
	}

	private BuilderPayService setHeaders() throws ClientProtocolException, IOException  {
		this.headers = new ArrayList<Header>();
		this.headers.add(new BasicHeader("Content-Type", "application/json"));
		var authResponse = this.authenticationService.getAuth();
		this.headers.add(new BasicHeader("Authorization", authResponse.getToken()));
		return this;
	}

	private BuilderPayService validateRequest(BillsPaymentRequest request) throws Exception  {
		if (request.getBarCode().isBlank()) {
			throw new Exception("Missing Bar Code", new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		}
    	
    	if (Double.isNaN(Double.parseDouble(request.getBarCode()))) {
			throw new Exception("Bar Code is not valid", new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		}
		
		if (request.getPaymentDate().isEmpty()) {
			throw new Exception("Missing Payment Date", new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		}
		
		this.request = request;
		
		return this;
	}

}
