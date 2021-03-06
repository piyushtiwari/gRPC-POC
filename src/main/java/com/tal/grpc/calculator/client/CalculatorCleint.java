package com.tal.grpc.calculator.client;

import com.proto.calculator.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class CalculatorCleint {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8889)
                .usePlaintext()
                .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        // Unary
//        SumRequest sumRequest = SumRequest.newBuilder()
//                .setFirstNumber(10)
//                .setSecondNumber(5)
//                .build();
//
//        SumResponse sumResponse = stub.sum(sumRequest);
//
//        System.out.println(sumRequest.getFirstNumber() + " + " + sumRequest.getSecondNumber() + " = " + sumResponse.getSumResult());
//
//        channel.shutdown();


        // Streamin Server
        PrimeNumberDecompositionRequest request = PrimeNumberDecompositionRequest.newBuilder()
                .setNumber(120)
                .build();

        stub.primeNumberDecomposition(request)
                .forEachRemaining( primeNumberDecompositionResponse -> {
                    System.out.println(primeNumberDecompositionResponse.getPrimeFactor());
                });

        channel.shutdown();
    }
}
