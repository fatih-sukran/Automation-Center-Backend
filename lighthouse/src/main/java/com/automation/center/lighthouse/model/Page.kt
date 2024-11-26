package com.automation.center.lighthouse.model

import jakarta.persistence.*
import lombok.Data

@Data
@Entity(name = "page")
class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String? = null
    var url: String? = null

    @OneToMany(mappedBy = "page", cascade = [CascadeType.ALL], orphanRemoval = true)
    var results: List<Result> = ArrayList()

    @ManyToOne
    @JoinColumn(name = "suite_id")
    var suite: Suite? = null
}
