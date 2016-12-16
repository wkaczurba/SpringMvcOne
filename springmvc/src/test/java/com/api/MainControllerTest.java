package com.api;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import com.User;
import com.data.UserRepository;
import com.web.MainController;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class MainControllerTest {

	@Test
	public void helloTest() throws Exception {
		MainController controller = new MainController(null);
		
		MockMvc mvc = standaloneSetup(controller).setSingleView(
				new InternalResourceView("subpage")).build();
		
		mvc.perform(get("/subpage")).andExpect(view().name("subpage"));
	}
	
	@Test
	public void mainTest() throws Exception {
		MainController controller = new MainController(null);
		
		MockMvc mvc = standaloneSetup(controller).build();
		
		mvc.perform(get("/"))
			.andExpect(view().name("index"));
	}
	
	@Test
	public void argsDemo1Test() throws Exception {
		MainController controller = new MainController(null);
		
		MockMvc mvc = standaloneSetup(controller).setSingleView(new InternalResourceView("argsdemo_one")).build();
		
		mvc.perform(get("/argsdemo1?first_int=347&some_string=Some%20text"))
			.andExpect(view().name("argsdemo_one"))
			.andExpect(model().attributeExists("first_int"))
			.andExpect(model().attributeExists("someString"))
		    .andExpect(model().attribute("first_int", 347))
		    .andExpect(model().attribute("someString", "Some%20text"));
	}
	
	@Test
	public void asPathTest() throws Exception {
		// @RequestMapping(value="/aspath/{command}/{number}/{number2}", method=GET)
		// String command,
		// int number
		// number2,
		// Returns: Model with attributes, name as "aspath"
		
		MainController controller = new MainController(null);
		String command = "anycommand";
		int number = 3049;
		int number2 = 32123;
		
		MockMvc mvc = standaloneSetup(controller).setSingleView(new InternalResourceView("aspath")).build();
		mvc.perform(get(String.format("/aspath/%s/%d/%d", command, number, number2)))
			.andExpect(view().name("aspath"))
			.andExpect(model().attributeExists("command"))
			.andExpect(model().attributeExists("number"))
			.andExpect(model().attributeExists("number2"))
			.andExpect(model().attribute("command", command))
			.andExpect(model().attribute("number", number))
			.andExpect(model().attribute("number2", number2));
	}
	
	@Test
	public void showFormsTest() throws Exception {
		MainController controller = new MainController(null);
		
		MockMvc mvc = standaloneSetup(controller)
				         .setSingleView(new InternalResourceView("forms"))
				         .build(); // No need for an internal view I guess... 
		
		mvc.perform(get("/forms")).andExpect(view().name("forms"));
		// Request: "forms", method=GET.
	}
	
	@Test
	public void formsPostTest() throws Exception {
		UserRepository mockRepo = mock(UserRepository.class);
		MainController controller = new MainController(mockRepo);
		
		User unsaved = new User("ONE", "TWO", "THREE", "FOUR", "FIVE@EMAIL.COM");
		User saved = new User(23L, "ONE", "TWO", "THREE", "FOUR", "FIVE@EMAIL.COM");		
		when(mockRepo.saveUser(unsaved)).thenReturn(saved);
		
		MockMvc mvc = standaloneSetup(controller).build();		
		mvc.perform(post("/forms")
				.param("firstName", "ONE")
				.param("lastName", "TWO")
				.param("userName", "THREE")
				.param("password", "FOUR")
		        .param("email", "FIVE@EMAIL.COM"))
			.andExpect(redirectedUrl("user/THREE"));
				
		verify(mockRepo, atLeastOnce()).saveUser(unsaved);
	}
	

	// TODO: add showUserTest()
	@Test
	public void showUserTest() throws Exception {
		UserRepository userRepo = Mockito.mock(UserRepository.class);
		User userValue = new User(23L, "Joahim", "Banas", "jbanas", "pass123", "jbanas@banas.com");
		when(userRepo.findByUserName("jbanas")).thenReturn(userValue);
		
		MainController controller = new MainController(userRepo);
		
		MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();
		
		mvc.perform(get("/user/jbanas"))
			.andExpect(view().name("user"))
			.andExpect(model().attributeExists("user"))
			.andExpect(model().attribute("user", userValue));
	}

}
