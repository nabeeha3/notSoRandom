import java.util.Random;

// using https://docs.oracle.com/javase/7/docs/api/java/util/Random.html
public class notSoRandom
{
    public static int nextNum(long seed) 
    {
        //"calculate" the next number by exploiting java's 'random'
        int bits = 32;
        long seed2 = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
        return (int)(seed2 >>> (48 - bits));
    }

    public static void main(String[] args) 
    {
        long firstNum = -1822586175;
        long secondNum = -638982434;
        long seed = 0;
        //check all next nums (after the first) and when we get the second num, we have found the seed
        for (int i = 0; i < 65536; i++) {
            seed = firstNum * 65536 + i;
            if (nextNum(seed) == secondNum) {
                System.out.println("Seed found: " + seed);
               break;
            }
        }
        //use seed for next num after first two
        Random randomNum = new Random((seed ^ 0x5DEECE66DL) & ((1L << 48) - 1));
        int next = randomNum.nextInt();
        int secondNext = randomNum.nextInt();
        System.out.println("second number is: " + next + " and so the third one is: " + secondNext + " with seed: " + seed);
    }
}
