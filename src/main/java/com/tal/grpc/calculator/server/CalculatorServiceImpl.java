package com.tal.grpc.calculator.server;

import com.proto.calculator.*;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        SumResponse sumResponse = SumResponse.newBuilder()
                .setSumResult( request.getFirstNumber() + request.getSecondNumber())
                .build();

        responseObserver.onNext(sumResponse);
        responseObserver.onCompleted();
    }


    @Override
    public void primeNumberDecomposition(PrimeNumberDecompositionRequest request, StreamObserver<PrimeNumberDecompositionResponse> responseObserver) {
        //PrimeNumberDecompositionResponse response = PrimeNumberDecompositionResponse.newBuilder();

        int k = 2;
        int N = request.getNumber();

        try {
            while (N > 1) {
                if (N % k == 0) {
                    // print k
                    PrimeNumberDecompositionResponse response = PrimeNumberDecompositionResponse.newBuilder()
                            .setPrimeFactor(k)
                            .build();

                    responseObserver.onNext(response);
                    N = N / k;

                    Thread.sleep(500L);
                } else {
                    k++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            responseObserver.onCompleted();
        }
    }
}
