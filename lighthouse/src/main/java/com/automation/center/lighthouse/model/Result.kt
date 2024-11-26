package com.automation.center.lighthouse.model

import jakarta.persistence.*
import lombok.Data

@Data
@Entity(name = "result")
class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "result_value")
    var value: String? = null

    @ManyToOne
    @JoinColumn(name = "report_id")
    var report: Report? = null

    @ManyToOne
    @JoinColumn(name = "metric_id")
    var metric: Metric? = null

    @ManyToOne
    @JoinColumn(name = "page_id")
    var page: Page? = null
}
