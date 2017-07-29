/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.streamingpool.core.starter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.streamingpool.core.conf.DefaultStreamFactories;
import org.streamingpool.core.conf.EmbeddedPoolConfiguration;

/**
 * Streamingpool configuration
 */
@Configuration
@Import({ EmbeddedPoolConfiguration.class, DefaultStreamFactories.class })
public class StreamingPoolCoreStarterConfiguration {
    /* */
}
