package com.automation.center.lighthouse.dto.report

import com.automation.center.lighthouse.dto.reportItem.ReportItemDto
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ReportDto(
    var id: Long? = null,
    var date: LocalDateTime? = null,
    @JsonProperty("suite_id")
    var suiteId: Long? = null,
    @JsonProperty("suite_name")
    var suiteName: String? = null,
    @JsonProperty("results")
    val reportItems: List<ReportItemDto> = ArrayList()
)
