package com.automation.center.test.report

import com.automation.center.lighthouse.LighthouseApplication
import com.automation.center.lighthouse.service.ReportService
import com.automation.center.test.DummyData.ReportData
import com.automation.center.test.DummyData.ResultData
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
class ReportServiceTests {

    @Autowired
    private lateinit var service: ReportService

    @Test
    fun findAll() {
        val allDto = service.findAll()
        assertThat(allDto.size).isEqualTo(3)
        assertThat(allDto).isEqualTo(listOf(ReportData.dto1, ReportData.dto2, ReportData.dto3))
    }

    @Test
    fun findById() {
        val foundDto = service.findById(ReportData.dto1.id).getOrNull()
        assertThat(foundDto).isNotNull()
        assertThat(foundDto).isEqualTo(ReportData.dto1)
    }

    @Test
    fun save() {
        val savedDto = service.save(ReportData.addDto)
        assertThat(savedDto).isNotNull()

        val foundDto = service.findById(savedDto.id).getOrNull()
        assertThat(foundDto).isNotNull()
        assertThat(foundDto).isEqualTo(ReportData.dto4)
    }


    @Test
    fun deleteById() {
        service.delete(ReportData.dto2.id)

        val foundDto = service.findById(ReportData.dto2.id)
        assertTrue(foundDto.isEmpty)

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto).isEqualTo(listOf(ReportData.dto1, ReportData.dto3))
    }

    @Test
    fun deleteByDto() {
        service.delete(ReportData.dto3)

        val foundDto = service.findById(ReportData.dto3.id)
        assertTrue(foundDto.isEmpty)

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto).isEqualTo(listOf(ReportData.dto1, ReportData.dto2))
    }

    @Test
    fun addResult() {
        service.addResult(ResultData.addDto)
        val foundDto = service.findById(ResultData.addDto.reportId).getOrNull()
        assertThat(foundDto).isNotNull()
        assertThat(foundDto?.results).isEqualTo(
            listOf(
                ResultData.dto1,
                ResultData.dto2,
                ResultData.dto3,
                ResultData.dto4
            )
        )
    }

    @Test
    fun removeResult() {
        service.removeResult(ResultData.dto3)

        val foundDto = service.findById(ResultData.addDto.reportId).getOrNull()
        assertThat(foundDto).isNotNull()
        assertThat(foundDto?.results).isEqualTo(listOf(ResultData.dto1, ResultData.dto2))
    }
}