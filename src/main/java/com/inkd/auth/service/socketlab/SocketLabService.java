package com.inkd.auth.service.socketlab;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.socketlab.SocketLabMsgRQ;
import com.socketLabs.injectionApi.SendResponse;
import com.socketLabs.injectionApi.SocketLabsClient;
import com.socketLabs.injectionApi.message.BasicMessage;
import com.socketLabs.injectionApi.message.EmailAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocketLabService {

    @Value("${socketlab.server.id}")
    private Integer serverID;

    @Value("${socketlab.injection.api.Key}")
    private String injectionAPIKey;

    /**
     *
     * @param socketLabMsgRQ
     * @return
     * @throws AppsException
     */
    public SendResponse sendEmail(SocketLabMsgRQ socketLabMsgRQ) throws AppsException {
        //Create the socket lab client
        SocketLabsClient client = new SocketLabsClient(serverID, injectionAPIKey);

        //Create basic message
        BasicMessage message = new BasicMessage();

        //Set message subject
        message.setSubject(socketLabMsgRQ.getSubject());
        //Set HTML Body
        message.setHtmlBody(socketLabMsgRQ.getHtmlBody());
        //Set plain text
        message.setPlainTextBody(socketLabMsgRQ.getPlainTextBody());

        //Set from Email
        message.setFrom(new EmailAddress(socketLabMsgRQ.getFromEmail()));
        //Set to Emails
        message.setTo(this.getEmailAddressList(socketLabMsgRQ.getToEmails()));

        try {
            //Send emails
            SendResponse response = client.send(message);
            return response;
        } catch (Exception e) {
            throw new AppsException("Email send failed");
        }
    }

    /**
     * Get Email Address List
     *
     * @param emailList
     * @return
     * @throws AppsException
     */
    private List<EmailAddress> getEmailAddressList(List<String> emailList) throws AppsException {
        List<EmailAddress> emailAddressList = new ArrayList<>();

        if (emailList.isEmpty()) {
            throw new AppsException("To email address not found");
        } else {
            for (String email : emailList) {
                emailAddressList.add(new EmailAddress(email));
            }
        }

        return emailAddressList;
    }
}
