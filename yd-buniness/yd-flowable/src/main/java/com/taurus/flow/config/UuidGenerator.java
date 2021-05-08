package com.taurus.flow.config;

import org.flowable.common.engine.impl.persistence.StrongUuidGenerator;

/**
 * @Description:
 */
public class UuidGenerator extends StrongUuidGenerator {

    @Override
    public String getNextId() {
        String uuid = super.getNextId();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

}
