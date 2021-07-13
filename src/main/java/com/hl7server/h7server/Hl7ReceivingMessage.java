package com.hl7server.h7server;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.hoh.hapi.server.HohServlet;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.protocol.ReceivingApplication;
import ca.uhn.hl7v2.protocol.ReceivingApplicationException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/hl7list"}, displayName = "hl7_listener")
public class Hl7ReceivingMessage extends HohServlet {


    /**
     * Initialise the servlet
     */
    @Override
    public void init(ServletConfig theConfig) throws ServletException {

        /* Servlet should be initialized with an instance of
         * ReceivingApplication, which handles incoming messages
         */
        setApplication(new MyApplication());

    }

    /**
     * The application does the actual processing
     */
    private class MyApplication implements ReceivingApplication {

        public Message processMessage(Message theMessage, Map map) throws ReceivingApplicationException, HL7Exception, HL7Exception {
            System.out.println("Received message:\n" + theMessage.encode());

            Message response;
            try {
                response = theMessage.generateACK();
            } catch (IOException e) {
                throw new ReceivingApplicationException(e);
            }

            boolean somethingFailed = false;
            if (somethingFailed) {
                throw new ReceivingApplicationException("");
            }

            if (somethingFailed) {
                try {
                    response = theMessage.generateACK("AE", new HL7Exception("There was a problem!!"));
                } catch (IOException e) {
                    throw new ReceivingApplicationException(e);
                }
            }

            return response;
        }

        @Override
        public boolean canProcess(Message theMessage) {
            return true;
        }

    }

}
