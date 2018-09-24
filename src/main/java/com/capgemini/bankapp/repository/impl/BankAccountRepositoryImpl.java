package com.capgemini.bankapp.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double getBalance(long accountId) throws AccountNotFoundException {
		double balance = jdbcTemplate.queryForObject("SELECT accountBalance from bankaccount where accountId=?",
				new Object[] { accountId }, Double.class);
		return balance;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		int count = jdbcTemplate.update("update bankaccount set accountBalance=? where accountId=?",
				new Object[] { accountId, newBalance });
		return count != 0;
	}

	@Override
	public boolean addBankAccount(BankAccount account) {
		int count = jdbcTemplate.update("insert into bankaccount values(?,?,?,?)",
				new Object[] { account.getAccountId(), account.getAccountHolderName(), account.getAccountType(),
						account.getAccountBalance() });
		return count != 0;
	}

	@Override
	public BankAccount findBankAccountById(long accountId) {
		return jdbcTemplate.queryForObject("select * from bankaccount where accountId=?", new Object[] { accountId },
				new BankAccountRowMapper());

	}

	@Override
	public List<BankAccount> findAllBankAccounts() {
		jdbcTemplate.query("select * from bankaccount", new Object[] {}, new BankAccountRowMapper());
		return null;
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		int count = jdbcTemplate.update("update bankaccount set accountHolderName=?,acountType=? where accountId=?",
				new Object[] { account.getAccountHolderName(), account.getAccountType() });
		return count != 0 ? account : findBankAccountById(account.getAccountId());
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		int count = jdbcTemplate.update("delete from bankaccount where accountId=?", new Object[] { accountId });
		return count != 0;
	}

	private class BankAccountRowMapper implements RowMapper<BankAccount> {

		@Override
		public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
			BankAccount account = new BankAccount();
			account.setAccountId(rs.getInt(1));
			account.setAccountHolderName(rs.getString(2));
			account.setAccountType(rs.getString(3));
			account.setAccountBalance(rs.getDouble(4));
			return account;
		}

	}

}
