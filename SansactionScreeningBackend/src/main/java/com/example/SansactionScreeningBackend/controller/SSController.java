/**
 * 
 */
package com.example.SansactionScreeningBackend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SansactionScreening.models.SS;
import com.example.SansactionScreening.repositories.SSRepository;
import com.example.SansactionScreening.models.SS;

/**
 * @author Lenovo
 *
 */


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class SSController {

	@Autowired
	private SSRepository ssR;
	
	@PostMapping("/Users")
    public SS createUsers(@Valid @RequestBody SS ss) {
       
        return ssR.save(ss);
        
    }
	
	 @GetMapping("/Users")
	    public List<SS> getAllUsers() {
	        Sort sortByUserNameAtDesc = new Sort(Sort.Direction.DESC, "userName");
	        return ssR.findAll();
	    }
	 
	 @GetMapping("/User/{userName}")
	 public Optional<SS> getUser(@PathVariable String userName)
	 {
		 return ssR.findById(userName);
	 }
	 
}
