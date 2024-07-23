package com.automation.center.lighthouse.dto.report

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class AddReportDto(
    var date: LocalDateTime? = null,
    @JsonProperty("suite_name")
    var suiteName: String? = null,
)
