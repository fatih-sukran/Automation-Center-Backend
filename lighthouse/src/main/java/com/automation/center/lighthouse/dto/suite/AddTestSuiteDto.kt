package com.automation.center.lighthouse.dto.suite

data class AddTestSuiteDto(
    var name: String? = null,
    var description: String? = null,
    var cron: String? = null,
    val metrics: List<Long> = ArrayList(),
    val urls: List<String> = ArrayList()
)
