package com.example.workflow;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface TransactionWorkflow {
    public static final String QUEUE_NAME = "Account_Transaction";

    @WorkflowMethod
    void startAccountTransferWorkflow();

    @SignalMethod
    void signalSufficientFunds();

    @SignalMethod
    void signalAmountDebited();

    @SignalMethod
    void signalAmountCredited();

}