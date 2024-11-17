package com.automation.center.test.metric

import com.automation.center.lighthouse.LighthouseApplication
import com.automation.center.lighthouse.dto.metric.AddMetricDto
import com.automation.center.lighthouse.dto.metric.MetricDto
import com.automation.center.lighthouse.service.MetricService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import kotlin.jvm.optionals.getOrNull

@Sql("/sql/metric/insert.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql("/sql/metric/delete.sql", executionPhase = AFTER_TEST_METHOD)
@SpringBootTest(classes = [LighthouseApplication::class])
class MetricJpaTests {
    private val dto1 = MetricDto(21L, "Metric 21", "code21")
    private val dto2 = MetricDto(22L, "Metric 22", "code22")
    private val dto3 = MetricDto(23L, "Metric 23", "code23")
    private val dto4 = MetricDto(1L, "New Metric", "new code")
    private val addDto = AddMetricDto("New Metric", "new code")

    @Autowired
    private lateinit var service: MetricService

    @Test
    fun findAll() {
        val allDto = service.findAll()

        assertThat(allDto.size).isEqualTo(3)
        assertThat(allDto).isEqualTo(listOf(dto1, dto2, dto3))
    }

    @Test
    fun findById() {
        val foundDto = service.findById(dto1.id).getOrNull()

        assertThat(foundDto).isNotNull()
        assertThat(foundDto).isEqualTo(dto1)
    }

    @Test
    fun save() {
        val savedDto = service.save(addDto)
        assertThat(savedDto).isNotNull()

        val foundDto = service.findById(savedDto.id).getOrNull()
        assertThat(foundDto).isNotNull()
        assertThat(foundDto).isEqualTo(savedDto)
        assertThat(foundDto).isEqualTo(dto4)
    }

    @Test
    fun deleteById() {
        service.delete(dto2.id)

        val foundDto = service.findById(dto2.id)
        assertTrue(foundDto.isEmpty)

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto).isEqualTo(listOf(dto1, dto3))
    }

    @Test
    fun deleteByDto() {
        service.delete(dto3)

        val foundDto = service.findById(dto3.id)
        assertTrue(foundDto.isEmpty)

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto).isEqualTo(listOf(dto1, dto2))
    }
}
