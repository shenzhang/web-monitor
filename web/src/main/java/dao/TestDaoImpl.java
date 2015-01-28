package dao;

import java.util.Random;

/**
 * User: shenzhang
 * Date: 1/23/15
 * Time: 2:30 PM
 */
public class TestDaoImpl implements TestDao {
    @Override
    public int random() {
        System.out.println("generating random number...");

        Random random = new Random();
        return random.nextInt();
    }
}
