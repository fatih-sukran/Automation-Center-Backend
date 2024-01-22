package com.fatih.automation.common.model;

import com.fatih.automation.common.enums.BuildStatus;
import com.fatih.automation.common.model.build.ClassBuild;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Entity
@Table(name = "test_build")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TestBuild extends BaseEntity {

    private String queueRef;
    private Integer queueId;

    @OneToMany(mappedBy = "testBuild")
    private List<ClassBuild> classBuilds;

    @Enumerated
    private BuildStatus buildStatus = BuildStatus.IN_QUEUE;
}
