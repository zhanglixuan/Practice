package com.example.domain.query;

import com.example.domain.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 张丽璇
 * @date 2023/2/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AccountQuery extends Account {
	private Double money2;
}
