package me.study.late_binding_job_and_step_attributes.recorder

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
import kotlin.reflect.KClass

@Component
class BeanLifeCycleRecorder {
    private val index = AtomicLong()
    private val records = mutableMapOf<KClass<*>, ConcurrentHashMap<Long, BeanLifeCycle>>()

    fun record(kClass: KClass<*>, currentLifeCycle: BeanLifeCycle) = records.computeIfAbsent(kClass) { _ ->
        val records = ConcurrentHashMap<Long, BeanLifeCycle>()
        records[index.getAndIncrement()] = currentLifeCycle
        records
    }

    fun getRecords(kClass: KClass<*>) =
        records[kClass] ?: throw IllegalStateException("Cannot found record. record: $kClass")

}
