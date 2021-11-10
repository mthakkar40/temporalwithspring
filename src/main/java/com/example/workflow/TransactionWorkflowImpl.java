package com.example.workflow;

import com.example.activity.MyActivity;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.Workflow;
import io.temporal.workflow.WorkflowMethod;

import java.time.Duration;

public class TransactionWorkflowImpl implements TransactionWorkflow {
    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(500)
            .build();
    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            // Optionally provide customized RetryOptions.
            // Temporal retries failures by default, this is simply an example.
            .setRetryOptions(retryoptions)
            .build();


    private final MyActivity activity = Workflow.newActivityStub(MyActivity.class, defaultActivityOptions);

    public boolean isOrderConfimed = false;

    public boolean isOrderPickedUp = false;

    public boolean isOrderDelivered = false;

    @Override
    void startAccountTransferWorkflow() { }

    @Override
    void signalSufficientFunds() { }

    @Override
    void signalAmountDebited() { }

    @Override
    void signalAmountCredited() { }

    @Override
    public void startApprovalWorkflow() {
        activity.placeOrder();

        System.out.println("***** Waiting for Restaurant to cofirm your order");
        Workflow.await(() -> isOrderConfimed);

        System.out.println(" Please wait till we assign a delivery executive");
        Workflow.await(() -> isOrderPickedUp);
        Workflow.await(() -> isOrderDelivered);
    }

    @Override
    public void signalOrderAccepted() {
        activity.setOrderAccepted();
        this.isOrderConfimed = true;
    }
    @Override
    public void signalOrderPickedUp() {
        activity.setOrderPickedUp();
        this.isOrderPickedUp = true;
    }

    @Override
    public void signalOrderDelivered() {
        activity.setOrderDelivered();
        this.isOrderDelivered = true;
    }

}
