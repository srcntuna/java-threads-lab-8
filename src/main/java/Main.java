import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Main {




    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Future> listFuture = new ArrayList<>();

        for(int i = 0 ;i<3;i++){

            Random random = new Random();

            Future<String> futureInt  = executorService.submit(() ->{
                try{
                    Thread.sleep(random.nextInt(5000));
                    return "Hi Sercan!";
                }catch (InterruptedException e){
                    throw  new RuntimeException();
                }
            });

            listFuture.add(futureInt);
        }


        Thread.sleep(2500);

        int finishedFuturesCount = countFinishedFutures(listFuture);



        System.out.println("finished future count is "+finishedFuturesCount);
        System.out.println("unfinished future count is "+(listFuture.size() - finishedFuturesCount));

        executorService.shutdown();

    }
    public static int countFinishedFutures(List<Future> futures) {
        // your code here

        int count = 0;

        for(Future future : futures){
            if(future.isDone()){
                count++;
            }
        }

        return count;
    }
}