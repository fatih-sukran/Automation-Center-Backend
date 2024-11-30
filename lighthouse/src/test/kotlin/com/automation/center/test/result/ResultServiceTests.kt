package com.automation.center.test.result

import com.automation.center.lighthouse.LighthouseApplication
import com.automation.center.lighthouse.service.ResultService
import com.automation.center.test.DummyData.ResultData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import kotlin.jvm.optionals.getOrNull

@Sql("/sql/delete.sql", "/sql/insert.sql", executionPhase = BEFORE_TEST_METHOD)
@SpringBootTest(classes = [LighthouseApplication::class])
class ResultServiceTests {

    @Autowired
    private lateinit var service: ResultService

    @Test
    fun findAll() {
        val allDto = service.findAll()
        assertThat(allDto.size).isEqualTo(3)
        assertThat(allDto).isEqualTo(listOf(ResultData.dto1, ResultData.dto2, ResultData.dto3))
    }

    @Test
    fun findById() {
        val foundDto = service.findById(ResultData.dto1.id).getOrNull()
        assertThat(foundDto).isNotNull()
        assertThat(foundDto).isEqualTo(ResultData.dto1)
    }

    @Test
    fun save() {
        val savedDto = service.save(ResultData.addDto)
        assertThat(savedDto).isNotNull()

        val foundDto = service.findById(savedDto.id).getOrNull()
        assertThat(foundDto).isNotNull()
        val expectedDto = ResultData.dto4.copy(id = savedDto.id)
        assertThat(foundDto).isEqualTo(expectedDto)
    }


    @Test
    fun deleteById() {
        service.delete(ResultData.dto2.id)

        val foundDto = service.findById(ResultData.dto2.id)
        assertTrue(foundDto.isEmpty)

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto).isEqualTo(listOf(ResultData.dto1, ResultData.dto3))
    }

    @Test
    fun deleteByDto() {
        service.delete(ResultData.dto3)

        val foundDto = service.findById(ResultData.dto3.id)
        assertTrue(foundDto.isEmpty)

        val allDto = service.findAll()
        assertThat(allDto).hasSize(2)
        assertThat(allDto).isEqualTo(listOf(ResultData.dto1, ResultData.dto2))
    }
}