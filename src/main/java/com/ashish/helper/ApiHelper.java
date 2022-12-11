package com.ashish.helper;

import java.io.File;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiHelper {

	private static Logger logger = LogManager.getLogger(ApiHelper.class);

	/**
	 * @author ashishchatterjee
	 * @param fullUrl
	 * @param methodType
	 * @param apiParameters
	 * @param apiHeaders
	 * @param jsonBody
	 * @param paraminbody
	 * @return
	 */
	public static Response executeAndGetResponse(String fullUrl, String methodType, Map<String, String> apiParameters,
			Map<String, String> apiHeaders, String jsonBody, Boolean paraminbody) {
		// remove the query params from full url
		String[] cmd = fullUrl.split("\\?");
		String requestUrl = cmd[0];

		// Prepare request
		RequestSpecification reqspec = RestAssured.given();

		if (apiHeaders != null && apiHeaders.size() > 0) {
			reqspec = reqspec.headers(apiHeaders);
			logger.info("Api Request Header is: " + apiHeaders);
		}

		if (jsonBody != null && (jsonBody.endsWith(".xlsx") || jsonBody.endsWith(".xls"))) {
			reqspec = reqspec.multiPart("file_storage", new File(jsonBody));
		} else if (jsonBody != null) {
			reqspec = reqspec.body(jsonBody);
			logger.info("Api Request Json Body: " + jsonBody);
		}

		if (apiParameters != null) {
			// set request query params from passed in HashMap
			if (apiParameters != null && apiParameters.size() > 0) {
				reqspec = reqspec.queryParams(apiParameters);
			}
		} else if (cmd.length > 1) {
			String parameters[] = cmd[1].split("&");
			for (int i = 0; i < parameters.length; i++) {
				String key = parameters[i].split("=")[0];
				String value = parameters[i].split("=")[1];
				reqspec = reqspec.queryParam(key, value);
			}
		}

		reqspec = reqspec.when();

		Response response = null;
		switch (methodType.toLowerCase()) {
		case "get":
			response = reqspec.get(requestUrl);
			break;
		case "post":
			response = reqspec.post(requestUrl);
			break;
		case "delete":
			response = reqspec.delete(requestUrl);
			break;
		case "put":
			response = reqspec.put(requestUrl);
			break;
		case "patch":
			response = reqspec.patch(requestUrl);
			break;
		}

		response = response.then().log().all().extract().response();
		logger.info("API Response for " + requestUrl + " :- " + response.asString());
		logger.info("Response Cookie :" + response.getCookies());
		logger.info("Response Time :" + response.getTime());
		logger.info("Response Headers :" + response.getHeaders());
		logger.info("Response Status Code :" + response.getStatusCode());

		return response;
	}

}
