package me.study.late_binding_job_and_step_attributes.recorder

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Component
class BeanLifeCycleRecorder {
    private val index = AtomicLong()
    private val records = mutableMapOf<String, ConcurrentHashMap<Long, BeanLifeCycle>>()

    fun record(name: String, currentLifeCycle: BeanLifeCycle) {
        val record = records[name]
        if (record == null)
            records[name] = ConcurrentHashMap<Long, BeanLifeCycle>()
                .also { it[index.getAndIncrement()] = currentLifeCycle }
        else record[index.getAndIncrement()] = currentLifeCycle
    }

    fun getRecords(name: String) =
        records[name] ?: throw IllegalStateException("Cannot found record. record: $name")

}
