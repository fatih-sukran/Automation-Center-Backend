package com.automation.center.test.metric

import com.automation.center.lighthouse.LighthouseApplication
import com.automation.center.lighthouse.service.MetricService
import com.automation.center.test.DummyData.MetricData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import kotlin.jvm.optionals.getOrNull

@Sql("/sql/insert.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql("/sql/delete.sql", executionPhase = AFTER_TEST_METHOD)
@SpringBootTest(classes = [LighthouseApplication::class])
class MetricServiceTests {

    @Autowired
    private lateinit var service: MetricService

    @Test
    fun findAll() {
        val allDto = service.findAll()

        assertThat(allDto.size).isEqualTo(3)
        assertThat(allDto).isEqualTo(listOf(MetricData.dto1, MetricData.dto2, MetricData.dto3))
    }

    @Test
    fun findById() {
        val foundDto = service.findById(MetricData.dto1.id).getOrNull()

        assertThat(foundDto).isNotNull()
        assertThat(foundDto).isEqualTo(MetricData.dto1)
    }

    @Test
    fun save() {
        val savedDto = service.save(MetricData.addDto)
        assertThat(savedDto).isNotNull()

        val foundDto = service.findById(savedDto.id).getOrNull()
        assertThat(foundDto).isNotNull()
        assertThat(foundDto).isEqualTo(savedDto)
        assertThat(foundDto).isEqualTo(MetricData.dto4)
    }

    @Test
    fun deleteById() {
        service.delete(MetricData.dto2.id)

        val foundDto = service.findById(MetricData.dto2.id)
        assertTrue(foundDto.isEmpty)

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto).isEqualTo(listOf(MetricData.dto1, MetricData.dto3))
    }

    @Test
    fun deleteByDto() {
        service.delete(MetricData.dto3)

        val foundDto = service.findById(MetricData.dto3.id)
        assertTrue(foundDto.isEmpty)

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto).isEqualTo(listOf(MetricData.dto1, MetricData.dto2))
    }
}
