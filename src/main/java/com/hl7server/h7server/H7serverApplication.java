package com.hl7server.h7server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.Servlet;

@SpringBootApplication
public class H7serverApplication {

    public static void main(String[] args) throws Exception {


        SpringApplication.run(H7serverApplication.class, args);

        int port = 9000;
        // Create a Jetty server instance
        Server server = new Server(port);
        Context context = new Context(server, "/Hl7Listener", Context.SESSIONS);
        Servlet servlet = new Hl7ReceivingMessage();

        /*
         * Adds the servlet to listen at
         * http://localhost:9000/Hl7Listener/Incoming
         */
        context.addServlet(new ServletHolder(servlet), "/Incoming");
        server.start();
       // server.stop();

    }


}
