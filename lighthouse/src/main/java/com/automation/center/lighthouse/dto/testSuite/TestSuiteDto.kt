package com.automation.center.lighthouse.dto.testSuite

import com.automation.center.lighthouse.dto.metric.MetricDto
import com.automation.center.lighthouse.dto.suiteUrl.SuiteUrlDto

data class TestSuiteDto(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var cron: String? = null,
    val metrics: List<MetricDto> = ArrayList(),
    val urls: List<SuiteUrlDto> = ArrayList()
)
