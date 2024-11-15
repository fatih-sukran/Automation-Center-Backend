package com.automation.center.test.metric

import com.automation.center.lighthouse.LighthouseApplication
import com.automation.center.lighthouse.dto.metric.AddMetricDto
import com.automation.center.lighthouse.dto.metric.MetricDto
import com.automation.center.lighthouse.mapper.MetricMapper
import com.automation.center.lighthouse.model.Metric
import com.automation.center.lighthouse.repository.MetricRepository
import com.automation.center.lighthouse.service.MetricService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest(classes = [LighthouseApplication::class])
class MetricServiceTest {

    @MockBean
    private lateinit var mapper: MetricMapper

    @MockBean
    private lateinit var repository: MetricRepository

    @Autowired
    private lateinit var service: MetricService

    @Test
    fun `should save metric successfully`() {
        // Given
        val addMetricDto = AddMetricDto("Metric Name", "M001")
        val metric = Metric("Metric Name", "M001")
        val savedMetric = Metric("Metric Name", "M001")
        savedMetric.id = 1L
        val metricDto = MetricDto(1L, "Metric Name", "M001")
        // When
        `when`(mapper.toEntity(addMetricDto)).thenReturn(metric)
        `when`(repository.save(metric)).thenReturn(savedMetric)
        `when`(mapper.toDto(savedMetric)).thenReturn(metricDto)

        // Then
        val result = service.save(addMetricDto)

        // Assert
        assertEquals(1L, result.id)
        assertEquals("Metric Name", result.name)
        assertEquals("M001", result.code)

        // Verify
        verify(mapper, times(1)).toEntity(addMetricDto)
        verify(repository, times(1)).save(metric)
        verify(mapper, times(1)).toDto(savedMetric)
    }

    @Test
    fun `should find metric by id`() {
        // Given
        val metric = Metric("Metric Name", "M001")
        metric.id = 1L
        val metricDto = MetricDto(1L, "Metric Name", "M001")

        // When
        `when`(repository.findById(1L)).thenReturn(Optional.of(metric))
        `when`(mapper.toDto(metric)).thenReturn(metricDto)

        // Then
        val result = service.findById(1L)

        // Assert
        assertNotNull(result)
        assertEquals(metricDto, result)

        // Verify
        verify(repository, times(1)).findById(1L)
        verify(mapper, times(1)).toDto(metric)
    }

    @Test
    fun `should find all metrics`() {
        // Given
        val metric1 = Metric("Metric1", "M001")
        metric1.id = 1L
        val metric2 = Metric("Metric2", "M002")
        metric2.id = 2L
        val metricDto1 = MetricDto(1L, "Metric1", "M001")
        val metricDto2 = MetricDto(2L, "Metric2", "M002")

        // When
        `when`(repository.findAll()).thenReturn(listOf(metric1, metric2))
        `when`(mapper.toDtos(listOf(metric1, metric2))).thenReturn(listOf(metricDto1, metricDto2))

        // Then
        val result = service.findAll()

        // Assert
        assertNotNull(result)
        assertEquals(2, result.size)
        assertEquals(result[0], metricDto1)
        assertEquals(result[1], metricDto2)

        // Verify
        verify(repository, times(1)).findAll()
        verify(mapper, times(1)).toDtos(listOf(metric1, metric2))
    }
}
