package job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zmf
 * @version 1.0
 * @ClassName MyJob
 * @Description: 自定义作业类
 * @date 2020/2/25 16:08
 */
@Slf4j
public class MyJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        // 获取分片总数
        int shardingTotalCount = shardingContext.getShardingTotalCount();
        // 获取分片项
        int shardingItem = shardingContext.getShardingItem();
        // 获取分片参数
        String shardingParameter = shardingContext.getShardingParameter();

        log.info("作业名称" + shardingContext.getJobName());
        log.info("分片总数" + shardingTotalCount);
        log.info("分片项" + shardingItem);
        log.info("分片项参数" + shardingParameter);

        switch(shardingItem){
            case 0:
                log.info("第1个分片项参数" + shardingParameter);
                break;
            case 1:
                log.info("第2个分片项参数" + shardingParameter);
                break;
            case 2:
                log.info("第3个分片项参数" + shardingParameter);
                break;
            case 3:
                log.info("第4个分片项参数" + shardingParameter);
                break;
            default :
                log.info("默认项");
                break;
        }
    }
}
