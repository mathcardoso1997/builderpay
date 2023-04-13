package javaproject.builderpay.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class BuilderPayClient {
	
	public HttpEntity doPost(String uri, List<NameValuePair> params, List<Header> headers) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		for (Header header : headers) {
			httpPost.addHeader(header);
		}
		HttpResponse response = httpClient.execute(httpPost);
		return response.getEntity();
	}
	
	public <T> T handleResponse(HttpEntity entity, Class<T> classOfT) throws UnsupportedOperationException, IOException  {
		if (entity != null) {
		    try (InputStream instream = entity.getContent()) {
		    	JSONObject jsonObj = new JSONObject(instream);
		    	return new Gson().fromJson(jsonObj.toString(), classOfT);
		    }
		}
		return null;
	}

}
