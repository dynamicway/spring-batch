package me.study.late_binding_job_and_step_attributes.recorder

import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class BeanLifeCycleRecorder {
    private val records = mutableMapOf<KClass<*>, MutableList<BeanLifeCycle>>()

    fun record(kClass: KClass<*>, currentLifeCycle: BeanLifeCycle) = records.computeIfAbsent(kClass) { _ ->
        mutableListOf(currentLifeCycle)
    }

    fun getRecords(kClass: KClass<*>) =
        records[kClass] ?: throw IllegalStateException("Cannot found record. record: $kClass")

}
