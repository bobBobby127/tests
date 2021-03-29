package com.silentEight.homeworkDemo.controller.rest_Tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.silentEight.homeworkDemo.controller.rest.DetectGenderController;
import com.silentEight.homeworkDemo.service.DetectGenderService;

@ExtendWith(SpringExtension.class)
@WebMvcTest({DetectGenderService.class, DetectGenderController.class})
class Variant2_Tests {
	
	@Autowired
	private MockMvc mvc; 
	
	private void testVariant2(String name, String expectedGender) throws Exception {
		if(!name.equals("") && !expectedGender.equals("")) {
			mvc.perform(get("/api/genderDetector/variant2?name=" + name))
				.andExpect(content().string(expectedGender));
		}	
	}
	
	@Test
	public void testGenderDetection1() throws Exception {
		testVariant2("Jan Maria Rokita", "INCONCLUSIVE");
	}
	
	@Test
	public void testGenderDetection2() throws Exception {
		testVariant2("Adam Ada Ewa Nowak", "FEMALE");
	}
	
	@Test
	public void testGenderDetection3() throws Exception {
		testVariant2("Ewa Adam Lars Lubicz", "MALE");
	}
	
	@Test
	public void testGenderDetection4() throws Exception {
		testVariant2("Iga Igor Adam Kubicz", "MALE");
	}
	
	@Test
	public void testGenderDetection5() throws Exception {
		testVariant2("Nowak", "INCONCLUSIVE");
	}
	
	@Test
	public void testGenderDetection6() throws Exception {
		testVariant2("Zofia Cecylia Ziemziaczek", "FEMALE");
	}
	
	@Test
	public void testGenderDetection7() throws Exception {
		testVariant2("Kacper Pola Skrzypiec", "INCONCLUSIVE");
	}
	
	@Test
	public void testGenderDetection8() throws Exception {
		testVariant2("Luke Lars Wiśniewski", "MALE");
	}
	
	@Test
	public void testGenderDetection9() throws Exception {
		testVariant2("Jan Basia Tosia Dąb", "FEMALE");
	}
	
	@Test
	public void testGenderDetection10() throws Exception {
		testVariant2("Matylda Bolek Bob Chwastek", "MALE");
	}
	
	@Test
	public void testGenderDetection11() throws Exception {
		testVariant2("Ewa Adam Małysz", "INCONCLUSIVE");
	}
	
	@Test
	public void testGenderDetection12() throws Exception {
		testVariant2("", "INCONCLUSIVE");
	}
	
	@Test
	public void testGenderDetection13() throws Exception {
		testVariant2("ADAM MAŁYSZ EWA", "INCONCLUSIVE");
	}
	
	@Test
	public void testGenderDetection14() throws Exception {
		testVariant2("123 !@# ...", "INCONCLUSIVE");
	}
	
	@Test
	public void testGenderDetection15() throws Exception {
		testVariant2("-- BOLEK BOB EWA !@# !!", "MALE");
	}

}
