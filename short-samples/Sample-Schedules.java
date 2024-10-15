// Что запускаем по расписанию
ScheduleActionStartWorkflow action =
    ScheduleActionStartWorkflow.newBuilder()
        .setWorkflowType(GreetingWorkflow.class)
        .setArguments("World")
        .setOptions(workflowOptions)
        .build();

// Объект расписание - сохранется в БД Temporal
Schedule schedule =
    Schedule.newBuilder()
      // Что именно делаем по расписанию
      .setAction(action)
      
      // Политика параллельных запусков
      .setPolicies(SchedulePolicies.newBuilder()
        .setOverlapPolicy(ScheduleOverlapPolicy.SCHEDULE_OVERLAP_POLICY_TERMINATE_OTHER)
      )
    
      // Когда запускать - расписание + интервалы
      .setSpec(
          ScheduleSpec.newBuilder()
              // Run the schedule at 5pm on Friday
              .setCalendars(
                  Collections.singletonList(
                      ScheduleCalendarSpec.newBuilder()
                          .setHour(Collections.singletonList(new ScheduleRange(17)))
                          .setDayOfWeek(Collections.singletonList(new ScheduleRange(5)))
                          .build()))
              // Run the schedule every 5s
              .setIntervals(
                  Collections.singletonList(new ScheduleIntervalSpec(Duration.ofSeconds(5))))
              .build()).build();

// Сохраняем расписание на сервер
ScheduleHandle handle = 
    scheduleClient.createSchedule("ScheduleId", schedule, ScheduleOptions.newBuilder().build());

