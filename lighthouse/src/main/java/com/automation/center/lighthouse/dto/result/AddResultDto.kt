package com.automation.center.lighthouse.dto.result

import lombok.Data

@Data
data class AddResultDto(
    var reportId: Long? = null,
    var pageId: Long? = null,
    var metricId: Long? = null,
    var value: String? = null,
)
