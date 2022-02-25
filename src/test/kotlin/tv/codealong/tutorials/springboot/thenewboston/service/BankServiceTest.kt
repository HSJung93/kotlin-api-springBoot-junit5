package tv.codealong.tutorials.springboot.thenewboston.service

import io.mockk.every
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import tv.codealong.tutorials.springboot.thenewboston.datasource.BankDataSource
import io.mockk.mockk
import io.mockk.verify

internal class BankServiceTest{

    private val dataSource: BankDataSource = mockk(relaxed = true) // basic value is returned without return?
    private val bankService = BankService(dataSource)

    @Test
    fun `should call its data source to retrieve banks`() {
        // given

//        every { dataSource.retrieveBanks() } returns emptyList()

        // when
        bankService.getBanks()


        // then
        // verify that the method you put in here was being done in the realm of your test.
        verify(exactly = 1) { dataSource.retrieveBanks() }

    }

}