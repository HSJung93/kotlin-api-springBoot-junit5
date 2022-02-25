package tv.codealong.tutorials.springboot.thenewboston.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest{

    // POJO
    private val mockDataSource = MockBankDataSource()

    @Test
    fun `should privide a collection of banks`() {
        // given(arrange)
        
        // when(act): actual call that's being made that you want to test
        val banks = mockDataSource.retrieveBanks()

        // then(assert)
//        assertThat(banks).isNotEmpty
        assertThat(banks.size).isGreaterThanOrEqualTo(3)

    }
    
    @Test
    fun `should provide some mock data`() {
        
        // when
        val banks = mockDataSource.retrieveBanks()
        
        // then
        assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        assertThat(banks).anyMatch { it.trust != 0.0 }
        assertThat(banks).anyMatch { it.transactionFee != 0 }
    }
    
    

}