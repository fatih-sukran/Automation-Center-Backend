package com.automation.center.lighthouse.dto.reportItem

import lombok.Data

@Data
data class AddReportItemDto(
    var url: String? = null,
    var value: String? = null,
    var metricId: Long? = null,
    var reportId: Long? = null,
)
