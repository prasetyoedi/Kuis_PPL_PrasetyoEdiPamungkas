import org.example.StringUtils;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StringUtilsTest {

    private StringUtils stringUtils;

    @BeforeEach
    void setup() {
        stringUtils = new StringUtils();
        System.out.println("Sebelum memulai setiap pengujian");
    }

    @Test
    void testReverseString() {
        System.out.println("Menguji pembalikan string untuk input null, string kosong, dan string normal");
        assertNull(stringUtils.reverseString(null), "Harus mengembalikan null untuk input null");
        assertEquals("", stringUtils.reverseString(""), "Harus mengembalikan string kosong untuk input kosong");
        assertEquals("cba", stringUtils.reverseString("abc"), "Harus mengembalikan string terbalik untuk input normal");
    }

    @Test
    void testReverseStringWithSpacesAndSpecialCharacters() {
        System.out.println("Menguji reverse/pembalikan string yang mengandung spasi dan karakter khusus");
        assertAll("Menguji reverseString dengan spasi dan karakter khusus",
                () -> assertEquals(" dcba", stringUtils.reverseString("abcd "), "Harus membalikkan string dengan spasi di akhir"),
                () -> assertEquals("!cba", stringUtils.reverseString("abc!"), "Harus membalikkan string dengan karakter khusus di awal"),
                () -> assertEquals("321 CBA", stringUtils.reverseString("ABC 123"), "Harus membalikkan string dengan angka dan spasi")
        );
    }

    @Test
    void testIsPalindrome() {
        System.out.println("Menguji apakah string merupakan palindrome, termasuk untuk input null dan string kosong");
        assertFalse(stringUtils.isPalindrome(null), "Harus mengembalikan false untuk input null");
        assertTrue(stringUtils.isPalindrome(""), "Harus mengembalikan true untuk input kosong");
        assertTrue(stringUtils.isPalindrome("A man, a plan, a canal, Panama"), "Harus mengembalikan true untuk palindrome dengan karakter non-alfanumerik");
        assertFalse(stringUtils.isPalindrome("abc"), "Harus mengembalikan false untuk input bukan palindrome");
    }

    @Test
    void testIsPalindromeWithNumbersAndMixedCases() {
        System.out.println("Menguji apakah string dengan angka dan huruf kapital merupakan palindrome");
        assertAll("Menguji isPalindrome dengan angka dan kasus campuran",
                () -> assertTrue(stringUtils.isPalindrome("12321"), "Harus mengembalikan true untuk palindrome numerik"),
                () -> assertTrue(stringUtils.isPalindrome("RaceCar"), "Harus mengembalikan true untuk palindrome dengan kasus campuran"),
                () -> assertFalse(stringUtils.isPalindrome("123abc"), "Harus mengembalikan false untuk input campuran bukan palindrome")
        );
    }

    @Test
    void testCountVowels() {
        System.out.println("Menguji perhitungan jumlah vokal dalam string untuk input null, string kosong, dan string normal");
        assertEquals(0, stringUtils.countVowels(null), "Harus mengembalikan 0 untuk input null");
        assertEquals(0, stringUtils.countVowels(""), "Harus mengembalikan 0 untuk input kosong");
        assertEquals(5, stringUtils.countVowels("A quick brown fox"), "Harus mengembalikan jumlah vokal yang benar untuk input normal");
    }

    @Test
    void testCountVowelsWithSpecialCharactersAndNumbers() {
        System.out.println("Menguji perhitungan jumlah vokal dalam string yang juga mengandung karakter khusus dan angka");
        assertAll("Menguji countVowels dengan karakter khusus dan angka",
                () -> assertEquals(0, stringUtils.countVowels("my!"), "Harus mengembalikan 0 untuk 'my!' karena tidak mengandung vokal"),
                () -> assertEquals(4, stringUtils.countVowels("u123eio"), "Harus menghitung vokal dengan angka, 'u123eio' mengandung 4 vokal"),
                () -> assertEquals(0, stringUtils.countVowels("123456"), "Harus mengembalikan 0 untuk '123456' karena tidak mengandung vokal")
        );
    }

    @AfterAll
    void afterAll() {
        stringUtils = null;
        System.out.println("Setelah semua pengujian, stringUtils diatur ke null");
    }
}
