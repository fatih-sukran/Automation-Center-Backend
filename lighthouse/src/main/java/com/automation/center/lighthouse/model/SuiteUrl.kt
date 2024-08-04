package com.automation.center.lighthouse.model

import jakarta.persistence.*
import lombok.Data
import lombok.ToString

@Data
@Entity(name = "suite_url")
class SuiteUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "url")
    var url: String? = null

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "test_suite_id")
    var testSuite: TestSuite? = null
}
