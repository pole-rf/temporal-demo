NotificationsWorkflow notifications = workflowClient.newWorkflowStub(
    NotificationsWorkflow.class,
    WorkflowOptions.newBuilder().setTaskQueue(NS_NOTIFICATIONS).build());

// Отправлем смс
WorkflowClient.start(notifications::sendSms, phone,
	// Параметры для шаблона смс
	Map.of(
    	"amount", "1234.00",
    	"reason", "списание"
	)
)

// Отправляем EMail с приложениями
WorkflowClient.start(notifications::sendEmail, emailTo,
	// Параметры для шаблона email
	Map.of(
    	"amount", "1234.00",
    	"reason", "списание",
	),
	// Ссылки на приложения
	Map.of(
    	"summary.pdf", "s3-signed-url",		
    	"details.pdf", "s3-signed-url",
	),
)



