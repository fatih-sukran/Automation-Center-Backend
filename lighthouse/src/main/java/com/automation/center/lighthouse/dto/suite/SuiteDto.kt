package com.automation.center.lighthouse.dto.suite

import com.automation.center.lighthouse.dto.metric.MetricDto
import com.automation.center.lighthouse.dto.page.PageDto

data class SuiteDto(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var cron: String? = null,
    val metrics: List<MetricDto> = ArrayList(),
    val pages: List<PageDto> = ArrayList()
)
