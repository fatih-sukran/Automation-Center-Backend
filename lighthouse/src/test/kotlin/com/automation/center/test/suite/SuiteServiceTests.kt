package com.automation.center.test.suite

import com.automation.center.lighthouse.LighthouseApplication
import com.automation.center.lighthouse.service.SuiteService
import com.automation.center.test.DummyData
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
class SuiteServiceTests {
    @Autowired
    private lateinit var service: SuiteService
    private val dummyData: DummyData.SuiteData = DummyData.SuiteData
    private val metricDummyData: DummyData.MetricData = DummyData.MetricData
    private val pageDummyData: DummyData.PageData = DummyData.PageData

    @Test
    fun findAll() {
        val allDto = service.findAll()

        assertThat(allDto.size).isEqualTo(3)
        assertThat(allDto).isEqualTo(listOf(dummyData.dto1, dummyData.dto2, dummyData.dto3))
    }

    @Test
    fun findById() {
        val foundDto = service.findById(dummyData.dto1.id).getOrNull()

        assertThat(foundDto).isNotNull()
        assertThat(foundDto).isEqualTo(dummyData.dto1)
    }

    @Test
    fun save() {
        val savedDto = service.save(dummyData.addDto)
        assertThat(savedDto).isNotNull()

        val foundDto = service.findById(savedDto.id).getOrNull()
        assertThat(foundDto).isNotNull()
        assertThat(foundDto).isEqualTo(savedDto)
        assertThat(foundDto).isEqualTo(dummyData.dto4)
    }

    @Test
    fun deleteById() {
        service.delete(dummyData.dto2.id)

        val foundDto = service.findById(dummyData.dto2.id)
        assertTrue(foundDto.isEmpty)

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto).isEqualTo(listOf(dummyData.dto1, dummyData.dto3))
    }

    @Test
    fun deleteByDto() {
        service.delete(dummyData.dto3)

        val foundDto = service.findById(dummyData.dto3.id)
        assertTrue(foundDto.isEmpty)

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto).isEqualTo(listOf(dummyData.dto1, dummyData.dto2))
    }

    @Test
    fun addMetric() {
        service.addMetricToSuite(dummyData.dto3.id, metricDummyData.dto3.id)
        val foundDto = service.findById(dummyData.dto3.id).getOrNull()

        assertThat(foundDto).isNotNull()
        assertThat(foundDto?.metrics).isEqualTo(listOf(metricDummyData.dto1, metricDummyData.dto3))
    }

    @Test
    fun removeMetric() {
        service.removeMetricFromSuite(dummyData.dto1.id, metricDummyData.dto1.id)
        val foundDto = service.findById(dummyData.dto1.id).getOrNull()

        assertThat(foundDto).isNotNull()
        assertThat(foundDto?.metrics).isEqualTo(listOf(metricDummyData.dto2, metricDummyData.dto3))
    }

    @Test
    fun addPage() {
        val savedPageDto = service.addPageToSuite(pageDummyData.addDto)
        val foundDto = service.findById(dummyData.dto1.id).getOrNull()

        assertThat(foundDto).isNotNull()
        assertThat(foundDto?.pages).hasSize(4)
        assertThat(foundDto?.pages).isEqualTo(
            listOf(
                pageDummyData.dto1,
                pageDummyData.dto2,
                pageDummyData.dto3,
                pageDummyData.dto4.copy(id = savedPageDto.id)
            )
        )
    }

    @Test
    fun removePage() {
        service.removePageFromSuite(pageDummyData.dto1)
        val foundDto = service.findById(pageDummyData.dto1.id).getOrNull()

        assertThat(foundDto).isNotNull()
        assertThat(foundDto?.pages).hasSize(2)
        assertThat(foundDto?.pages).isEqualTo(listOf(pageDummyData.dto2, pageDummyData.dto3))
    }
}
