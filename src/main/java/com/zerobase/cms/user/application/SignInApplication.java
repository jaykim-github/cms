package com.zerobase.cms.user.application;

import com.zerobase.cms.domain.config.JwtAuthenticationProvider;
import com.zerobase.cms.domain.domain.common.UserType;
import com.zerobase.cms.user.domain.SignInForm;
import com.zerobase.cms.user.domain.model.Customer;
import com.zerobase.cms.user.exception.CustomException;
import com.zerobase.cms.user.exception.ErrorCode;
import com.zerobase.cms.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {
    private final CustomerService customerService;
    public final JwtAuthenticationProvider provider;

    public String customerLoginToken(SignInForm form){

        Customer c = customerService.findValidCustomerByEmailAndPassword(form.getEmail(),
            form.getPassword()).orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));


        return provider.createToken(c.getEmail(), c.getId(), UserType.CUSTOMER);
    }
}
