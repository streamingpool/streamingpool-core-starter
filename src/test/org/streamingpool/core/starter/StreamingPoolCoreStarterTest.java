/**
 * Copyright (c) 2017 European Organisation for Nuclear Research (CERN), All Rights Reserved.
 */

package org.streamingpool.core.starter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.streamingpool.core.service.DiscoveryService;
import org.streamingpool.core.service.ProvidingService;
import org.streamingpool.core.service.StreamId;
import org.streamingpool.core.testing.NamedStreamId;

import io.reactivex.Flowable;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StreamingPoolCoreStarterTest {

    private static final StreamId<Long> STREAM_ID = NamedStreamId.ofName("ANY_STREAM");

    @Autowired
    private DiscoveryService discoveryService;

    @Autowired
    private ProvidingService providingService;

    @Test(timeout = 500)
    public void discoveryProvideSimpleTest() {
        providingService.provide(STREAM_ID, Flowable.interval(10, TimeUnit.MILLISECONDS).take(6));
        Publisher<Long> streamId = discoveryService.discover(STREAM_ID);

        List<Long> data = Flowable.fromPublisher(streamId).buffer(6).blockingFirst();
        Assertions.assertThat(data).contains(0l, 1l, 2l, 3l, 4l, 5l);

    }

}
