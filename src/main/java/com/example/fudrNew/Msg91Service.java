package com.example.fudrNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Msg91Service {

    @Autowired
    private RestTemplate restTemplate;

    private static final String MSG91_API_URL = "https://control.msg91.com/api/v5";
    private static final String AUTH_KEY = "411899AJZN1hnbae6657b6e6dP1";
    private static final String TEMPLATE_ID = "657b93cdd6fc0536f1685352";

    public String sendOtp(String mobile, int otp) throws Exception {

        String url = MSG91_API_URL + "/otp?mobile=" + mobile;
        String requestBody = String.format("{\"otp\":\"%d\",\"template_id\":\"%s\"}", otp, TEMPLATE_ID);

        HttpHeaders headers = new HttpHeaders();
        headers.set("authkey", AUTH_KEY);
        headers.set("content-type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            return restTemplate.postForObject(url, requestEntity, String.class);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error sending OTP";
        }   
    }


    public String verifyOtp(String mobile, int otp) throws Exception {
        String url = MSG91_API_URL + "/otp/verify?otp=" + otp + "&mobile=" + mobile;

        HttpHeaders headers = new HttpHeaders();
        headers.set("authkey", AUTH_KEY);
        headers.set("accept", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            // ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

            return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class).toString();

            // return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error verifying OTP";
        }
    }
}
