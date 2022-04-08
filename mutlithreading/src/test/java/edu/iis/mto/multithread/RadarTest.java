package edu.iis.mto.multithread;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;
import java.util.concurrent.Executor;

@ExtendWith(MockitoExtension.class)
class RadarTest {

    @Mock
    private PatriotBattery batteryMock;

    @RepeatedTest(100)
    void launchPatriotOnceAfterNoticingAScudMissile() {
        final int numOfRockets = 1;
        Executor executor = Runnable::run;
        BetterRadar radar = new BetterRadar(numOfRockets,batteryMock, executor);
        Scud enemyMissile = new Scud();
        radar.afterNotice(enemyMissile);
        verify(batteryMock).launchPatriot(enemyMissile);
    }
    @RepeatedTest(100)
    void launchPatriotXTimesAfterNoticingRandomNumberOfScudMissiles() {
        int numOfRockets = new Random().nextInt(10) + 1;
        Executor executor = Runnable::run;
        BetterRadar radar = new BetterRadar(numOfRockets,batteryMock, executor);
        Scud enemyMissile = new Scud();
        radar.afterNotice(enemyMissile);
        verify(batteryMock,times(numOfRockets)).launchPatriot(enemyMissile);
    }

}
