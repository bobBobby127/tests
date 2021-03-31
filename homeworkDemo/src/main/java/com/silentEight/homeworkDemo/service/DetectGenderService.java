package com.silentEight.homeworkDemo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class DetectGenderService {

	
	
	public Stream<String> urlToStringStream(URL fileUrl) {
		Stream<String> stream = Stream.empty();
		//stream = Files.lines(Paths.get(fileName));	
		
		try {
			stream = new BufferedReader( new InputStreamReader(fileUrl.openStream()) ).lines();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream;
	}
	
	
	
	// get gender VARIANT 1 - based on 1st name token 
	public String getGenderVariant1 (String name, URL maleTokensUrl, URL femaleTokensUrl) {

		String gender = "INCONCLUSIVE";
		var wrapper = new Object() {
			boolean isMale = false;
			boolean isFemale = false;
			String nameFirstToken = null;
		};
		
		String nameTokens[] = name.split(" ");
		if(nameTokens.length != 0) {
			wrapper.nameFirstToken = nameTokens[0];
		}

		if(wrapper.nameFirstToken != null) {
			this.urlToStringStream(maleTokensUrl).filter(line -> line.equalsIgnoreCase(wrapper.nameFirstToken)).findFirst().ifPresent(action -> {
				wrapper.isMale = true;
			});
			
			this.urlToStringStream(femaleTokensUrl).filter(line -> line.equalsIgnoreCase(wrapper.nameFirstToken)).findFirst().ifPresent(action -> {
				wrapper.isFemale = true;
			});
		}
		
		if(wrapper.isMale && !wrapper.isFemale) {
			gender = "MALE";
		} else if(!wrapper.isMale && wrapper.isFemale) {
			gender = "FEMALE";
		}
		
		
		System.out.println("[printOut]: Result for name '" + name + "' is '" + gender + "', first name token was '" + nameTokens[0] + "'");
		return gender;
	}
	
	
	
	// get gender VARIANT 1 - based on 1st name token 
	public String getGenderVariant2(String name, URL maleTokensUrl, URL femaleTokensUrl) {
		String gender = "INCONCLUSIVE";
		var wrapper = new Object() {
			short maleCount = 0;
			short femaleCount = 0;
		};
		String nameTokens[] = name.split(" ");
		
		for(String nameToken : nameTokens) {
			this.urlToStringStream(maleTokensUrl).filter(line -> line.equalsIgnoreCase(nameToken)).findFirst().ifPresent(action -> {
				wrapper.maleCount++;
			});
			
			this.urlToStringStream(femaleTokensUrl).filter(line -> line.equalsIgnoreCase(nameToken)).findFirst().ifPresent(action -> {
				wrapper.femaleCount++;
			});
		}
		
		if(wrapper.maleCount > wrapper.femaleCount) {
			gender = "MALE";
		} else if(wrapper.maleCount < wrapper.femaleCount) {
			gender = "FEMALE";
		}
		
		System.out.println("[printOut]: Result for name '" + name + "' is '" + gender + "', number of MALE TOKENS:" + wrapper.maleCount + ", number of FEMALE TOKENS:" + wrapper.femaleCount);
		return gender;
	}

}
