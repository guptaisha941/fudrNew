package com.example.fudrNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Msg91Controller {

    private final Msg91Service msg91Service;

    @Autowired
    public Msg91Controller(Msg91Service msg91Service) {
        this.msg91Service = msg91Service;
    }

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam String mobile, @RequestParam int otp) {
        try {
            return msg91Service.sendOtp(mobile,otp);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending OTP";
        }
    }


    @GetMapping("/verify-otp")
    public String verifyOtp(@RequestParam String mobile, @RequestParam int otp) {
        try {
            return msg91Service.verifyOtp(mobile,otp);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error verifying OTP";
        }
    }
}
