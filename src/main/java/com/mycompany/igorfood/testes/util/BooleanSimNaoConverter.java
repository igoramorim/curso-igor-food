package com.mycompany.igorfood.testes.util;

import javax.persistence.AttributeConverter;

public class BooleanSimNaoConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return attribute ? "S" : "N";
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		if ("S".equalsIgnoreCase(dbData)) {
			return true;
		} else if ("N".equalsIgnoreCase(dbData)) {
			return false;
		}
		
		return null;
	}

}
