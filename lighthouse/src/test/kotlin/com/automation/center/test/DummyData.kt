package com.automation.center.test

import com.automation.center.lighthouse.dto.metric.AddMetricDto
import com.automation.center.lighthouse.dto.metric.MetricDto
import com.automation.center.lighthouse.dto.page.AddPageDto
import com.automation.center.lighthouse.dto.page.PageDto
import com.automation.center.lighthouse.dto.report.AddReportDto
import com.automation.center.lighthouse.dto.report.ReportDto
import com.automation.center.lighthouse.dto.result.AddResultDto
import com.automation.center.lighthouse.dto.result.ResultDto
import com.automation.center.lighthouse.dto.suite.AddSuiteDto
import com.automation.center.lighthouse.dto.suite.SuiteDto
import java.time.LocalDateTime

class DummyData {
    object MetricData {
        val dto1: MetricDto = MetricDto(21L, "Metric 21", "code21")
        val dto2: MetricDto = MetricDto(22L, "Metric 22", "code22")
        val dto3: MetricDto = MetricDto(23L, "Metric 23", "code23")
        val dto4: MetricDto = MetricDto(1L, "New Metric", "new code")
        val addDto: AddMetricDto = AddMetricDto("New Metric", "new code")
    }

    object PageData {
        var dto1 = PageDto(id = 21L, suiteId = 21L, url = "url21", name = "Page 21")
        var dto2 = PageDto(id = 22L, suiteId = 21L, url = "url22", name = "Page 22")
        var dto3 = PageDto(id = 23L, suiteId = 21L, url = "url23", name = "Page 23")
        var dto4 = PageDto(id = 1L, suiteId = 21L, url = "new url", name = "New Page")
        var addDto = AddPageDto(suiteId = 21L, url = "new url", name = "New Page")
    }

    object SuiteData {
        val dto1 = SuiteDto(
            id = 21L,
            name = "Suite 21",
            description = "description 21",
            cron = "2 1 0 * * ?",
            metrics = listOf(MetricData.dto1, MetricData.dto2, MetricData.dto3),
            pages = listOf(PageData.dto1, PageData.dto2, PageData.dto3)
        )
        val dto2 = SuiteDto(
            id = 22L,
            name = "Suite 22",
            description = "description 22",
            cron = "2 2 0 * * ?",
            metrics = listOf(MetricData.dto1, MetricData.dto2)
        )
        val dto3 = SuiteDto(
            id = 23L,
            name = "Suite 23",
            description = "description 23",
            cron = "2 3 0 * * ?",
            metrics = listOf(MetricData.dto1)
        )
        val dto4 = SuiteDto(id = 1L, "New Suite", description = "new description", cron = "* * * * * ?")
        val addDto = AddSuiteDto("New Suite", "new description", "* * * * * ?")
    }

    object ResultData {
        var dto1 = ResultDto(id = 21L, value = "21", page = PageData.dto1, metric = MetricData.dto1, reportId = 21L)
        var dto2 = ResultDto(id = 22L, value = "22", page = PageData.dto1, metric = MetricData.dto2, reportId = 21L)
        var dto3 = ResultDto(id = 23L, value = "23", page = PageData.dto1, metric = MetricData.dto3, reportId = 21L)
        var dto4 = ResultDto(id = 1L, value = "1", page = PageData.dto1, metric = MetricData.dto1, reportId = 21L)
        var addDto = AddResultDto(value = "1", pageId = PageData.dto1.id, metricId = MetricData.dto1.id, reportId = 21L)
    }

    object ReportData {
        var dto1 = ReportDto(
            id = 21L,
            date = LocalDateTime.parse("2020-01-01T00:00:00"),
            suiteId = SuiteData.dto1.id,
            results = listOf(ResultData.dto1, ResultData.dto2, ResultData.dto3)
        )
        var dto2 = ReportDto(id = 22L, date = LocalDateTime.parse("2020-01-02T00:00:00"), suiteId = SuiteData.dto1.id)
        var dto3 = ReportDto(id = 23L, date = LocalDateTime.parse("2020-01-03T00:00:00"), suiteId = SuiteData.dto1.id)
        var dto4 = ReportDto(id = 1L, date = LocalDateTime.parse("2020-01-04T00:00:00"), suiteId = SuiteData.dto1.id)
        var addDto = AddReportDto(date = LocalDateTime.parse("2020-01-04T00:00:00"), suiteId = SuiteData.dto1.id)
    }
}
