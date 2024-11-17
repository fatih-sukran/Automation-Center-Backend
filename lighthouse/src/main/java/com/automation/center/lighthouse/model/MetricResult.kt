package com.automation.center.lighthouse.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "metric_result")
@Entity
class MetricResult() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "url_id")
    var url: Page? = null

    @ManyToOne
    @JoinColumn(name = "metric_id")
    var metric: Metric? = null

    @Column(name = "\"value\"")
    var value: String? = null

    @Column(name = "date")
    var date: LocalDateTime? = null

    constructor(id: Long) : this() {
        this.id = id
    }
}