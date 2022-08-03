package com.login.apilogin.controller;

import com.login.apilogin.domain.impl.AccessCode;
import com.login.apilogin.request.AccessCodePostRequestBody;
import com.login.apilogin.request.AccessValidatePostRequestBody;
import com.login.apilogin.response.GenerateCodeResponseBody;
import com.login.apilogin.response.GenericResponse;
import com.login.apilogin.response.ResponseBody;
import com.login.apilogin.service.impl.AccessCodeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccessCodeController extends AbstractController {

    private final AccessCodeServiceImpl accessCodeService;

    @PostMapping(path = "/generateAccessCode")
    @Operation(summary = "Create a new access code")
    public ResponseEntity<ResponseBody> save(@RequestBody @Valid AccessCodePostRequestBody accessCodeRequestBody) {
        AccessCode accessCode = accessCodeService.createAccessCode(accessCodeRequestBody);
        return new ResponseEntity<>(buildResponsyBody(
                new GenerateCodeResponseBody(accessCode.getCode()),
                "Operação de geração de código de recuperação de conta",
                "Create a new access code"
        ), HttpStatus.CREATED);
    }

    @PostMapping(path = "/validateAccessCode")
    @Operation(summary = "Validate a new access code")
    public ResponseEntity<HttpStatus> validate(@RequestBody @Valid AccessValidatePostRequestBody code) {
        accessCodeService.validateAccessCode(code.getCode());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
