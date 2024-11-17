package com.automation.center.test.page;

import com.automation.center.lighthouse.LighthouseApplication
import com.automation.center.lighthouse.dto.page.AddPageDto
import com.automation.center.lighthouse.dto.page.PageDto
import com.automation.center.lighthouse.service.PageService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import kotlin.jvm.optionals.getOrNull

@Sql(scripts = ["/sql/suite/insert.sql", "/sql/page/insert.sql"], executionPhase = BEFORE_TEST_METHOD)
@Sql(scripts = ["/sql/page/delete.sql", "/sql/suite/delete.sql"], executionPhase = AFTER_TEST_METHOD)
@SpringBootTest(classes = [LighthouseApplication::class])
class PageServiceTests {
    private var dto1 = PageDto(id = 21L, suiteId = 21L, url = "url21", name = "Page 21")
    private var dto2 = PageDto(id = 22L, suiteId = 21L, url = "url22", name = "Page 22")
    private var dto3 = PageDto(id = 23L, suiteId = 21L, url = "url23", name = "Page 23")
    private var dto4 = PageDto(id = 1L, suiteId = 21L, url = "new url", name = "New Page")
    private var addDto = AddPageDto(suiteId = 21L, url = "new url", name = "New Page")

    @Autowired
    private lateinit var service: PageService

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
        service.deleteById(dto1.id)
        val foundDto = service.findById(dto1.id).getOrNull()
        assertThat(foundDto).isNull()

        val allDto = service.findAll()
        assertThat(allDto.size).isEqualTo(2)
        assertThat(allDto).isEqualTo(listOf(dto2, dto3))
    }

    @Test
    fun deleteByDto() {
        service.delete(dto2)
        val foundDto = service.findById(dto2.id).getOrNull()
        assertThat(foundDto).isNull()

        val allDto = service.findAll()
        assertThat(allDto.size).isEqualTo(2)
        assertThat(allDto).isEqualTo(listOf(dto1, dto3))
    }
}
