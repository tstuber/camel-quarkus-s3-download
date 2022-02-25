package org.acme.getting.started;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;

public class Route extends EndpointRouteBuilder {

    @Override
    public void configure() throws Exception {
        
        from(aws2S3("{{bucket}}"))
            .setHeader("CamelFileName", header("CamelAwsS3Key"))
            .to(file("{{filedir}}"));

        for (int index = 0; index < 10; index++) {
            from(file("{{filedir}}").fileName("file" + index))
                .log("body: ${body}");
        }
    }
    
}
