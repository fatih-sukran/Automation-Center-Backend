package com.automation.center.lighthouse.dto.testSuite

data class AddTestSuiteDto(
    var name: String? = null,
    var description: String? = null,
    var cron: String? = null
)
