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
class Variant1_Tests {
	
	@Autowired
	private MockMvc mvc; 
	
	private void testVariant1(String name, String expectedGender) throws Exception {
		if(!name.equals("") && !expectedGender.equals("")) {
			mvc.perform(get("/api/genderDetector/variant1?name=" + name))
				.andExpect(content().string(expectedGender));
		}	
	}
	
	@Test
	public void testGenderDetection1() throws Exception {
		testVariant1("Jan Maria Rokita", "MALE");
	}
	
	@Test
	public void testGenderDetection2() throws Exception {
		testVariant1("Adam Ada Ewa Nowak", "MALE");
	}
	
	@Test
	public void testGenderDetection3() throws Exception {
		testVariant1("Ewa Adam Lars Lubicz", "FEMALE");
	}
	
	@Test
	public void testGenderDetection4() throws Exception {
		testVariant1("Iga Igor Adam Kubicz", "FEMALE");
	}
	
	@Test
	public void testGenderDetection5() throws Exception {
		testVariant1("Nowak", "INCONCLUSIVE");
	}
	
	@Test
	public void testGenderDetection6() throws Exception {
		testVariant1("Zofia Cecylia Ziemziaczek", "FEMALE");
	}
	
	@Test
	public void testGenderDetection7() throws Exception {
		testVariant1("Kacper Pola Skrzypiec", "MALE");
	}
	
	@Test
	public void testGenderDetection8() throws Exception {
		testVariant1("Luke Lars Wiśniewski", "MALE");
	}
	
	@Test
	public void testGenderDetection9() throws Exception {
		testVariant1("Jan Basia Tosia Dąb", "MALE");
	}
	
	@Test
	public void testGenderDetection10() throws Exception {
		testVariant1("Matylda Bolek Bob Chwastek", "FEMALE");
	}
	
	@Test
	public void testGenderDetection11() throws Exception {
		testVariant1("Ewa Adam Małysz", "FEMALE");
	}
	
	@Test
	public void testGenderDetection12() throws Exception {
		testVariant1("Sylwia Maria Laskowska", "FEMALE");
	}
	
	@Test
	public void testGenderDetection13() throws Exception {
		testVariant1("ADAM MAŁYSZ EWA", "MALE");
	}
	
	@Test
	public void testGenderDetection14() throws Exception {
		testVariant1("123 !@# ...", "INCONCLUSIVE");
	}
	
	@Test
	public void testGenderDetection15() throws Exception {
		testVariant1("-- BOLEK BOB EWA !@# !!", "INCONCLUSIVE");
	}

}
