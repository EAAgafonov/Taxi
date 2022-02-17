package com.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "iway", url = "${iway.url}")
public interface IWayClient extends IWay {
}
