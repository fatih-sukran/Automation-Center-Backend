package com.automation.center.lighthouse.dto.suiteItem

import lombok.Data

@Data
data class AddSuiteUrlDto(
    var suiteId: Long? = null,
    val url: String? = null
)
