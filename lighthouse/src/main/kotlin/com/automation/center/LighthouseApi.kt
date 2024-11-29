package com.automation.center

import com.automation.center.lighthouse.dto.metric.MetricDto
import com.automation.center.lighthouse.dto.page.PageDto
import com.jayway.jsonpath.JsonPath
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

data class Result(val page: PageDto, var metric: Map<MetricDto, String>)

class LightHouseApi(private val pages: List<PageDto>, private val metrics: List<MetricDto>) {

    fun getResults(): List<Result> {
        return pages.stream().map { getResult(it) }.toList()
    }

    private fun getResult(pages: PageDto): Result {
        val endpoint = "https://www.googleapis.com/pagespeedonline/v5/runPagespeed"
        val uri = UriComponentsBuilder.fromHttpUrl(endpoint)
            .queryParam("url", pages.url)
            .queryParam("strategy", "mobile")
            .queryParam("category", "performance")
            .build()
            .toUri()
        val response = RestTemplate().getForEntity(uri, String::class.java).body

        val map = HashMap<MetricDto, String>()
        val jsonPath = JsonPath.parse(response)

        for (metric in metrics) {
            // Use safe casting and convert the value to String
            val value = jsonPath.read<Any>(metric.jsonPath) // Read value as Any
            map[metric] = value.toString() // Convert to String and assign to the map
        }

        return Result(pages, map)
    }
}
