package me.study.late_binding_job_and_step_attributes.scope.job

import me.study.late_binding_job_and_step_attributes.config.BatchTest
import me.study.late_binding_job_and_step_attributes.recorder.BeanLifeCycleRecorder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@BatchTest
@SpringBootTest(classes = [JobScopeBeanLifeCycleConfiguration::class, BeanLifeCycleRecorder::class])
class JobScopeLifeCycleTest @Autowired constructor(
    private val jobLauncherTestUtils: JobLauncherTestUtils,
    private val beanLifeCycleRecorder: BeanLifeCycleRecorder
) {

    @Test
    fun if_step_has_StepScope_annotation_then_a_new_step_instance_is_created_every_time_the_job_is_executed() {
        val numberOfExecute = (1..10).random()
        repeat(numberOfExecute) {
            jobLauncherTestUtils.launchJob()
        }
        assertThat(beanLifeCycleRecorder.getRecords("lifeCycleRecordStepA")).hasSize(numberOfExecute * 2)
        assertThat(beanLifeCycleRecorder.getRecords("lifeCycleRecordStepB")).hasSize(numberOfExecute * 2)
    }

    @Test
    fun test() {
        val mutableMapOf = mutableMapOf<Int, List<Int>>()
        repeat(10) {
            mutableMapOf.computeIfAbsent(it) { i -> mutableListOf(it) }
        }
    }

}
