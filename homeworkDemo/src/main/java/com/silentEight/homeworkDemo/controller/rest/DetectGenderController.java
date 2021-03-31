package com.silentEight.homeworkDemo.controller.rest;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silentEight.homeworkDemo.service.DetectGenderService;

@RestController
@RequestMapping("/api/genderDetector/")
public class DetectGenderController {

	// private final String maleTokensFileName = "src/main/resources/static/maleTokens.txt";
	// private final String femaleTokensFileName = "src/main/resources/static/femaleTokens.txt";
	private static URL maleTokensUrl;
	private static URL femaleTokensUrl;
	
	static {
		try {
			maleTokensUrl = ResourceUtils.getURL("classpath:static/maleTokens.txt");
			femaleTokensUrl = ResourceUtils.getURL("classpath:static/femaleTokens.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	DetectGenderService detectGenderService;
	
	
	// Get all MALE Tokens
	@GetMapping("/tokens/male")
	public List<String> getAllMaleTokens () {
		return detectGenderService.urlToStringStream(maleTokensUrl).collect(Collectors.toList());
	}
	
	// Get all FEMALE Tokens
	@GetMapping("/tokens/female")
	public List<String> getAllFemaleTokens () {
		return detectGenderService.urlToStringStream(femaleTokensUrl).collect(Collectors.toList());
	}
	
	

	// Get the Gender - variant 1 - gender based on the first name
	@GetMapping("/variant1")
	public String getGenderVariant11(@RequestParam String name) {
		return detectGenderService.getGenderVariant1(name, maleTokensUrl, femaleTokensUrl);
	}
	
	// Get the Gender - variant 2 - gender based on majority rule
	@GetMapping("/variant2")
	public String getGenderVariant22(@RequestParam String name) {	
		return detectGenderService.getGenderVariant2(name, maleTokensUrl, femaleTokensUrl);
	}
	
	

}

