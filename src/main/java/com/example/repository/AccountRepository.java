package com.example.repository;

import com.example.domain.Account;
import com.example.domain.AccountRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class AccountRepository {
    private final JdbcTemplate jdbc;

    public AccountRepository(JdbcTemplate jdbc) {                   ❷
        this.jdbc = jdbc;
    }

    public Account findAccountById(long id) {
        String sql = "SELECT * FROM account WHERE id = ?";            ❸
        return jdbc.queryForObject(sql, new AccountRowMapper(), id);
    }

    public void changeAmount(long id, BigDecimal amount) {
        String sql = "UPDATE account SET amount = ? WHERE id = ?";
        jdbc.update(sql, amount, id);                                 ❹
    }

    public List<Account> findAllAccounts() {
        String sql = "SELECT * FROM account";
        return jdbc.query(sql, new AccountRowMapper());
    }
}
