IteratorBatchWorkflow batchWorkflowStub workflowClient.newWorkflowStub(
	IteratorBatchWorkflow.class,
	WorkflowOptions.newBuilder().setTaskQueue(Worker.TASK_QUEUE_NAME).build());

WorkflowClient.start(batchWorkflowStub::processBatch, config.pageSize, 0)

