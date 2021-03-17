
import java.util.Random;

class myThread
{
        private static int[] myBuffer;

        private static int getRandomX(int min, int max)
        {
            Random x = new Random();
            return x.nextInt((max - min) + 1) + min;
        }

        public static void main(String[] args) throws InterruptedException
        {
            myBuffer = new int[1];

            Thread t1;
            t1 = new Thread(() -> {
                if(myBuffer[0] == 0) {
                    int x = getRandomX(1000, 5000);
                    System.out.println("Generated value <x> : " + x + " " + Thread.currentThread().getName());
                    myBuffer[0] = x;
                    System.out.println("Value <x> " + myBuffer[0] + " has been stored");
                }
            });

            Thread t2;
            t2 = new Thread(() -> {
                if (myBuffer[0] != 0) {
                    try {
                        System.out.println("Reader <y> waits for " + Thread.currentThread().getName());
                        Thread.sleep(myBuffer[0]);
                        System.out.println("Reader <y> " + Thread.currentThread().getName() + " waited " + myBuffer[0]);
                        myBuffer[0] = 0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });


            t1.start();
            t1.join();

            t2.start();
            t2.join();


        }
}
