package com.automation.center.lighthouse.repository;

import com.automation.center.lighthouse.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MetricRepository extends JpaRepository<Metric, Long> {

    @Modifying
    @Query(nativeQuery = true, value = """
            DELETE FROM metric
            WHERE id NOT IN (
                SELECT metric_id
                FROM test_suite_metric
            )
            """)
    void deleteAll();
}
