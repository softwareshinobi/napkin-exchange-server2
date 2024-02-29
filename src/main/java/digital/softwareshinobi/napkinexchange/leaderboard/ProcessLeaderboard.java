package digital.softwareshinobi.napkinexchange.leaderboard;

import digital.softwareshinobi.napkinexchange.trader.model.Account;
import digital.softwareshinobi.napkinexchange.trader.service.AccountService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ProcessLeaderboard {

    @Autowired
    private final AccountService accountService;

    public List<Leaderboard> topTenAccounts() {

        List<Account> accounts
                = SortAccountProfits.sortAccountByProfits(accountService.findAllAccounts());

        return accounts.stream()
                .map(account
                        -> new Leaderboard(
                        accounts.indexOf(account) + 1,
                        account.getUsername(),
                        account.getAccountBalance(),
                        account.getTotalProfits()))
                .limit(10)
                .collect(Collectors.toList());

    }

    public List<Leaderboard> topThreeAccounts() {

        List<Account> accounts
                = SortAccountProfits.sortAccountByProfits(accountService.findAllAccounts());

        return accounts.stream()
                .map(account -> new Leaderboard(
                accounts.indexOf(account) + 1,
                account.getUsername(),
                account.getAccountBalance(),
                account.getTotalProfits()))
                .limit(3)
                .collect(Collectors.toList());

    }

    public Leaderboard findAccountRanking(String username) {

        List<Account> accounts
                = SortAccountProfits.sortAccountByProfits(accountService.findAllAccounts());

        return accounts.stream()
                .filter(account -> account.getUsername().equals(username))
                .map(account -> new Leaderboard(
                accounts.indexOf(account) + 1,
                account.getUsername(),
                account.getAccountBalance(),
                account.getTotalProfits()))
                .findFirst().orElse(null);

    }

}
