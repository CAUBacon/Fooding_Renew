package teaspoon.fooding.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public class BaseEntity extends BaseTimeEntity {

//    @CreatedBy
//    private Long createdBy;
//
//    @LastModifiedBy
//    private Long lastModifiedBy;
}
