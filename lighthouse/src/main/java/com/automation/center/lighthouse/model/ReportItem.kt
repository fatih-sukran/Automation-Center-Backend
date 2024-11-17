package com.automation.center.lighthouse.model

import jakarta.persistence.*
import lombok.Data

@Data
@Entity(name = "report_item")
class ReportItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var url: String? = null

    @Column(name = "\"value\"")
    var value: String? = null

    @ManyToOne
    @JoinColumn(name = "metric_id")
    var metric: Metric? = null

    @ManyToOne
    @JoinColumn(name = "report_id")
    var report: Report? = null
}
