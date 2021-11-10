package com.example;

import com.example.activity.MyActivity;
import com.example.config.TemporalConfig;
import com.example.workflow.MyWorkflow;
import com.example.workflow.WorkflowImpl;
import io.temporal.activity.Activity;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.workflow.Workflow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TemporalRestDemoApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext appContext = SpringApplication.run(TemporalRestDemoApp.class, args);
        WorkerFactory factory = appContext.getBean(WorkerFactory.class);
        MyActivity signUpActivity = appContext.getBean(MyActivity.class);
        Worker worker = factory.newWorker(MyWorkflow.QUEUE_NAME);
        worker.registerWorkflowImplementationTypes(WorkflowImpl.class);
        worker.registerActivitiesImplementations(signUpActivity);
        factory.start();


        /* WITHOUT SPRINGBOOT
       // WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
       // WorkflowClient client = WorkflowClient.newInstance(service);
        // Worker factory is used to create Workers that poll specific Task Queues.
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(Shared.MONEY_TRANSFER_TASK_QUEUE);
        // This Worker hosts both Workflow and Activity implementations.
        // Workflows are stateful so a type is needed to create instances.
        worker.registerWorkflowImplementationTypes(MoneyTransferWorkflowImpl.class);
        // Activities are stateless and thread safe so a shared instance is used.
        worker.registerActivitiesImplementations(new AccountActivityImpl());
        // Start listening to the Task Queue.
        factory.start();

         */
    }
}
