package com.automation.center.lighthouse.dto.report

import com.automation.center.lighthouse.dto.result.ResultDto
import com.automation.center.lighthouse.dto.suite.SuiteDto
import java.time.LocalDateTime

data class ReportDto(
    var id: Long? = null,
    var date: LocalDateTime? = null,
    var suite: SuiteDto? = null,
    val results: List<ResultDto> = ArrayList()
)
