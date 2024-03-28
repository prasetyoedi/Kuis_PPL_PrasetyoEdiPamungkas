import org.example.Wallet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class WalletTest {

    private Wallet wallet;

    @BeforeEach
    void setup() {
        wallet = new Wallet(100, "USD");
        System.out.println("Sebelum memulai setiap pengujian, inisiasi dompet baru dengan saldo 100 dan mata uang USD");
    }

    @Test
    void testGetBalance() {
        Assertions.assertEquals(100, wallet.getBalance(), "Saldo harus sama dengan saldo awal");
        System.out.println("Menguji pengambilan saldo, saldo saat ini: " + wallet.getBalance() + "dengan mata uang: " + wallet.getCurrency());
    }

    @Test
    void testGetCurrency() {
        Assertions.assertNotNull(wallet.getCurrency(), "Mata uang tidak boleh null");
        Assertions.assertEquals("USD", wallet.getCurrency(), "Mata uang harus cocok");
        System.out.println("Menguji pengambilan mata uang, saldo saat ini: " + wallet.getBalance() + "dengan mata uang: " + wallet.getCurrency());
    }

    @Test
    void testDepositAmount() {
        wallet.depositAmount(50);
        Assertions.assertEquals(150, wallet.getBalance(), "Saldo harus bertambah setelah setoran");
        System.out.println("Menguji fungsi setoran, saldo saat ini: " + wallet.getBalance() + "dengan mata uang: " + wallet.getCurrency());
    }

    @Test
    void testWithdrawAmountSuccess() {
        boolean result = wallet.withdrawAmount(50);
        System.out.println("Menguji penarikan uang dengan saldo yang cukup, saldo saat ini: " + wallet.getBalance() + " dengan mata uang: " + wallet.getCurrency());
        Assertions.assertAll("Penarikan uang berhasil dan saldo berkurang",
                () -> Assertions.assertTrue(result, "Penarikan harus berhasil"),
                () -> Assertions.assertEquals(50, wallet.getBalance(), "Saldo harus berkurang setelah penarikan")
        );
    }

    @Test
    void testWithdrawAmountFail() {
        boolean result = wallet.withdrawAmount(150);
        Assertions.assertFalse(result, "Penarikan harus gagal karena saldo tidak cukup");
        System.out.println("Menguji penarikan uang dengan saldo tidak cukup, saldo saat ini: " + wallet.getBalance() + "dengan mata uang: " + wallet.getCurrency());
    }

    @Test
    void testTransferFundsSuccess() {
        Wallet recipient = new Wallet(0, "USD");
        wallet.transferFunds(recipient, 50);
        Assertions.assertAll(
                () -> Assertions.assertEquals(50, wallet.getBalance(), "Saldo pengirim harus berkurang setelah transfer"),
                () -> Assertions.assertEquals(50, recipient.getBalance(), "Saldo penerima harus bertambah setelah transfer")
        );
        System.out.println("Menguji transfer dana antar dompet dengan mata uang yang sama, saldo saat ini: " + wallet.getBalance() + "dengan mata uang: " + wallet.getCurrency());
    }

    @Test
    void testTransferFundsFailDifferentCurrencies() {
        Wallet recipient = new Wallet(0, "EUR");
        Assertions.assertThrows(IllegalArgumentException.class, () -> wallet.transferFunds(recipient, 50), "Harus melempar eksepsi untuk transfer dengan mata uang yang berbeda");
        System.out.println("Menguji transfer dana antar dompet dengan mata uang yang berbeda, saldo saat ini: " + wallet.getBalance() + "dengan mata uang: " + wallet.getCurrency());
    }

    @AfterEach
    void tearDown() {
        wallet = new Wallet(100, "USD");
        System.out.println("Setelah pengujian dilakukan, saldo saat ini: " + wallet.getBalance() + "dengan mata uang: " + wallet.getCurrency());
    }

    @AfterAll
    void resetWallet() {
        wallet = null;
        System.out.println("Setelah semua pengujian selesai, dompet direset menjadi null.");
    }
}
