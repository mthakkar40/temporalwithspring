package com.example.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface MyActivity {
    @ActivityMethod
    void placeOrder();

    @ActivityMethod
    void setOrderAccepted();

    @ActivityMethod
    void setOrderPickedUp();

    @ActivityMethod
    void setOrderDelivered();
}
