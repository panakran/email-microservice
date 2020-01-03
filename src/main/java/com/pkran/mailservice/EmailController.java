package com.pkran.mailservice;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1.0")
@Api(value = "Email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @ApiOperation(value = "Send new email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Email sended"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/email")
    public void sentNewMail(@ApiParam(required = true) @RequestBody EmailDTO emailDTO) {
        emailService.createMessageAndSent(emailDTO);
    }

}
