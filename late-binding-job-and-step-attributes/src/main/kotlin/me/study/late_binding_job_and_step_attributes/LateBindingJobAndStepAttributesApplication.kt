package me.study.late_binding_job_and_step_attributes

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
class LateBindingJobAndStepAttributesApplication

fun main() {
    runApplication<LateBindingJobAndStepAttributesApplication>()
}
