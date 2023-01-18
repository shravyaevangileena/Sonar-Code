package com.creditcard.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.entities.Statement;
import com.creditcard.exception.InvalidCardException;
import com.creditcard.service.StatementService;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController

public class StatementController {

	@Autowired
	StatementService statementservice;
	String details="Statement Not Found!";
	private Logger logger=Logger.getLogger(StatementController.class.getName());
	
	@GetMapping("/list")
	public List<Statement> getAllStatements() {
		return statementservice.getAllStatements();
		
	}
	@GetMapping("/getstatement/{id}")
	public Statement  getStatement(@PathVariable("id") long id) {
		if(statementservice.getStatement(id)==null) {
			logger.log(Level.INFO, details );
			throw new InvalidCardException();
		}
		return statementservice.getStatement(id);
	}
	
	@PostMapping(value="/addstatement",consumes = "application/json")
	public Statement addStatement(@RequestBody Statement statement) {
		
		return statementservice.addStatement(statement);
		
	}
	
	@DeleteMapping("/statement/delete/{id}")
	public  String removeStatement(long id) {
		if(statementservice.getStatement(id)==null) {
			logger.log(Level.INFO, details );
			throw new InvalidCardException();
		}
		statementservice.removeStatement(id);
		return "Deleted successfully";
		
	}
	
	@PutMapping(value="/statement/update/{statementid}",consumes = "application/json")
	public Statement updateStatement(@PathVariable("statementid") long id,@RequestBody Statement statement) {
		if(statementservice.getStatement(id)==null) {
			logger.log(Level.INFO, details );
			throw new InvalidCardException();
		}
		return statementservice.updateStatement(id, statement);
		
	}

}
