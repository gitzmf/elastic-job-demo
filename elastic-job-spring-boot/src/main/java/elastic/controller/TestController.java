package elastic.controller;

import elastic.config.ElasticJobConfig;
import elastic.job.MySimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private ElasticJobConfig elasticJobConfig;

    @RequestMapping("/addJob")
    public void addJob() {
        int shardingTotalCount = 2;
        elasticJobConfig.addSimpleJobScheduler(new MySimpleJob().getClass(),"* * * * * ?",shardingTotalCount,"0=A,1=B");

    }

}