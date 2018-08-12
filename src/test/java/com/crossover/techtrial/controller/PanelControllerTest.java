package com.crossover.techtrial.controller;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import com.crossover.techtrial.service.PanelService;
import com.crossover.techtrial.service.PanelServiceImpl;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * PanelControllerTest class will test all APIs in PanelController.java.
 * @author Crossover
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PanelControllerTest {
  
  MockMvc mockMvc;
  
  @Mock
  private PanelController panelController;
  
  @Autowired
  private TestRestTemplate template;

  @Before
  public void setup() throws Exception {
    mockMvc = MockMvcBuilders.standaloneSetup(panelController).build();
  }

  @Test
  public void testPanelShouldBeRegistered() throws Exception {
    HttpEntity<Object> panel = getHttpEntity(
        "{\"serial\": \"232324\", \"longitude\": \"54.123232\"," 
            + " \"latitude\": \"54.123232\",\"brand\":\"tesla\" }");
    ResponseEntity<Panel> response = template.postForEntity(
        "/api/register", panel, Panel.class);
    Assert.assertEquals(202,response.getStatusCode().value());
  }
  
  @Test
  public void testgetHourlyElectricityPDates() throws Exception {
	  HttpEntity<Object> hourly = getHttpEntity("");
		    ResponseEntity<HourlyElectricity> response = template.getForEntity(
		        "/api/panels/232324/hourly",HourlyElectricity.class);
		  
    Assert.assertEquals(200,response.getStatusCode().value());
  }
  
  @Test
  public void testpostHourlyElectricityPDates() throws Exception {
    HttpEntity<Object> hrs = getHttpEntity(
        "{\"generatedElectricity\": \"300\", \"readingAt\": \"2012-08-21 12:11:12\" }");
    ResponseEntity<HourlyElectricity> response = template.postForEntity(
        "/api/panels/232324/hourly", hrs, HourlyElectricity.class);
    Assert.assertEquals(200,response.getStatusCode().value());
  }
  
  @Test
  public void testgetDayElectricityPDates() throws Exception {
	  HttpEntity<Object> daily = getHttpEntity("");
		    ResponseEntity<Object> response = template.getForEntity(
		        "/api/panels/232324/daily",Object.class);
		  
    Assert.assertNotNull(response.toString());
  }
  
  @Test
  public void testPanelEquals() throws Exception {
	  
	 
	Panel panel1 = new Panel(); 
	panel1.setBrand("spaces ");
	panel1.setSerial("232321");
	
	Panel panel2 = new Panel(); 
	panel2.setBrand("spaces ");
	panel2.setSerial("232322");
	
		  
    Assert.assertEquals(false,panel1.equals(panel2));
  }

  private HttpEntity<Object> getHttpEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<Object>(body, headers);
  }
}
