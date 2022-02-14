package com.java_demos.email_integration.integration;

import com.java_demos.email_integration.properties.ApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class OrderIntegrationConfig {

    @Bean
    public IntegrationFlow orderFlow(
            ApiProperties apiProperties,
            EmailToOrderTransformer emailToOrderTransformer,
            OrderMessageHandler orderMessageHandler) {

//        return IntegrationFlows
//
//                .from(Mail.imapInboundAdapter(
//                        emailProps.getImapUrl()),
//                        e -> e.poller(Pollers.fixedDelay(emailProps.getPollRate())))
//
//                .transform(emailToOrderTransformer)
//                .handle(orderMessageHandler)
//                .get();

        log.info("\n ============" + apiProperties);
        return IntegrationFlows
                .from(Http.inboundChannelAdapter("/send-order")
                        .requestMapping(r -> r.methods(HttpMethod.POST)
                                .consumes("application/json"))
                        .requestPayloadType(Order.class))

                .transform(order -> {
                    log.info("\n Received Order from send url: " + order);
                    log.info("Will redirect to receive url");
                    return order;
                })
                .handle(orderMessageHandler)
                .get();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
//        List<HttpMessageConverter<?>> converters = new ArrayList<>();
//        converters.add(new MappingJackson2HttpMessageConverter());
//        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }
}
