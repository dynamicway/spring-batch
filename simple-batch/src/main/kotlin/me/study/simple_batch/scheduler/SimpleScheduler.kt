package me.study.simple_batch.scheduler

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SimpleScheduler(
    private val simpleTaskletJob: Job,
    private val jobLauncher: JobLauncher
) {
    @Scheduled(fixedDelay = 5 * 1000L)
    fun executeSimpleJob() {
        jobLauncher.run(simpleTaskletJob, JobParameters(mapOf("requestDate" to JobParameter("12345678", String::class.java))))
    }
}

