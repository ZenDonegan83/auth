package com.inkd.auth.controller.socketlab;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.common.ResponseDTO;
import com.inkd.auth.model.dto.socketlab.SocketLabMsgRQ;
import com.inkd.auth.service.socketlab.SocketLabService;
import com.socketLabs.injectionApi.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/socket-lab")
public class SocketLabController {

    @Autowired
    private SocketLabService socketLabService;

    @PostMapping(value = "/sendEmail", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<SendResponse>> sendEmail(@RequestBody SocketLabMsgRQ socketLabMsgRQ) {
        ResponseDTO<SendResponse> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            SendResponse rs = this.socketLabService.sendEmail(socketLabMsgRQ);

            response.setResult(rs);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
