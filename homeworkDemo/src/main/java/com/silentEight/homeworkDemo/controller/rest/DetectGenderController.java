package com.silentEight.homeworkDemo.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silentEight.homeworkDemo.service.DetectGenderService;

@RestController
@RequestMapping("/api/genderDetector/")
public class DetectGenderController {

	private final String maleTokensFileName = "src/main/resources/static/maleTokens.txt";
	private final String femaleTokensFileName = "src/main/resources/static/femaleTokens.txt";
	
	@Autowired
	DetectGenderService detectGenderService;
	
	// Get all MALE Tokens
	@GetMapping("/tokens/male")
	public List<String> getAllMaleTokens (){
		return detectGenderService.fileToStringStream(maleTokensFileName).collect(Collectors.toList());
	}
	
	// Get all FEMALE Tokens
	@GetMapping("/tokens/female")
	public List<String> getAllFemaleTokens (){
		return detectGenderService.fileToStringStream(femaleTokensFileName).collect(Collectors.toList());
	}
	
	
	
	// Get the Gender - variant 1 - gender based on the first name
	@GetMapping("/variant1")
	public String getGenderVariant11(@RequestParam String name) {
		return detectGenderService.getGenderVariant1(name, maleTokensFileName,femaleTokensFileName);
	}
	
	// Get the Gender - variant 2 - gender based on majority rule
	@GetMapping("/variant2")
	public String getGenderVariant22(@RequestParam String name) {	
		return detectGenderService.getGenderVariant2(name, maleTokensFileName,femaleTokensFileName);
	}
	
	

}

/*
 * PREVIOUS VERSION - WITH @RequestBody (raw)
 * // Get the Gender - variant 1 - gender based on the first name
	@GetMapping("/variant11")
	public String getGenderVariant1(@RequestBody String name) {
		return detectGenderService.getGenderVariant1(name, maleTokensFileName,femaleTokensFileName);
	}
	// Get the Gender - variant 2 - gender based on majority rule
	@GetMapping("/variant22")
	public String getGenderVariant2(@RequestBody String name) {	
		return detectGenderService.getGenderVariant2(name, maleTokensFileName,femaleTokensFileName);
	}
 * 
*/
