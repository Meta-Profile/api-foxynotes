package com.metaprofile.api.metaprofile.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaProfileUpdatePayload {
    @Nullable
    private String title;

    @Nullable
    private String color;
}
