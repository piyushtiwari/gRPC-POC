package com.tal.grpc.greeting.server;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {

        // Extract the fields
        Greeting greeting = request.getGreeting();

        // Create the response
        String result = "Hello " + greeting.getFirstName();
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();

        // Send the response
        responseObserver.onNext(response);

        // Compete the RPC call
        responseObserver.onCompleted();
    }
}
