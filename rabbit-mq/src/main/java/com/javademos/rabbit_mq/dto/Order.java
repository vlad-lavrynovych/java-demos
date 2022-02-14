package com.javademos.rabbit_mq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true, includeFieldNames = true)
public class Order {

    private int id;

    private String data;
}
