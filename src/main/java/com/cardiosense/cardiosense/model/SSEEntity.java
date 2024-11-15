package com.cardiosense.cardiosense.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sse_events")
@Data
@AllArgsConstructor
public class SSEEntity {

    @Id
    private String id;

    private String message;
}
