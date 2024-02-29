package digital.softwareshinobi.napkinexchange.trader.service;

import digital.softwareshinobi.napkinexchange.order.AccountTransaction;
import digital.softwareshinobi.napkinexchange.trader.exception.AccountBalanceException;
import digital.softwareshinobi.napkinexchange.trader.exception.AccountNotFoundException;
import digital.softwareshinobi.napkinexchange.trader.exception.InvalidAccountException;
import digital.softwareshinobi.napkinexchange.trader.model.Account;
import digital.softwareshinobi.napkinexchange.trader.repository.AccountRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    private final Integer DEFAULTBALANCE = 1000000;

    public List<Account> findAllAccounts() {

        return accountRepository.findAll();

    }

    public Account getAccountByName(String username) throws AccountNotFoundException {

        return accountRepository.findById(username)
                .orElseThrow(() -> new AccountNotFoundException("No account with that username"));

    }

    public void saveTraderAccount(Account account) {

        accountRepository.save(account);

    }

    public void createTraderAccount(String username, String password) throws InvalidAccountException {

        if (doesTraderAccountExist(username)) {

            throw new InvalidAccountException("Unable to create account. There is already an account with that username");

        }

        Account newAccount = new Account(username, password);

        newAccount.updateAccountBalance(DEFAULTBALANCE);

        accountRepository.save(newAccount);

        newAccount = null;
    }

    public boolean doesTraderAccountExist(String username) {

        try {

            getAccountByName(username);

            return true;

        } catch (AccountNotFoundException exception) {

            return false;

        }

    }

    public void updateBalanceAndSave(AccountTransaction accountTransaction)
            throws AccountNotFoundException, AccountBalanceException {

        Account account = getAccountByName(accountTransaction.getUsername());

        account.updateAccountBalance(accountTransaction.getAmountToAdd());

        this.saveTraderAccount(account);

        account = null;

    }

    public void updateBalanceAndSave(Account account, double amountToAdd) {

        account.updateAccountBalance(amountToAdd);

        this.saveTraderAccount(account);

    }

}
