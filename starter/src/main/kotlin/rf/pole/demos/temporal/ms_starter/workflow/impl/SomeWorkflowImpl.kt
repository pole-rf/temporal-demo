package rf.pole.demos.temporal.ms_starter.workflow.impl

import io.temporal.activity.ActivityOptions
import io.temporal.spring.boot.WorkflowImpl
import io.temporal.workflow.Async
import io.temporal.workflow.Workflow
import rf.pole.demos.temporal.ms_pub_sub.activity.api.PublishEventA
import rf.pole.demos.temporal.ms_starter.workflow.api.FirstActivity
import rf.pole.demos.temporal.ms_starter.workflow.api.SecondActivity
import rf.pole.demos.temporal.ms_starter.workflow.api.SomeWorkflow
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import kotlin.time.measureTime

@WorkflowImpl(workers = ["starter"], taskQueues = ["starter"])
open class SomeWorkflowImpl : SomeWorkflow {
    private var proceed: Boolean = false
    private val log = Workflow.getLogger(SomeWorkflowImpl::class.java)!!

//    init {
//        println("init{}: ${Thread.currentThread().id} / ${Thread.currentThread().name} / ${this.toString()} / $proceed")
//    }

    private val firstActivity = Workflow.newActivityStub(
        FirstActivity::class.java,
        ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(10)).build()
    )
    private val secondActivity = Workflow.newActivityStub(
        SecondActivity::class.java,
        ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(10)).build()
    )

    private val publishEventA = Workflow.newActivityStub(
        PublishEventA::class.java,
        ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(10)).build()
    )

    override fun execute(data: String) {
        // Этой строкой можно убедиться, что метод eecute() выполняется многократно:
        // - каждый раз при создании класса SomeWorkflowImpl
        // - Temporal помнит "точку сохранения" и код пере-выполнятеся до этой точки
        // - Точка сохранения - фактически это вызов Activity или Signal - если для данного WorkflowId в истории выполнения нет информации - это означает что это и есть точка сохранения
        println("execute(): ${Thread.currentThread().id} / ${Thread.currentThread().name} / ${this.toString()}")

        log.debug("Starting SomeWorkflow execution")

//        val resFirst = Async.function(firstActivity::doFirstAction, data)
//        val resSecond = Async.function(secondActivity::doSecondAction, data)

        Workflow.await(Duration.ofMinutes(40)) {
            println("zzz(${Instant.now().toEpochMilli()}) ${Thread.currentThread().id} / ${Thread.currentThread().name} / ${this.toString()}")

            this.proceed
        }

        println("ooo(${Instant.now().toEpochMilli()}) ${Thread.currentThread().id} / ${Thread.currentThread().name} / ${this.toString()}")

//        resFirst.get()
//        resSecond.get()
        log.debug("Activities finished")

        val r = publishEventA.publish("subType1", data)
        log.debug("r: $r")
    }

    /**
     *  При выполнении query() сначала будет создан экземпляр класса SomeWorkflowImpl
     *  Потом будет выполнен код метода execute() и только после этого будет выполнен метод query()
     */
    override fun query(): String {
        println("query():    ${Thread.currentThread().id} / ${Thread.currentThread().name} / ${this.toString()}")
        return "123123"
    }

    /**
     *  При выполнении sianal() сначала будет создан экземпляр класса SomeWorkflowImpl
     *  Потом будет выполнен код метода execute() и только после этого будет выполнен метод signal()
     */
    override fun signal() {
        println("signal():   ${Thread.currentThread().id} / ${Thread.currentThread().name} / ${this.toString()}")
        proceed = true
    }
}