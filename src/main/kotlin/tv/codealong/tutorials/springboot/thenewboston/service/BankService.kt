package tv.codealong.tutorials.springboot.thenewboston.service

import org.springframework.stereotype.Service
import tv.codealong.tutorials.springboot.thenewboston.datasource.BankDataSource
import tv.codealong.tutorials.springboot.thenewboston.model.Bank

// calling datasource
// handling or mapping exceptions performing transformations on the data
@Service
class BankService(private val dataSource : BankDataSource) {
    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)

}