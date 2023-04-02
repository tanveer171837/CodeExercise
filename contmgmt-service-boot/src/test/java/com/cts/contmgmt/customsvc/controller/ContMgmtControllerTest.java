package com.cts.contmgmt.customsvc.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.contmgmt.api.request.DocumentRequest;
import com.cts.contmgmt.customsvc.ContMgmtBootApplication;
import com.cts.contmgmt.customsvc.service.ContMgmtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ContMgmtController.class)
@EnableConfigurationProperties
@ContextConfiguration(classes = { ContMgmtBootApplication.class })
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles(profiles = "local")
@PropertySource("classpath:/application.properties")
public class ContMgmtControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private ContMgmtController contMgmtController;

	@MockBean
	private ContMgmtService contMgmtService;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	void downloadDocumentTest() throws Exception {
		DocumentRequest documentRequest = DocumentRequest.builder().docId(123L).build();
		ObjectWriter ow = mapper.writer();
		String requestJson = ow.writeValueAsString(documentRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/contmgmtApi/document").content(requestJson)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void downloadAllDocumentsTest() throws Exception {
		mockMvc.perform(get("/contmgmtApi/documents").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
