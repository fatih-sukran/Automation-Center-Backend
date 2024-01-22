package com.fatih.automation.common.model.build;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatih.automation.common.enums.ResultStatus;
import com.fatih.automation.common.model.BaseEntity;
import com.fatih.automation.common.model.TestBuild;
import com.fatih.automation.common.model.TestClass;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "class_build")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ClassBuild extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "test_class_id")
    TestClass testClass;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_build_id")
    TestBuild testBuild;

    @Enumerated
    ResultStatus resultStatus;
}
