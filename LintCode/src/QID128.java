// http://www.lintcode.com/en/problem/hash-function/
public class QID128 {
    public int hashCode(char[] key,int HASH_SIZE) {

        if (key == null || key.length == 0 || HASH_SIZE <= 0) {
            return 0;
        }

        // (a * b) % p = (a % p * b % p) % p
        // (a + b) % p = (a % p + b % p) % p
        long sum = 0;
        for (int i = 0; i < key.length; i++) {
            sum = (sum * 33 + key[i]) % HASH_SIZE;
        }

        return (int)sum;
    }
}
