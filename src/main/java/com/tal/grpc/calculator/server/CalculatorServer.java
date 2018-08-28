package com.tal.grpc.calculator.server;

import com.tal.grpc.greeting.server.GreetServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class CalculatorServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8889)
                .addService(new CalculatorServiceImpl())
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
