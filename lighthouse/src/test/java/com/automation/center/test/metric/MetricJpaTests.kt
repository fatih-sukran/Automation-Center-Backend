package com.automation.center.test.metric

import com.automation.center.lighthouse.LighthouseApplication
import com.automation.center.lighthouse.dto.metric.AddMetricDto
import com.automation.center.lighthouse.dto.metric.MetricDto
import com.automation.center.lighthouse.service.MetricService
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql

@Sql("/sql/metric.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SpringBootTest(classes = [LighthouseApplication::class])
class MetricJpaTests {
    private val dto1 = MetricDto(1L, "Metric 1", "code1")
    private val dto2 = MetricDto(2L, "Metric 2", "code2")
    private val dto4 = MetricDto(4L, "Metric 4", "code4")
    private val addDto = AddMetricDto("Metric 4", "code4")

    @Autowired
    private lateinit var service: MetricService

    @Test
    fun findAll() {
        val allDto = service.findAll()

        assertThat(allDto.size).isEqualTo(3)
        assertThat(allDto.first().name).isEqualTo(dto1.name)
        assertThat(allDto.first().code).isEqualTo(dto1.code)
    }

    @Test
    fun findById() {
        val firstDto = service.findAll().first()
        val foundDto = service.findById(firstDto.id)

        assertThat(foundDto).isNotNull()
        assertThat(foundDto.name).isEqualTo(dto1.name)
        assertThat(foundDto.code).isEqualTo(dto1.code)
    }

    @Test
    fun save() {
        val savedDto = service.save(addDto)
        assertThat(savedDto).isNotNull()

        val foundDto = service.findById(savedDto.id)
        assertThat(foundDto).isNotNull()
        assertThat(foundDto).isEqualTo(savedDto)
        assertThat(foundDto.name).isEqualTo(dto4.name)
        assertThat(foundDto.code).isEqualTo(dto4.code)
    }

    @Test
    fun deleteById() {
        val deleteDto = service.findAll().first()
        service.delete(deleteDto.id)

        assertThatThrownBy { service.findById(deleteDto.id) }
            .hasMessage("No value present")

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto.first().name).isEqualTo(dto2.name)
        assertThat(allDto.first().code).isEqualTo(dto2.code)
    }

    @Test
    fun deleteByDto() {
        service.delete(dto2)

        assertThatThrownBy { service.findById(dto2.id) }
            .hasMessage("No value present")

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto.first().name).isEqualTo(dto1.name)
        assertThat(allDto.first().code).isEqualTo(dto1.code)
    }
}
