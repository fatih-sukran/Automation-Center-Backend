package com.automation.center.lighthouse.dto.reportItem

import com.automation.center.lighthouse.dto.metric.MetricDto

data class ReportItemDto(
    var id: Long? = null,
    var url: String? = null,
    var value: String? = null,
    var metric: MetricDto? = null,
    val reportId: Long,
)
