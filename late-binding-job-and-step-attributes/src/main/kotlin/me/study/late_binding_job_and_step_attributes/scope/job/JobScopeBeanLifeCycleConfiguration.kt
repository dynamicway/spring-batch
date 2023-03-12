package me.study.late_binding_job_and_step_attributes.scope.job

import me.study.late_binding_job_and_step_attributes.recorder.BeanLifeCycle
import me.study.late_binding_job_and_step_attributes.recorder.BeanLifeCycleRecorder
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class JobScopeBeanLifeCycleConfiguration(
    private val beanLifeCycleRecorder: BeanLifeCycleRecorder
) {

    @Bean
    fun lifeCycleRecordJob(jobRepository: JobRepository, lifeCycleRecordStepA: Step, lifeCycleRecordStepB: Step): Job {
        val job = JobBuilder("lifeCycleRecordJob", jobRepository)
            .start(lifeCycleRecordStepA)
            .next(lifeCycleRecordStepB)
            .build()
        beanLifeCycleRecorder.record(job.name, BeanLifeCycle.CREATED)
        return job
    }

    @Bean
    @JobScope
    fun lifeCycleRecordStepA(
        jobRepository: JobRepository, platformTransactionManager: PlatformTransactionManager
    ): Step {
        val name = "lifeCycleRecordStepA"
        val step = StepBuilder(name, jobRepository).tasklet({ _, _ ->
                                                                beanLifeCycleRecorder.record(
                                                                    name,
                                                                    BeanLifeCycle.EXECUTED
                                                                )
                                                                RepeatStatus.FINISHED
                                                            }, platformTransactionManager).build()
        beanLifeCycleRecorder.record(name, BeanLifeCycle.CREATED)
        return step
    }

    @Bean
    @JobScope
    fun lifeCycleRecordStepB(
        jobRepository: JobRepository, platformTransactionManager: PlatformTransactionManager
    ): Step {
        val name = "lifeCycleRecordStepB"
        val step = StepBuilder(name, jobRepository).tasklet({ _, _ ->
                                                                beanLifeCycleRecorder.record(
                                                                    name,
                                                                    BeanLifeCycle.EXECUTED
                                                                )
                                                                RepeatStatus.FINISHED
                                                            }, platformTransactionManager).build()
        beanLifeCycleRecorder.record(name, BeanLifeCycle.CREATED)
        return step
    }

}
