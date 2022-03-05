package com.metaprofile.api.metaprofile.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMetaDataPayload {
    private Long mpId;
    private Long mpcId;
    private Long mpfId;
    private Object data;
}
