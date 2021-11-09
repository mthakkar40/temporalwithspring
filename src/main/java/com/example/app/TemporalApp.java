package com.example.app;

import com.example.activity.MyActivity;
import com.example.workflow.MyWorkflow;
import com.example.workflow.WorkflowImpl;
import io.temporal.activity.Activity;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.workflow.Workflow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TemporalApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = SpringApplication.run(TemporalApp.class, args);
        WorkerFactory wFactory = appContext.getBean(WorkerFactory.class);
        MyActivity signUpActivity = appContext.getBean(MyActivity.class);
        Worker worker = wFactory.newWorker(MyWorkflow.QUEUE_NAME);
        worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
        worker.registerActivitiesImplementations(signUpActivity);
        wFactory.start();
    }
}
