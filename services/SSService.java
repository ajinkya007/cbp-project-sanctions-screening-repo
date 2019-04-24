package com.citi.bridge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citi.bridge.models.SS;
import com.citi.bridge.repositories.SSRepository;

@Service
public class SSService {

	@Autowired
	private SSRepository ssR;

	public SS storeSS(SS ss) {
		return ssR.save(ss);
	}

}