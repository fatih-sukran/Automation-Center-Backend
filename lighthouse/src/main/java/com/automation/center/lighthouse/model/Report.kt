package com.automation.center.lighthouse.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity(name = "report")
class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var date: LocalDateTime? = null
    var suiteName: String? = null

    @ManyToOne
    @JoinColumn(name = "test_suite_id")
    var testSuite: TestSuite? = null

    @OneToMany
    @JoinColumn(name = "report_id")
    val reportItems: List<ReportItem> = ArrayList()
}
