package com.automation.center.lighthouse.dto.suiteUrl

import lombok.Data

@Data
data class AddSuiteUrlDto(
    var suiteId: Long? = null,
    val url: String? = null
)
