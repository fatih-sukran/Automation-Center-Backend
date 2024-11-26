package com.automation.center.test.page;

import com.automation.center.lighthouse.LighthouseApplication
import com.automation.center.lighthouse.service.PageService
import com.automation.center.test.DummyData
import org.assertj.core.api.Assertions.assertThat
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
class PageServiceTests {
    @Autowired
    private lateinit var service: PageService
    private val dummyData: DummyData.PageData = DummyData.PageData

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
        assertThat(foundDto).isEqualTo(dummyData.dto4.copy(id = savedDto.id))
    }

    @Test
    fun deleteById() {
        service.deleteById(dummyData.dto1.id)
        val foundDto = service.findById(dummyData.dto1.id).getOrNull()
        assertThat(foundDto).isNull()

        val allDto = service.findAll()
        assertThat(allDto.size).isEqualTo(2)
        assertThat(allDto).isEqualTo(listOf(dummyData.dto2, dummyData.dto3))
    }

    @Test
    fun deleteByDto() {
        service.delete(dummyData.dto2)
        val foundDto = service.findById(dummyData.dto2.id).getOrNull()
        assertThat(foundDto).isNull()

        val allDto = service.findAll()
        assertThat(allDto.size).isEqualTo(2)
        assertThat(allDto).isEqualTo(listOf(dummyData.dto1, dummyData.dto3))
    }
}
