package me.study.late_binding_job_and_step_attributes.config

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.test.context.SpringBatchTest
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
@SpringBatchTest
annotation class BatchTest
