package com.tal.grpc.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hellooooo");

        Server server = ServerBuilder.forPort(8888)
                .addService(new GreetServiceImpl())
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook( new Thread( ()->{
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the Server");
        } ));

        server.awaitTermination();
    }
}
