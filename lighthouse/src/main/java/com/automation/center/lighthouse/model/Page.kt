package com.automation.center.lighthouse.model

import jakarta.persistence.*
import lombok.Data
import lombok.ToString

@Data
@Entity(name = "page")
class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var url: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "suite_id")
    var suite: Suite? = null
}
