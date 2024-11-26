package com.automation.center.lighthouse.dto.report

import java.time.LocalDateTime

data class AddReportDto(
    var suiteId: Long? = null,
    var date: LocalDateTime? = null
)
