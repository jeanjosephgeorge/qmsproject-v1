package com.revature.util;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {

	public static final String CONTENT_TYPE = "application/json";

	public static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	// Restricting Instantiation
	private Json() {
	}

	public static byte[] write(Object o) {
		try {
			return mapper.writeValueAsBytes(o);
		} catch (IOException e) {
			// This redirects to a Java Exceptions class
			Exceptions.logJsonMarshalException(e, o.getClass());
			return null;
		}
	}

	public static Object read(InputStream is, Class<?> clas) {
		try {
			return mapper.readValue(is, clas);
		} catch (IOException e) {
			Exceptions.logJsonMarshalException(e, clas);
			return null;
		}
	}

}
