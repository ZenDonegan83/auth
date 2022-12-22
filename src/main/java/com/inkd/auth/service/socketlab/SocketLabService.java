package com.inkd.auth.service.socketlab;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.socketlab.SocketLabMsgRQ;
import com.socketLabs.injectionApi.SendResponse;
import org.springframework.stereotype.Service;

@Service
public interface SocketLabService {

    SendResponse sendEmail(SocketLabMsgRQ socketLabMsgRQ) throws AppsException;

}
