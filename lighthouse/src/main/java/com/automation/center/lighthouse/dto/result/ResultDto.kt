package com.automation.center.lighthouse.dto.result

import com.automation.center.lighthouse.dto.metric.MetricDto
import com.automation.center.lighthouse.dto.page.PageDto

data class ResultDto(
    var id: Long? = null,
    var value: String? = null,
    var page: PageDto? = null,
    var metric: MetricDto? = null,
    var reportId: Long? = null,
)
