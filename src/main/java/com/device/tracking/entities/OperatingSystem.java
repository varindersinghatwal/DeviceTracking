package com.device.tracking.entities;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OperatingSystem {
    String name;
    String version;
}
