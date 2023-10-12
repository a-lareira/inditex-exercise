package es.lareira.inditex;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class InditexExerciseIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void test1() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/appliedPrice")
        .queryParam("productId", "35455")
        .queryParam("brandId", "1")
        .queryParam("applicationDate", "2020-06-14T10:00:00");
    mockMvc.perform(requestBuilder)
        .andExpect(jsonPath("$.productCode").value(35455))
        .andExpect(jsonPath("$.brandCode").value(1))
        .andExpect(jsonPath("$.rateCode").value(1))
        .andExpect(jsonPath("$.finalPrice").value(35.5))
        .andExpect(jsonPath("$.fromApplicationDate").value("2020-06-14T00:00:00"))
        .andExpect(jsonPath("$.toApplicationDate").value("2020-12-31T23:59:59"));
  }

  @Test
  void test2() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/appliedPrice")
        .queryParam("productId", "35455")
        .queryParam("brandId", "1")
        .queryParam("applicationDate", "2020-06-14T16:00:00");
    mockMvc.perform(requestBuilder)
        .andExpect(jsonPath("$.productCode").value(35455))
        .andExpect(jsonPath("$.brandCode").value(1))
        .andExpect(jsonPath("$.rateCode").value(2))
        .andExpect(jsonPath("$.finalPrice").value(25.45))
        .andExpect(jsonPath("$.fromApplicationDate").value("2020-06-14T15:00:00"))
        .andExpect(jsonPath("$.toApplicationDate").value("2020-06-14T18:30:00"));
  }

  @Test
  void test3() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/appliedPrice")
        .queryParam("productId", "35455")
        .queryParam("brandId", "1")
        .queryParam("applicationDate", "2020-06-14T21:00:00");
    mockMvc.perform(requestBuilder)
        .andExpect(jsonPath("$.productCode").value(35455))
        .andExpect(jsonPath("$.brandCode").value(1))
        .andExpect(jsonPath("$.rateCode").value(1))
        .andExpect(jsonPath("$.finalPrice").value(35.5))
        .andExpect(jsonPath("$.fromApplicationDate").value("2020-06-14T00:00:00"))
        .andExpect(jsonPath("$.toApplicationDate").value("2020-12-31T23:59:59"));
  }

  @Test
  void test4() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/appliedPrice")
        .queryParam("productId", "35455")
        .queryParam("brandId", "1")
        .queryParam("applicationDate", "2020-06-15T10:00:00");
    mockMvc.perform(requestBuilder)
        .andExpect(jsonPath("$.productCode").value(35455))
        .andExpect(jsonPath("$.brandCode").value(1))
        .andExpect(jsonPath("$.rateCode").value(3))
        .andExpect(jsonPath("$.finalPrice").value(30.50))
        .andExpect(jsonPath("$.fromApplicationDate").value("2020-06-15T00:00:00"))
        .andExpect(jsonPath("$.toApplicationDate").value("2020-06-15T11:00:00"));
  }

  @Test
  void test5() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/appliedPrice")
        .queryParam("productId", "35455")
        .queryParam("brandId", "1")
        .queryParam("applicationDate", "2020-06-16T21:00:00");
    mockMvc.perform(requestBuilder)
        .andExpect(jsonPath("$.productCode").value(35455))
        .andExpect(jsonPath("$.brandCode").value(1))
        .andExpect(jsonPath("$.rateCode").value(4))
        .andExpect(jsonPath("$.finalPrice").value(38.95))
        .andExpect(jsonPath("$.fromApplicationDate").value("2020-06-15T16:00:00"))
        .andExpect(jsonPath("$.toApplicationDate").value("2020-12-31T23:59:59"));
  }

  @Test
  void test_notFound_when_product_does_not_exists() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/appliedPrice")
        .queryParam("productId", "35456")
        .queryParam("brandId", "1")
        .queryParam("applicationDate", "2020-06-16T21:00:00");
    mockMvc.perform(requestBuilder)
        .andExpect(status().isNotFound());
  }

  @Test
  void test_notFound_when_brand_does_not_exists() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/appliedPrice")
        .queryParam("productId", "35455")
        .queryParam("brandId", "973")
        .queryParam("applicationDate", "2020-06-16T21:00:00");
    mockMvc.perform(requestBuilder)
        .andExpect(status().isNotFound());
  }

  @Test
  void test_notFound_when_no_price_for_application_date() throws Exception {
    MockHttpServletRequestBuilder requestBuilder = get("/v1/appliedPrice")
        .queryParam("productId", "35455")
        .queryParam("brandId", "1")
        .queryParam("applicationDate", "2030-06-16T21:00:00");
    mockMvc.perform(requestBuilder)
        .andExpect(status().isNotFound());
  }
}
