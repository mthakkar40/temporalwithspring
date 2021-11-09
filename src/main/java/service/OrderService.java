package service;

import com.example.workflow.MyWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    WorkflowServiceStubs workflowServiceStubs;

    @Autowired
    WorkflowClient workflowClient;

    public void placeOrder(String workflowId) {
        MyWorkflow workflow = createWorkFlowConnection(workflowId);
        WorkflowClient.start(workflow::startApprovalWorkflow);
    }

    public void makeOrderAccepted(String workflowId) {
        MyWorkflow workflow = workflowClient.newWorkflowStub(MyWorkflow.class, "Order_" + workflowId);
        workflow.signalOrderAccepted();
    }

    public void makeOrderPickedUp(String workflowId) {
        MyWorkflow workflow = workflowClient.newWorkflowStub(MyWorkflow.class, "Order_" + workflowId);
        workflow.signalOrderPickedUp();
    }

    public void makeOrderDelivered(String workflowId) {
        MyWorkflow workflow = workflowClient.newWorkflowStub(MyWorkflow.class, "Order_" + workflowId);
    }

    public MyWorkflow createWorkFlowConnection(String Id) {
        WorkflowOptions options = WorkflowOptions.newBuilder().setTaskQueue(MyWorkflow.QUEUE_NAME)
                                  .setWorkflowId("Order_" + Id).build();
        return workflowClient.newWorkflowStub(MyWorkflow.class, options);

    }
}
