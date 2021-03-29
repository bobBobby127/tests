package com.silentEight.homeworkDemo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class DetectGenderService {

	
	
	public Stream<String> fileToStringStream (String fileName) {
		Stream<String> stream = Stream.empty();
		try {
			stream = Files.lines(Paths.get(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream;
	}
	
	
	
	// get gender VARIANT 1 - based on 1st name token 
	public String getGenderVariant1 (String name, String maleTokensFileName, String femaleTokensFileName) {

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
			System.out.println(wrapper.nameFirstToken);
			//
			this.fileToStringStream(maleTokensFileName).filter(line -> line.equalsIgnoreCase(wrapper.nameFirstToken)).findFirst().ifPresent(action -> {
				wrapper.isMale = true;
			});
			
			this.fileToStringStream(femaleTokensFileName).filter(line -> line.equalsIgnoreCase(wrapper.nameFirstToken)).findFirst().ifPresent(action -> {
				wrapper.isFemale = true;
			});
		}
		
		if(wrapper.isMale && !wrapper.isFemale) {
			gender = "MALE";
		} else if(!wrapper.isMale && wrapper.isFemale) {
			gender = "FEMALE";
		}
		
		return gender;
	}
	
	
	
	// get gender VARIANT 1 - based on 1st name token 
	public String getGenderVariant2(String name, String maleTokensFileName, String femaleTokensFileName) {
		String gender = "INCONCLUSIVE";
		var wrapper = new Object() {
			short maleCount = 0;
			short femaleCount = 0;
		};
		String nameTokens[] = name.split(" ");
		
		for(String nameToken : nameTokens) {
			this.fileToStringStream(maleTokensFileName).filter(line -> line.equalsIgnoreCase(nameToken)).findFirst().ifPresent(action -> {
				wrapper.maleCount++;
			});
			
			this.fileToStringStream(femaleTokensFileName).filter(line -> line.equalsIgnoreCase(nameToken)).findFirst().ifPresent(action -> {
				wrapper.femaleCount++;
			});
		}
		
		if(wrapper.maleCount > wrapper.femaleCount) {
			gender = "MALE";
		} else if(wrapper.maleCount < wrapper.femaleCount) {
			gender = "FEMALE";
		}
		
		System.out.println(wrapper.maleCount);
		System.out.println(wrapper.femaleCount);
		return gender;
	}

}
