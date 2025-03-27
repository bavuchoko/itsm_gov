package simms.gov.itsm.config.token;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 갱신토큰 : ip 를 관리하는 맵.
 * 해당 컬렉션에 쓰레기 데이터가 계속 쌓이는 것을 방지하기 위해 매일 자정 초기화 되어야 한다.
 * */
@Component
public class TokenIpCollection<K,V> {
    private static final Logger log = LoggerFactory.getLogger(TokenIpCollection.class);
    private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public TokenIpCollection() {
        long initialDelay = ChronoUnit.MILLIS.between(LocalDateTime.now(), LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0));
        scheduler.scheduleAtFixedRate(this::reset, initialDelay, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
        log.info("now started server and user token count is  = {}", this.size());
    }

    private void reset() {
        map.clear();
    }

    public void put(K key, V value) {
        log.info("user login ip is = {}", value);
        map.put(key, value);
    }

    public V get(K key) {
        return map.get(key);
    }

    public void remove(K key) {
        log.info("user logout ip is = {}", get(key));
        map.remove(key);
    }

    public int size() {
        return  map.size();
    }

    @PreDestroy
    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException ex) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
