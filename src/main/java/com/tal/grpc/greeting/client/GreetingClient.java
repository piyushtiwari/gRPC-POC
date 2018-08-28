package com.tal.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("Hello, I am a gRPC client");
        //unaryCall();

        // Server Streaming Call
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888)
                .usePlaintext()
                .build();

        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Piyush")
                .setLastName("T")
                .build();

        GreetManyTimesRequest greetManyTimesRequest = GreetManyTimesRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        // We stream the responses ( in a blocking manner )
        greetClient.greetManyTimes(greetManyTimesRequest)
                .forEachRemaining( greetManyTimesResponse -> {
                    System.out.println(greetManyTimesResponse.getResult());
                });

        shutdownChannel(channel);
    }

    private static void unaryCall(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888)
                .usePlaintext()
                .build();

        System.out.println("Created Stub");

        // Old and Dummy
        //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
        //DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);

        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Piyush")
                .setLastName("T")
                .build();

        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        // call the RPC and get the response
        GreetResponse greetResponse = greetClient.greet(greetRequest);
        // Do something
        System.out.println(greetResponse.getResult());

        shutdownChannel(channel);
    }

    private static void shutdownChannel(ManagedChannel channel){
        System.out.println("Shutting Down Channel");
        channel.shutdown();
    }
}
