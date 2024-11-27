package com.automation.center.lighthouse.dto.suite

data class AddSuiteDto(
    var name: String? = null,
    var description: String? = null,
    var cron: String? = null
)
