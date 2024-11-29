package com.automation.center.lighthouse.dto.report

import com.automation.center.lighthouse.dto.result.ResultDto
import java.time.LocalDateTime

data class ReportDto(
    var id: Long? = null,
    var date: LocalDateTime? = null,
    var suiteId: Long? = null,
    var suiteName: String? = null,
    val results: List<ResultDto> = ArrayList()
)
