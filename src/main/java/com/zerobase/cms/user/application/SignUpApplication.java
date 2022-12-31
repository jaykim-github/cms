package com.zerobase.cms.user.application;

import com.zerobase.cms.user.client.MailgunClient;
import com.zerobase.cms.user.client.mailgun.SendMailForm;
import com.zerobase.cms.user.domain.SignUpForm;
import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.cms.user.exception.ErrorCode;
import com.zerobase.cms.user.service.SignUpCustomerService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {
    private final MailgunClient mailgunClient;
    private final SignUpCustomerService singUpCustomerSerivce;

    public void customerVerify(String email, String code){
        singUpCustomerSerivce.verifyEmail(email,code);
    }

    public String customerSignUp(SignUpForm form){
        if(singUpCustomerSerivce.isEmailExists(form.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else{
            Customer c = singUpCustomerSerivce.signUp(form);
            LocalDateTime now = LocalDateTime.now();

            String code = getRandomCode();

             SendMailForm sendMailForm  = SendMailForm.builder()
                    .from("test@test.com")
                        .to(form.getEmail())
                            .subject("Verification Email!")
                                .text(getVerificationEmailBody(form.getEmail(), form.getName(),code))
                                    .build();

            mailgunClient.sendEmail(sendMailForm);

            singUpCustomerSerivce.changeCustomerValidateEmail(c.getId(), code);
            return "회원 가입에 성공하였습니다.";
        }
    }
    private String getRandomCode(){
        return RandomStringUtils.random(10, true, true);
    }
    private String getVerificationEmailBody(String email, String name, String code){
        StringBuilder builder = new StringBuilder();
        return builder.append("Hello ").append(name).append("! Please Click Link for verification.\n\n")
            .append("http://localhost:8081/signup/verify/customer?email=")
            .append(email)
            .append("&code=")
            .append(code).toString();
    }
}