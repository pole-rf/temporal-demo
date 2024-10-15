public int processBatch(int pageSize, int offset) {
    // Получаем очередной список сущностей для обработки
    List<SingleRecord> records = recordLoader.getRecords(pageSize, offset);

    // На каждую сущность стартуем Child Workflow
    // в котором будет происходить обработка
    List<Promise<Void>> results = new ArrayList<>(records.size());
    for (SingleRecord record : records) {
      // Uses human friendly child id.
      String childId = 
        Workflow.getInfo().getWorkflowId() + "/" + record.getId();
      RecordProcessorWorkflow processor =
          Workflow.newChildWorkflowStub(
              RecordProcessorWorkflow.class,
              ChildWorkflowOptions.newBuilder().setWorkflowId(childId).build());
      Promise<Void> result = Async.procedure(processor::processRecord, record);
      results.add(result);
    }

    // Ждём когда все запущенные в текущем батче Child Workflow завершатся
    Promise.allOf(results).get();

    // Если больше записей для обработки нет - завершаем текущий Workflow
    if (records.isEmpty()) {
      return offset;
    }

    // Запускаем Workflow для следующей очереди записей
    return nextRun.processBatch(pageSize, offset + records.size());
}


