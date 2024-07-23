package com.automation.center.lighthouse.dto.suiteItem

import lombok.Data

@Data
data class AddSuiteItemDto(
    var url: String? = null,
    var cron: String? = null,
    var suiteId: Long? = null,
    val metricIds: List<Long> = ArrayList()
)
