package me.study.simple_batch.job

import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.support.AbstractPlatformTransactionManager

@Configuration
class SimpleConfig(
    private val jobRepository: JobRepository,
    private val platformTransactionManager: AbstractPlatformTransactionManager,
) {
    @Bean
    fun simpleJob() = JobBuilder("simpleJob", jobRepository)
        .start(simpleStep())
        .build()

    @Bean
    fun simpleStep() = StepBuilder("simpleStep", jobRepository)
        .tasklet({ _, _ ->
                     repeat(100) {
                         println("$it")
                     }
                     RepeatStatus.FINISHED
                 }, platformTransactionManager)
        .build()
}
