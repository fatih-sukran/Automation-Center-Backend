package com.automation.center.lighthouse.dto.suiteItem

import com.automation.center.lighthouse.dto.metric.MetricDto
import lombok.Data

@Data
data class SuiteItemDto(
    var id: Long? = null,
    var url: String? = null,
    var cron: String? = null,
    val metrics: List<MetricDto> = ArrayList()
)
