package com.automation.center.lighthouse.dto.testSuite

import com.automation.center.lighthouse.dto.suiteItem.SuiteItemDto

data class TestSuiteDto(
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var cron: String? = null,
    val suiteItems: List<SuiteItemDto> = ArrayList()
)
