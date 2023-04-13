package javaproject.builderpay.service;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import javaproject.builderpay.client.BuilderPayClient;
import javaproject.builderpay.model.request.*;
import javaproject.builderpay.model.response.*;

@Service
public class AuthenticationService {
	
	@Autowired
	private BuilderPayClient client;
	
	private final String AUTH_URI = "https://vagas.builders/api/builders/auth/tokens";
	
    public AuthenticationResponse getAuth() throws ClientProtocolException, IOException  {
		AuthenticationRequest authRequest = new AuthenticationRequest();
		authRequest.setClientId("bd753592-cf9b-4d1a-96b9-cb8b2c01bd12");
		authRequest.setClientSecret("4e8229fe-1131-439c-9846-799895a8be5b");
		return this.auth(authRequest);	
    }
	
	private AuthenticationResponse auth(AuthenticationRequest authRequest) throws ClientProtocolException, IOException  {
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("client_id", authRequest.getClientId()));
		params.add(new BasicNameValuePair("client_secret", authRequest.getClientSecret()));
		List<Header> headers = new ArrayList<Header>();
		headers.add(new BasicHeader("Content-Type", "application/json"));
		HttpEntity entity = client.doPost(AUTH_URI, params, headers);
		return client.handleResponse(entity, AuthenticationResponse.class);
	}

}
