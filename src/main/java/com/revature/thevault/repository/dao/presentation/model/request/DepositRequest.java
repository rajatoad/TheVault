<<<<<<<< HEAD:src/main/java/com/revature/thevault/repository/dao/presentation/model/response/DepositRequest.java
package com.revature.thevault.repository.dao.presentation.model.response;
========
package com.revature.thevault.presentation.model.request;
>>>>>>>> c36a619d68f031656f36f1c13eb20d3e5cbc78d3:src/main/java/com/revature/thevault/repository/dao/presentation/model/request/DepositRequest.java

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepositRequest {
    String depositType;
    int accountId;
    String reference;
    int amount;
}
