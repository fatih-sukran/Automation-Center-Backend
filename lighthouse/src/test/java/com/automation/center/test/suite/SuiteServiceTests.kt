package com.automation.center.test.suite

import com.automation.center.lighthouse.LighthouseApplication
import com.automation.center.lighthouse.dto.metric.MetricDto
import com.automation.center.lighthouse.dto.testSuite.AddTestSuiteDto
import com.automation.center.lighthouse.dto.testSuite.TestSuiteDto
import com.automation.center.lighthouse.service.SuiteService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import kotlin.jvm.optionals.getOrNull

@Sql(
    scripts = ["/sql/metric/insert.sql", "/sql/suite/insert.sql", "/sql/suite_metric/insert.sql"],
    executionPhase = BEFORE_TEST_METHOD
)
@Sql(
    scripts = ["/sql/suite_metric/delete.sql", "/sql/suite/delete.sql", "/sql/metric/delete.sql"],
    executionPhase = AFTER_TEST_METHOD
)
@SpringBootTest(classes = [LighthouseApplication::class])
class SuiteServiceTests {
    private val metricDto1 = MetricDto(21L, "Metric 21", "code21")
    private val metricDto2 = MetricDto(22L, "Metric 22", "code22")
    private val metricDto3 = MetricDto(23L, "Metric 23", "code23")
    private val dto1 =
        TestSuiteDto(21L, "Suite 21", "description 21", "2 1 0 * * ?", listOf(metricDto1, metricDto2, metricDto3))
    private val dto2 = TestSuiteDto(22L, "Suite 22", "description 22", "2 2 0 * * ?", listOf(metricDto1, metricDto2))
    private val dto3 = TestSuiteDto(23L, "Suite 23", "description 23", "2 3 0 * * ?", listOf(metricDto1))
    private val dto4 = TestSuiteDto(1L, "New Suite", "new description", "* * * * * ?")
    private val addDto = AddTestSuiteDto("New Suite", "new description", "* * * * * ?")

    @Autowired
    private lateinit var service: SuiteService

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

    @Test
    fun addMetric() {
        service.addMetricToSuite(dto3.id, metricDto3)
        val foundDto = service.findById(dto3.id).getOrNull()

        assertThat(foundDto).isNotNull()
        assertThat(foundDto?.metrics).isEqualTo(listOf(metricDto1, metricDto3))
    }

    @Test
    fun removeMetric() {
        service.removeMetricFromSuite(dto1.id, metricDto1)
        val foundDto = service.findById(dto1.id).getOrNull()

        assertThat(foundDto).isNotNull()
        assertThat(foundDto?.metrics).isEqualTo(listOf(metricDto2, metricDto3))
    }

    //todo: addPage and removePage tests
}
