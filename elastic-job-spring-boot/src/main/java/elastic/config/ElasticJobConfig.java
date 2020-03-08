package elastic.config;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zmf
 * @version 1.0
 * @ClassName ElasticJobConfig
 * @Description: ElasticJob配置类,实现当前暂未解决的问题： 动态添加的任务只能在添加的机器上运行，平行部署的其他机器上不会运行该任务
 * @date 2020/3/8 15:35
 */
@Configuration
public class ElasticJobConfig {

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter(@Value("${elaticjob.zookeeper.server-lists}") final String serverList, @Value("${elaticjob.zookeeper.namespace}") final String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }

    @Autowired
    private ZookeeperRegistryCenter regCenter;

    /**
     * 动态添加
     * @param jobClass
     * @param cron
     * @param shardingTotalCount
     * @param shardingItemParameters
     */
    public void addSimpleJobScheduler(final Class<? extends SimpleJob> jobClass,
                                      final String cron,
                                      final int shardingTotalCount,
                                      final String shardingItemParameters){
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder(jobClass.getName(), cron, shardingTotalCount)
                .shardingItemParameters(shardingItemParameters)
                .jobParameter("job参数")
                .build();
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, jobClass.getCanonicalName());
        JobScheduler jobScheduler = new JobScheduler(regCenter, LiteJobConfiguration.newBuilder(simpleJobConfig).build());
        jobScheduler.init();

    }
}