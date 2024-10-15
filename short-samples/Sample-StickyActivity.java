// Выбираем уникальное имя для TaskQueue
// Удобнее будет брать имя POD-а
String uniqueTaskQueueName = "someQueue_" + UUID.randomUUID().toString()

// Сохраняем имя пода внутри активити
// Это нужно чтобы первым активити привязать воркфлоу к определённому POD-у
StoreActivitiesImpl storeActivityImpl = 
    new StoreActivitiesImpl(uniqueTaskQueueName)

// Создаём объект Worker привязанный к текущему pod-у
// И регистрируем наш Activity в этом воркере
Worker workerLinkedToCurrentPod = workerFactory.newWorker(uniqueTaskQueueName);
workerLinkedToCurrentPod.registerActivitiesImplementations(storeActivityImpl);



// Внутри Workflow блок Sticky Activity нужно обернуть для общего ретрая
Workflow.retry(retryOptions, Optional.empty(), () -> processFileImpl(source, destination))






