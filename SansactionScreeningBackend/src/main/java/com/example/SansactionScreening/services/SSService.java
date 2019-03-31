package com.example.SansactionScreening.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SansactionScreening.models.SS;
import com.example.SansactionScreening.repositories.SSRepository;

@Service
public class SSService {
	
	@Autowired
	private SSRepository ssR;
	
	public SS storeSS(SS ss)
	{
		return ssR.save(ss);
	}
	

}
